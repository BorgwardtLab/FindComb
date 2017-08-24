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


        //Stuff that we might need later
        mPlayer = new Point(1500, 300);
        playing = true;


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mstage++;
        if(mstage>2) {
            playing = false;
        }

        return true;
        //return super.onTouchEvent(event);
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
            paint.setColor(Color.WHITE);
            //drawing a background color for canvas
            canvas.drawColor(Color.parseColor("#0099cc"));

            String text = getContext().getString(R.string.Tut1);
            canvas.drawText(text, width/10, 2*width/30, paint);
            if(mstage == 0){

                // Display pictures
            }

            if(mstage > 0) {
                text = getContext().getString(R.string.Tut2);
                canvas.drawText(text, width / 10, 7 * width / 60, paint);
            }
            if(mstage ==1){
                // Display buttons
            }



            if(mstage>1) {
                text = getContext().getString(R.string.Tut3);
                canvas.drawText(text, width/10, 5*width/30, paint);
            }
            if(mstage==2){
                //Display correct combination
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