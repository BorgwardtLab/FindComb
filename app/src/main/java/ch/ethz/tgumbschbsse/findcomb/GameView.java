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

        int[] red = { Color.rgb(200,0,0),Color.rgb(255,0,0)};
        int[] blue = { Color.rgb(0,100,150),Color.rgb(0,112,192)};
        int[] green = { Color.rgb(0,140,50),Color.rgb(0,176,80)};
        int[] purple = { Color.rgb(200,0,0),Color.rgb(112, 48, 160)};
        int[] yellow = { Color.rgb(200,200,0),Color.rgb(255,255,0)};

        // The visuals
        mrectRed = new Rectangle((new Rect(1400,100,1800,300)),red);
        mrectBlue = new Rectangle((new Rect(1400,350,1800,550)),blue);
        mrectGreen = new Rectangle((new Rect(1400,600,1800,800)),green);
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
                mLevel.checkClicked(mPlayer);
        }


        return true;
        //return super.onTouchEvent(event);
    }


    private void update() {

        if(mLevel.clicked == true){
            boolean[] rbgpyPlayer = {mrectRed.clicked,mrectBlue.clicked,mrectGreen.clicked,false,false};
            mrectBlue.processClicked();
            mrectRed.processClicked();
            mrectGreen.processClicked();
            if (Arrays.equals(rbgpy,rbgpyPlayer)){
                mScore++;
            }
            else{
                mScore--;
            }
            mLevelIndicator = 5;
            LevelInit();
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
            default:
                playing = false;
        }

    }
}