package ch.ethz.tgumbschbsse.findcomb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v4.app.ShareCompat;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by tgumbsch on 8/10/17.
 */


public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    Activity mact;

    // The layouts
    private Level mLevel;
    private Progress mProgressBar;



    // Mechanics
    public int mLevelIndicator;
    public int mStartLevel;
    public int mScore;
    private long mTimestamp;
    private long mpm;
    private int deviation;
    public int mLevelsNumber;


    // In case moving is an option
    private Point mPlayer;


    //These objects will be used for drawing
    //private Paint paint;
    private Canvas canvas;
    private int width;
    private int height;
    private SurfaceHolder surfaceHolder;
    private Context mContext;

    //Sounds
    private final MediaPlayer soundfinal;
    private final MediaPlayer soundright;
    private final MediaPlayer soundwrong;


    public GameView(Context context, Activity act) {
        super(context);
        mact = act;

        // The visuals
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;


        soundright = MediaPlayer.create(context,R.raw.stapler);
        soundwrong = MediaPlayer.create(context,R.raw.buzz);
        soundfinal = MediaPlayer.create(context,R.raw.fanfare);

        //init mechanics
        mScore = 120; //The player has two minutes
        mLevelIndicator = 1;
        mTimestamp = System.currentTimeMillis();
        mLevelsNumber = 2;
        mStartLevel = mLevelIndicator;

        mProgressBar = new Progress(mLevelsNumber, width,height);


        //initializing drawing objects
        surfaceHolder = getHolder();
        mContext = context;


        LevelInit();

        //Stuff that we might need later
        mPlayer = new Point(1500, 300);
        playing = true;


    }

    public void make_continous(){
        mLevel.Continous=true;
        mLevelIndicator = -1; //this is the new score which is returned
        mTimestamp = System.currentTimeMillis();
        mScore = 120; //this is just the timer
        playing = true;
        LevelInit();

    }

    public void reset(int start, int end, int score){
        mScore = score; //The player has two minutes
        mLevelIndicator = start;
        mTimestamp = System.currentTimeMillis();
        mLevelsNumber = end;
        mStartLevel = mLevelIndicator;
        playing = true;
        LevelInit();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mScore > 0 && mLevelIndicator <= mLevelsNumber) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mPlayer.set((int) event.getX(), (int) event.getY());
                    mLevel.checkClicked(mPlayer);
            }
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    playing = false;
                    System.out.println(String.valueOf(mScore));
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("score", mScore);
                    ((Activity) mContext).setResult(Activity.RESULT_OK, resultIntent);
                    ((Activity) mContext).finish();
            }

        }


        return true;
        //return super.onTouchEvent(event);
    }

    private void update() {
        mLevel.update();
        if (mScore > 0) {

            if ((System.currentTimeMillis() - mTimestamp) > 1000 && mLevelIndicator <= mLevelsNumber) {
                mScore--;
                mTimestamp = System.currentTimeMillis();
            }

            if (mScore < 0) {
                playing = false;
            }

            if (mLevel.clicked == true) {
                //Evaluate configuration
                mLevel.clicked = false;
                mLevel.compute_score();
                deviation = mLevel.logp;
                mpm = System.currentTimeMillis();
                if(mLevelIndicator<=mLevelsNumber) {
                    mScore += deviation;
                }
                if(deviation>0){
                    if(mLevelIndicator == mLevelsNumber){
                        soundfinal.start();
                    }
                    else {
                        soundright.start();
                    }
                }
                else{
                    soundwrong.start();
                }
                if(mLevel.Continous == true){
                    LevelInit();
                }
                else {
                    if (deviation > 0) {
                        mLevelIndicator++;
                        LevelInit();
                    } else {
                        mLevel.reset();
                    }
                }

            }
        }
    }

    private void draw() {
        //checking if surface is valid
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            Paint paint = new Paint();
            paint.setTextSize(width/30);
            //drawing a background color for canvas
            canvas.drawColor(Color.WHITE);


            //Score drawing
            paint.setColor(Color.BLACK);
            if (mScore > 0 && mLevelIndicator <= mLevelsNumber) {
                mLevel.draw(canvas);
                mProgressBar.draw(canvas);

                Paint paintb = new Paint();
                paintb.setColor(Color.parseColor("#0099cc"));

                canvas.drawRect(new Rect(19*width/20,3*height/20,15*width/20, 3*height/5),paintb);

                paintb.setColor(Color.BLACK);
                paintb.setTextSize(100);
                canvas.drawText("Go!",16*width/20, 8*height/20,paintb);

                canvas.drawText(String.valueOf(mScore), 27 * width / 30, height / 10, paint);


                if(System.currentTimeMillis() - mpm < 1000){
                    if(deviation>0){
                        paint.setColor(Color.GREEN);
                    }
                    else{
                        paint.setColor(Color.RED);
                    }
                    canvas.drawText(String.valueOf(deviation), 24 * width / 30, height / 10, paint);
                }
                //Unlocking the canvas

            } else {
                paint.setTextSize(width/20);
                if (mScore < 0) {
                    if(mLevel.Continous == false) {
                        canvas.drawText("Game Over", width / 3, height / 3, paint);
                        soundwrong.start();
                    }
                    else{
                        canvas.drawText("Score: " + String.valueOf(-mLevelIndicator), width / 3, height / 3, paint);
                    }
                } else {
                    canvas.drawText("Score: " + String.valueOf(mScore), width / 3, height / 3, paint);
                }

            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }


    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }


    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println(String.valueOf(playing));

        if(playing==false){
            System.out.println(String.valueOf(mScore));
            Intent resultIntent = new Intent();
            if(mLevel.Continous==false) {
                resultIntent.putExtra("score", mScore);
            }
            else{
                resultIntent.putExtra("score", -mLevelIndicator);
            }
            ((Activity) mContext).setResult(Activity.RESULT_OK, resultIntent);
            ((Activity) mContext).finish();
        }

    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int getScore() {
        return mScore;
    }


    // The level architecture

    public void LevelInit() {

        if (mLevelIndicator < 0) {
            mLevelIndicator--;
            mProgressBar.set(Math.min(1-mLevelIndicator,10), 10);
            mLevel.random_new();
        }
        else {
            mProgressBar.set(mLevelIndicator - mStartLevel + 1, mLevelsNumber - mStartLevel + 1);
            switch (mLevelIndicator) {
                case 1:
                    // Binary indicator of colors in columns: {red, blue, green, yellow, purple}
                    // mLevel = new Level(mContext, width, height,
                    // the first column healthy        new boolean[]{false, true, false, false, false},
                    // the second column healthy       new boolean[]{false, true, false, false, false},
                    //the third column thealthy        new boolean[]{false, true, false, false, false},
                    // the first column sick        new boolean[]{false, true, false, false, false},
                    // the second column sick       new boolean[]{false, true, false, false, false},
                    //the third column sick        new boolean[]{false, true, false, false, false},
                    // what colors to we use?        new boolean[]{true, true, false, false, false},
                    // The solution rbgpy = new boolean[]{true, false, false, false, false});
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{true, true, false, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{true, true, false, false, false},
                            new boolean[]{true, false, false, false, false});
                    break;
                case 2:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, true, true, false, false},
                            new boolean[]{false, false, true, false, false},
                            new boolean[]{false, true, true, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{true, true, true, false, false},
                            new boolean[]{true, true, false, false, false},
                            new boolean[]{true, true, true, false, false},
                            new boolean[]{true, false, false, false, false});
                    break;
                case 3:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, true, true, false, true},
                            new boolean[]{true, false, true, false, false},
                            new boolean[]{false, true, true, false, true},
                            new boolean[]{false, false, true, true, false},
                            new boolean[]{false, true, true, true, true},
                            new boolean[]{true, true, false, true, false},
                            new boolean[]{true, true, true, true, true},
                            new boolean[]{false, false, false, true, false});
                    break;
                case 4:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{true, false, false/*one true here is not canonical*/, true, true},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{true, false, false, false, true},
                            new boolean[]{true, false, true, false, false},
                            new boolean[]{false, true, true, true, false},
                            new boolean[]{true, false, true, false, false},
                            new boolean[]{true, true, true, true, true},
                            new boolean[]{false, false, true, false, false});
                    break;
                case 5:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{false, false, false, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{false, false, false, false, false},
                            new boolean[]{true, true, false, false, false},
                            new boolean[]{false, true, false, false, false});
                    break;


                case 6:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, true, true, false, false},
                            new boolean[]{true, true, false, false, false},
                            new boolean[]{false, true, true, false, false},
                            new boolean[]{true, true, true, false, false},
                            new boolean[]{true, true, true, false, false},
                            new boolean[]{true, true, true, false, false},
                            new boolean[]{true, true, true, false, false},
                            new boolean[]{true, false, true, false, false});

                    break;
                case 7:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{false, true, true, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{true, true, true, false, false},
                            new boolean[]{true, false, true, false, false});
                    break;
                case 8:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{true, true, true, false, true},
                            new boolean[]{true, true, false, false, true},
                            new boolean[]{false, true, true, false, true},
                            new boolean[]{true, true, true, false, true},
                            new boolean[]{true, true, true, false, true},
                            new boolean[]{true, true, true, false, true},
                            new boolean[]{true, true, true, false, true},
                            new boolean[]{true, false, true, false, false});
                    break;
                case 9:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, false, false, false, false},
                            new boolean[]{false, false, false, false, true},
                            new boolean[]{false, false, false, true, false},
                            new boolean[]{false, false, true, false, false},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{true, true, true, false, true},
                            new boolean[]{true, true, true, false, false});
                    break;
                case 10:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{true, true, true, true, false},
                            new boolean[]{true, true, false, false, true},
                            new boolean[]{false, true, true, true, true},
                            new boolean[]{true, true, true, false, true},
                            new boolean[]{true, true, true, true, true},
                            new boolean[]{true, true, true, true, true},
                            new boolean[]{true, true, true, false, true},
                            new boolean[]{true, false, true, false, true});
                    break;
                case 11:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, false, true, false, true},
                            new boolean[]{true, true, false, false, true},
                            new boolean[]{false, true, false, true, false},
                            new boolean[]{true, false, true, false, true},
                            new boolean[]{true, true, false, true, false},
                            new boolean[]{true, false, false, true, true},
                            new boolean[]{true, true, true, true, true},
                            new boolean[]{true, false, true, true, false});
                    break;
                case 12:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, true, true, true, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{false, true, true, true, false},
                            new boolean[]{true, true, true, false, false},
                            new boolean[]{true, true, false, true, false},
                            new boolean[]{true, false, true, true, false},
                            new boolean[]{true, true, true, true, false},
                            new boolean[]{true, true, true, true, false});//(1and3)or(2and4)
                    break;
                case 13:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{true, false, false, true, false},
                            new boolean[]{false, true, true, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{false, true, false, true, true},
                            new boolean[]{true, false, true, false, true},
                            new boolean[]{true, true, false, true, false},
                            new boolean[]{true, true, true, true, true},
                            new boolean[]{true, true, false, false, true}); //(1and2)or5
                    break;
                case 14:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{true, true, false, false, false},
                            new boolean[]{false, true, false, false, true},
                            new boolean[]{false, true, true, true, false},
                            new boolean[]{false, false, true, false, true},
                            new boolean[]{false, true, false, true, false},
                            new boolean[]{true, true, true, true, false},
                            new boolean[]{false, true, false, true, false}); //not(2)or4
                    break;
                case 15:
                    mLevel = new Level(mContext, width, height, false,
                            new boolean[]{true, true, false, false, true},
                            new boolean[]{false, false, false, false, true},
                            new boolean[]{false, false, true, true, true},
                            new boolean[]{false, true, false, true, true},
                            new boolean[]{false, false, false, false, true},
                            new boolean[]{true, false, false, true, true},
                            new boolean[]{true, true, true, true, true},
                            new boolean[]{false, false, false, false, false});//no rule
                    break;

            }
        }


    }
}