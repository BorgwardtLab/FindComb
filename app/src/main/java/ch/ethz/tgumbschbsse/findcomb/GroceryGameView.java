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
 * Congratulations, you found the most important file
 * GameView takes care of the game mechanics.
 * its most important objects are: One Level (Layout) and one progress bar
 * Game view has two modes depending on the Continous contidtion of the Level
 */


public class GroceryGameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    Activity mact;

    // The layouts
    private GroceryLevel mLevel;
    private Progress mProgressBar;



    // Mechanics
    public int mLevelIndicator;
    public int mStartLevel;
    public int mScore;
    private long mTimestamp;
    private long mpm;
    private int deviation;


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
    boolean sound_plyaed;

    public GroceryGameView(Context context, Activity act) {
        super(context);
        mact = act;

        // The visuals
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;


        soundfinal = MediaPlayer.create(context,R.raw.fanfare);
        sound_plyaed = false;

        //init mechanics
        mScore = 60; //The player has three minutes
        mLevelIndicator = 1;
        mTimestamp = System.currentTimeMillis();
        mStartLevel = mLevelIndicator;

        mProgressBar = new Progress(5, width,height,context);
        mLevel = new GroceryLevel(context,width,height,new int[]{1,2,3},new int[]{1,2,3},new int[]{1,2,3},new int[]{1,2,3},new int[]{1,2,3},new int[]{1,2,3});


        //initializing drawing objects
        surfaceHolder = getHolder();
        mContext = context;


        LevelInit();

        //Stuff that we might need later
        mPlayer = new Point(1500, 300);
        playing = true;


    }

    public void reset(int start, int end, int score){
        mScore = score; //The player has two minutes
        mLevelIndicator = start;
        mTimestamp = System.currentTimeMillis();
        mStartLevel = mLevelIndicator;
        playing = true;
        LevelInit();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mScore > 0) {
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
        mProgressBar.set(10, 10, (mScore) * (1000/(width / 12)));


        if(mScore * (1000/(width / 12)) < width/12){
            mProgressBar.lower();
        }
        else if(mScore * (1000/(width / 12)) > width/12){
            mProgressBar.upper();
        }


        if (mScore < 0) {
            playing = false;
        }

        if (mScore > 0) {

            if ((System.currentTimeMillis() - mTimestamp) > 1000) {
                mScore--;
                mTimestamp = System.currentTimeMillis();
            }


            if (mLevel.clicked == true) {
                //Evaluate configuration
                mLevel.clicked = false;
                mLevel.compute_score();
                deviation = mLevel.mscore;
                if(deviation>=0){
                    mProgressBar.soundright.start();
                }
                else{
                    mProgressBar.soundwrong.start();
                }
                mpm = System.currentTimeMillis();
                mScore += deviation;
                LevelInit();

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
            if (mScore > 0) {
                mProgressBar.draw(canvas);

                Paint paintb = new Paint();
                paintb.setColor(Color.parseColor("#0099cc"));

                canvas.drawRect(new Rect(19*width/20,3*height/20,15*width/20, 3*height/5),paintb);

                paintb.setColor(Color.BLACK);
                paintb.setTextSize(width/15);
                canvas.drawText("Go!",16*width/20, 8*height/20,paintb);

                canvas.drawText(String.valueOf(mLevelIndicator), 27 * width / 30, height / 10, paint);



                if(System.currentTimeMillis() - mpm < 4000){
                    if(deviation>0){
                        paint.setColor(Color.GREEN);
                    }
                    else{
                        paint.setColor(Color.RED);
                    }
                    canvas.drawText(String.valueOf(deviation), 24 * width / 30, height / 10, paint);
                }

                mLevel.draw(canvas);
                //Unlocking the canvas

            } else {
                paint.setTextSize(width/20);
                if (mScore <= 0) {
                    canvas.drawText("Score: " + String.valueOf(mLevelIndicator), width / 3, height / 3, paint);
                    if(sound_plyaed == false) {
                        soundfinal.start();
                        mProgressBar.soundtick.stop();
                        sound_plyaed = true;
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
            mProgressBar.upper();
            System.out.println(String.valueOf(-1*mLevelIndicator));
            Intent resultIntent = new Intent();
            int result = mLevelIndicator * -1;
            resultIntent.putExtra("score", result);
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


    // The level architecture

    public void LevelInit() {

        if (mLevelIndicator <= 0) {
            mLevelIndicator--;
            //mProgressBar.set(10, 10, (mScore)*(100/(width/12)));
            mLevel.random_new();
        }
        else {
            mLevelIndicator++;
            mLevel.random_new();
        }


    }
}