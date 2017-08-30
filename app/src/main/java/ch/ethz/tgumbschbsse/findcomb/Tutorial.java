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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
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


public class Tutorial extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    Activity mact;

    public Level mLevel;
    public int Level;
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

    public Tutorial(Context context, Activity act) {
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
                new boolean[]{false, true, false, false, false},
                new boolean[]{false, true, false, false, false},
                new boolean[]{false, true, false, false, false},
                new boolean[]{true, false, false, false, false},
                new boolean[]{true, true, false, false, false},
                new boolean[]{true, false, false, false, false},
                new boolean[]{true, true, false, false, false});
        mLevel.Tut = 1;
        Level = 1;

        //Stuff that we might need later
        mPlayer = new Point(1500, 300);
        playing = true;


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mstage++;
                if(mstage == 1) {
                    if(Level == 1) {
                        mLevel = new Level(mContext, width, height,
                                new boolean[]{false, true, false, false, false},
                                new boolean[]{false, true, false, false, false},
                                new boolean[]{false, true, false, false, false},
                                new boolean[]{true, false, false, false, false},
                                new boolean[]{true, true, false, false, false},
                                new boolean[]{true, false, false, false, false},
                                new boolean[]{true, true, false, false, false});
                    }
                    else if(Level == 2){
                        mLevel = new Level(mContext, width, height,
                                new boolean[]{false, true, true, false, false},
                                new boolean[]{true, true, false, false, false},
                                new boolean[]{false, true, true, false, false},
                                new boolean[]{true, false, true, false, false},
                                new boolean[]{true, true, true, false, false},
                                new boolean[]{true, false, true, false, false},
                                new boolean[]{true, true, true, false, false});
                    }
                    else{
                        mLevel = new Level(mContext, width, height,
                                new boolean[]{false, false, true, false, true},
                                new boolean[]{true, true, false, false, true},
                                new boolean[]{false, true, false, true, false},
                                new boolean[]{true, false, true, false, true},
                                new boolean[]{true, true, false, true, false},
                                new boolean[]{true, false, false, true, true},
                                new boolean[]{true, true, true, true, true});
                    }
                    mLevel.Tut = 1;
                }
                else if (mstage == 2){
                    if(Level==2){
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
                    else if(Level==3){
                        mLevel.cs13.clicked = true;
                        mLevel.cs24.clicked = true;
                        mLevel.cs34.clicked = true;
                        mLevel.ch13.clicked = true;
                        mLevel.ch34.clicked = true;
                    }
                }
                else if (mstage == 3) {
                    if(Level==1) {
                        mLevel.cs11.clicked = true;
                        mLevel.cs21.clicked = true;
                        mLevel.cs31.clicked = true;
                    }
                    else if(Level==2){
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
                    else{
                        mLevel.cs11.clicked = true;
                        mLevel.cs21.clicked = true;
                        mLevel.cs31.clicked = true;
                        mLevel.ch21.clicked = true;

                    }
                }
                if (mstage > 3) {
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

            if(mstage==0){
                if(Level==1) {
                    text = "Level 1";
                }
                else if(Level ==2){
                    text = "Level 2";
                }
                else{
                    text = "Level 3";
                }
                paint.setTextSize(width/20);
                canvas.drawText(text, width / 3, height / 3, paint);

            }
            else if(mstage==1) {
                if(Level==1) {
                    h1.draw(canvas);
                    text = getContext().getString(R.string.Tut1);
                    canvas.drawText(text, width / 20, 9 * height / 12, paint);
                }
                else if(Level ==2){
                    mLevel.draw(canvas);
                    text = getContext().getString(R.string.Tut21);
                    canvas.drawText(text, width / 20, 9 * height / 12, paint);
                }
                else{
                    mLevel.draw(canvas);
                    text = getContext().getString(R.string.Tut31);
                    canvas.drawText(text, width / 20, 9 * height / 12, paint);
                }
            }
            else if(mstage == 2) {
                if(Level==1) {
                    mLevel.draw(canvas);
                    text = getContext().getString(R.string.Tut2);
                    canvas.drawText(text,  width / 20, 9 * height / 12, paint);
                }
                else if(Level ==2){
                    mLevel.draw(canvas);
                    text = getContext().getString(R.string.Tut22);
                    canvas.drawText(text,  width / 20, 9 * height / 12, paint);
                }
                else{
                    mLevel.draw(canvas);
                    text = getContext().getString(R.string.Tut32);
                    canvas.drawText(text,  width / 20, 9 * height / 12, paint);
                }
            }
            else if(mstage ==3) {
                if(Level==1) {
                    mLevel.draw(canvas);
                    text = getContext().getString(R.string.Tut3);
                    canvas.drawText(text, width/20, 9 * height / 12, paint);
                }
                else if(Level ==2){
                    mLevel.draw(canvas);
                    text = getContext().getString(R.string.Tut23);
                    canvas.drawText(text, width/20, 9 * height / 12, paint);
                }
                else{
                    mLevel.draw(canvas);
                    text = getContext().getString(R.string.Tut33);
                    canvas.drawText(text,  width / 20, 9 * height / 12, paint);
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