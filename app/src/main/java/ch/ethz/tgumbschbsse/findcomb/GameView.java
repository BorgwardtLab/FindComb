package ch.ethz.tgumbschbsse.findcomb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Arrays;

/**
 * Created by tgumbsch on 8/10/17.
 */
public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;

    // In case moving is an option
    private Point mPlayer;

    // Layout stuff
    private Rectangle mrectRed;
    private Rectangle mrectBlue;
    private Rectangle mrectGreen;
    private Rectangle mrectPurple;
    private Rectangle mrectYellow;
    private Picture mLevel;
    private int mScore;

    // Mechanics
    private boolean[] rbgpy;
    private int mLevelIndicator;


    //These objects will be used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Context mContext;

    public GameView(Context context) {
        super(context);

        //Stuff that we might need later
        mPlayer = new Point(1500,300);
        playing = true;

        int[] red = { Color.rgb(212,0,0),Color.rgb(255,0,0)};
        int[] blue = { Color.rgb(0,90,154),Color.rgb(0,112,192)};
        int[] green = { Color.rgb(0,141,64),Color.rgb(0,176,80)};
        int[] purple = { Color.rgb(90,38,128),Color.rgb(112, 48, 160)};
        int[] yellow = { Color.rgb(212,212,0),Color.rgb(255,255,0)};

        // The visuals
        mrectRed = new Rectangle((new Rect(100,800,300,1000)),red);
        mrectBlue = new Rectangle((new Rect(450,800,650,1000)),blue);
        mrectGreen = new Rectangle((new Rect(800,800,1000,1000)),green);
        mrectPurple = new Rectangle((new Rect(1150,800,1350,1000)),purple);
        mrectYellow = new Rectangle((new Rect(1500,800,1700,1000)),yellow);
        mScore = 0;

        mContext = context;
        mLevelIndicator = 0;

        LevelInit();


        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPlayer.set((int) event.getX(), (int) event.getY());
                mrectGreen.checkClicked(mPlayer);
                mrectRed.checkClicked(mPlayer);
                mrectBlue.checkClicked(mPlayer);
                mrectPurple.checkClicked(mPlayer);
                mrectYellow.checkClicked(mPlayer);
                mLevel.checkClicked(mPlayer);
        }


        return true;
        //return super.onTouchEvent(event);
    }


    private void update() {

        if(mLevel.clicked == true){
            //Evaluate configuration
            boolean[] rbgpyPlayer = {mrectRed.clicked, mrectBlue.clicked, mrectGreen.clicked, mrectPurple.clicked, mrectYellow.clicked};
            mrectBlue.processClicked();
            mrectRed.processClicked();
            mrectGreen.processClicked();
            mrectPurple.processClicked();
            mrectYellow.processClicked();
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.BLACK);
            if (Arrays.equals(rbgpy, rbgpyPlayer)) {
                mScore++;

            } else {
                mScore--;
                canvas.drawText("WRONG", 600, 700, paint);
            }

            //What happens next?
            if (mLevelIndicator < 5) {
                mLevelIndicator = 5;
                LevelInit();
            }
            else{
                playing = false;
            }
        }
    }

    private void draw() {
        //checking if surface is valid
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.BLACK);
            //drawing a background color for canvas
            canvas.drawColor(Color.WHITE);

            if(playing == true) {
                mLevel.draw(canvas);
                mrectRed.draw(canvas);
                mrectBlue.draw(canvas);
                mrectGreen.draw(canvas);
                mrectPurple.draw(canvas);
                mrectYellow.draw(canvas);
            }
            else{
                canvas.drawText("Score:" + String.valueOf(mScore), 600, 700, paint);
                Intent resultIntent = new Intent();
                control();
                control();
                control();
                resultIntent.putExtra("score",mScore);
                ((Activity) mContext).setResult(Activity.RESULT_OK, resultIntent);
                ((Activity) mContext).finish();
            }

            //Unlocking the canvas
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

    public int getScore(){
        return mScore;
    }






    // The level architecture

    public void LevelInit(){
        switch (mLevelIndicator){
            case 0:
                mLevel = new Picture(R.drawable.level1,mContext);
                rbgpy = new boolean[] {true,false,false,false,false};
                break;
            case 5:
                mLevel = new Picture(R.drawable.level5,mContext);
                rbgpy = new boolean[] {true,false,true,false,false};
                break;
        }

    }
}