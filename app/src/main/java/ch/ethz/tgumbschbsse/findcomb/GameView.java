package ch.ethz.tgumbschbsse.findcomb;

import android.content.Context;
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
    private Picture mLevel;
    private int mScore;

    // Mechanics
    private int[] rbgPlayer;
    private int[] rbgLevel;


    //These objects will be used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public GameView(Context context, int Score) {
        super(context);

        //Stuff that we might need later
        mPlayer = new Point(1500,300);

        // The visuals
        mrectRed = new Rectangle((new Rect(1400,100,1800,300)),Color.rgb(255,0,0));
        mrectBlue = new Rectangle((new Rect(1400,350,1800,550)),Color.rgb(0,112,192));
        mrectGreen = new Rectangle((new Rect(1400,600,1800,800)),Color.rgb(0,176,80));
        mLevel = new Picture(R.drawable.level1,context);
        mScore = Score;

        //The mechanics
        rbgPlayer = new int[] {0,0,0};
        rbgLevel = new int[] {1,0,0};


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
                mLevel.checkClicked(mPlayer);
        }


        return true;
        //return super.onTouchEvent(event);
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {

        if(mrectBlue.clicked == true){
            rbgPlayer[1] = rbgPlayer[1] + 1;
            mrectBlue.processClicked();
        }

        if(mrectRed.clicked == true){
            rbgPlayer[0] = rbgPlayer[0] + 1;
            mrectRed.processClicked();
        }

        if(mrectGreen.clicked == true){
            rbgPlayer[2] = rbgPlayer[2] +  1;
            mrectGreen.processClicked();
        }

        if(mLevel.clicked == true){
            playing = false;
            if (Arrays.equals(rbgLevel,rbgPlayer)){
                mScore++;
            }
            else{
                mScore--;
            }
        }
    }

    private void draw() {
        //checking if surface is valid
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            //drawing a background color for canvas
            canvas.drawColor(Color.WHITE);
            //Drawing the player

            mLevel.draw(canvas);
            mrectRed.draw(canvas);
            mrectBlue.draw(canvas);
            mrectGreen.draw(canvas);
            Paint paint =  new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.BLACK);
            canvas.drawText(String.valueOf(mScore), 600, 700, paint);

            //Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
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
}