package ch.ethz.tgumbschbsse.findcomb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by tgumbsch on 8/28/17.
 */

public class TutorialComb extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    Activity mact;

    private Level mLevel;
    private Picture h1;

    // In case moving is an option
    private Point mPlayer;

    private int mstage;



    //These objects will be used for drawing
    //private Paint paint;
    private Canvas canvas;
    private int width;
    private int height;
    private SurfaceHolder surfaceHolder;
    private Context mContext;

    public TutorialComb(Context context, Activity act) {
        super(context);
        mact = act;

        // The visuals
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;


        //initializing drawing objects
        surfaceHolder = getHolder();
        mContext = context;
        mstage = 0;

        h1 = new Picture(R.drawable.leftside, mContext,0,0,3*height/7,2*height/3);

        mLevel = new Level(mContext, width, height,
                new boolean[]{false, true, true, false, false},
                new boolean[]{true, true, false, false, false},
                new boolean[]{false, true, true, false, false},
                new boolean[]{true, false, true, false, false},
                new boolean[]{true, true, true, false, false},
                new boolean[]{true, false, true, false, false},
                new boolean[]{true, true, true, false, false});
        mLevel.Tut = 1;


        //Stuff that we might need later
        mPlayer = new Point(1500, 300);
        playing = true;


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mstage++;
                if (mstage == 1) {
                    mLevel.cs11.clicked = true;
                    mLevel.cs13.clicked = true;
                    mLevel.cs21.clicked = true;
                    mLevel.cs23.clicked = true;
                    mLevel.cs31.clicked = true;
                    mLevel.cs33.clicked = true;
                    mLevel.ch13.clicked = true;
                    mLevel.ch33.clicked = true;
                    mLevel.ch21.clicked = true;
                }
                if (mstage == 2) {
                    mLevel = new Level(mContext, width, height,
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{false, true, false, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{false, true, true, false, false},
                            new boolean[]{true, false, false, false, false},
                            new boolean[]{true, true, true, false, false});
                    mLevel.cs11.clicked = true;
                    mLevel.cs13.clicked = true;
                    mLevel.cs21.clicked = true;
                    mLevel.cs23.clicked = true;
                    mLevel.cs31.clicked = true;
                    mLevel.cs33.clicked = true;
                    mLevel.ch13.clicked = true;
                    mLevel.ch33.clicked = true;
                    mLevel.ch21.clicked = true;
                }
                if (mstage > 2) {
                    playing = false;
                }

                //return super.onTouchEvent(event);
        }
        return true;
    }


    private void update() {
    }

    private void draw() {
        //checking if surface is valid
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            Paint paint = new Paint();
            paint.setTextSize(width/30);
            //paint.setColor(Color.WHITE);
            paint.setColor(Color.BLACK);
            //drawing a background color for canvas
            //canvas.drawColor(Color.parseColor("#0099cc"));
            canvas.drawColor(Color.WHITE);


            String text;


            if(mstage==0) {
                mLevel.draw(canvas);
                text = getContext().getString(R.string.Tut21);
                canvas.drawText(text, width / 10, 9 * height / 12, paint);
            }

            if(mstage == 1) {
                mLevel.draw(canvas);
                text = getContext().getString(R.string.Tut22);
                canvas.drawText(text,  width / 10, 9 * height / 12, paint);
            }



            if(mstage ==2) {
                mLevel.draw(canvas);
                text = getContext().getString(R.string.Tut23);
                canvas.drawText(text, width/10, 9 * height / 12, paint);
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

        if (playing != true) {
            Intent resultIntent = new Intent();
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


}