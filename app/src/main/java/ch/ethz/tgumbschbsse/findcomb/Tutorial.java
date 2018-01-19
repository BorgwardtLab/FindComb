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
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import android.text.TextPaint;
import android.text.StaticLayout;

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
    public int Level; //For different tutorials
    private Picture h1;

    private Progress mProgress;
    private int mcounter;
    private int[] x_spacing2, y_spacing2, x_spacing3, y_spacing3;

    private Arrow marrow, m2arrow, m3arrow;

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
        marrow = new Arrow(new int[]{2*width/13,width*2,2*width/13,width*2}, new int[]{3*height/20,width*2, height/2, width*2}, 3, 0.8, 15, context, true);

        x_spacing2 = new int[] {2*width/13, 2*width/13,5*width/13, 8*width/13,
                2*width/13, 2*width/13,5*width/13,
                2*width/13, 2*width/13,5*width/13, 7*width/13,
                2*width/13, 2*width/13,4*width/13,
                2*width/13, 2*width/13,4*width/13,5*width/13, 6*width/13,
                2*width/13, 2*width/13,4*width/13};
        y_spacing2 = new int[] {height/20,height/20,height/20,height/20,
                3*height/20,3*height/20,3*height/20,
                5*height/20,5*height/20,5*height/20,5*height/20,
                8*height/20,8*height/20,8*height/20,
                10*height/20,10*height/20,10*height/20,10*height/20,10*height/20,
                12*height/20,12*height/20,12*height/20,};
        m2arrow = new Arrow(x_spacing2,y_spacing2,3,0.8,15, mContext,false);

        x_spacing3 = new int[] {4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13,4*width/13};
        y_spacing3 = new int[] {32*height/80,33*height/80,34*height/80,35*height/80,36*height/80,37*height/80,38*height/80,39*height/80,40*height/80,41*height/80,42*height/80,43*height/80,44*height/80,45*height/80,46*height/80,47*height/80};

        m3arrow = new Arrow(x_spacing3,y_spacing3,3,0.8,2, mContext,false);


        mProgress = new Progress(10, width,height,mContext);
        mcounter = 150;

        h1 = new Picture(R.drawable.leftside, mContext,0,0,3*height/7,2*height/3);

        mLevel = new Level(mContext, width, height,false,
                new boolean[]{false, true, false, true, false},
                new boolean[]{false, true, false, false, false},
                new boolean[]{false, true, false, false, true},
                new boolean[]{true, false, false, false, false},
                new boolean[]{true, true, true, false, false},
                new boolean[]{true, false, false, false, false},
                new boolean[]{true, true, false, false, false}, new boolean[]{true, true, false, false, false});
        mLevel.Tut = 1;

        //Stuff that we might need later
        mPlayer = new Point(1500, 300);
        playing = false;


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                boolean back=false;
                if(event.getX() < 0.2* width && mstage > 0) {
                    mstage--;
                    back=true;
                } else {
                    mstage++;
                }
//                if(mstage == 0){
//                    playing = false;
//                }
                if(mstage == 1) {
                    playing = true;
                    if(back){
                        marrow.change_visibility();
                        m2arrow.change_visibility();
                    }
                }
                else if (mstage == 2){
                    if(back){
                        m3arrow.change_visibility();
                        m2arrow.change_visibility();
                        mLevel.cs11.clicked = false;
                        mLevel.cs21.clicked = false;
                        mLevel.cs31.clicked = false;
                        marrow = new Arrow(new int[]{2*width/13,width*2,2*width/13,width*2}, new int[]{3*height/20,width*2, height/2, width*2}, 3, 0.8, 15, mContext, false);
                    } else {
                        marrow.change_visibility();
                        m2arrow.change_visibility();
                    }
                }
                else if (mstage == 3) {
                    if(back) {
                        m2arrow = new Arrow(x_spacing2,y_spacing2,3,0.8,15, mContext,false);
                        m3arrow = new Arrow(x_spacing3,y_spacing3,3,0.8,2, mContext,false);
                        m3arrow.change_visibility();
                        marrow.change_visibility();
                    } else {
                        m3arrow.change_visibility();
                        m2arrow.change_visibility();
                        mLevel.cs11.clicked = true;
                        mLevel.cs21.clicked = true;
                        mLevel.cs31.clicked = true;

                        marrow = new Arrow(new int[]{width/5}, new int[]{15* height/20},1,0.8,100,mContext,false);
                    }
                }
                else if(mstage==4){
                    if (back) {
                        m2arrow.change_visibility();
                        marrow.change_visibility();
                        mProgress =  new Progress(10, width,height,mContext);
                        mcounter = 150;
                    } else {
                        m3arrow.change_visibility();
                        marrow.change_visibility();
                        m2arrow = new Arrow(new int[]{24 * width / 30}, new int[] {height / 10},2,0.8,80,mContext,false);
                        m3arrow = new Arrow(new int[]{10 * width / 30}, new int[] {8*height / 10},0,0.8,80,mContext,false);
                    }

                }
                else if (mstage==5){
                    if (back) {
                        m2arrow = new Arrow(new int[]{24 * width / 30}, new int[] {height / 10},2,0.8,80,mContext,true);
                        m3arrow.change_visibility();
                        mLevel.cs11.mvisible = true;
                    } else {
                        m2arrow.change_visibility();
                        marrow.change_visibility();
                    }
                    mcounter =100;
                    mProgress.set(10, 10, (mcounter) * (500 / (width / 12)));

                }
                else if (mstage==6){
                    m2arrow = new Arrow(new int[]{4*width/13,24 * width / 30}, new int[]{8*height/20,height / 10},2,0.8,60,mContext,true);
                    m3arrow.change_visibility();
                    mLevel.cs11.mvisible = false;
                    mcounter =100;
                    mProgress.set(10, 10, (mcounter) * (500 / (width / 12)));
                }
                else if(mstage>8){
                    System.out.println(String.valueOf(playing));
                    playing = false;
                }

            //return super.onTouchEvent(event);
        }
        return true;
    }


    private void update() {
        marrow.update();
        m2arrow.update();
        m3arrow.update();

        if(mstage==4) {
            mcounter--;
            if (mcounter>0) {
                mProgress.set(10, 10, (mcounter) * (500 / (width / 12)));
            }


            if(mcounter == 0){
                mProgress.upper();

                mstage++;

                m2arrow.change_visibility();
                marrow.change_visibility();
                mcounter =100;
                mProgress.set(10, 10, (mcounter) * (500 / (width / 12)));
            }
            else if(mcounter * (500/(width / 12)) < width/12){
                mProgress.lower();
            }
        }
        if(mstage==5){
            mProgress.soundwrong.stop();
            if(mcounter >0) {
                mcounter--;
                if (mcounter == 0) {
                    mProgress.set(10, 10, (150) * (500 / (width / 12)));
                    mProgress.soundright.start();
                    m2arrow.change_visibility();
                    m3arrow.change_visibility();
                }
            }
        }
        if(mstage==6){
            mProgress.soundwrong.stop();
            if(mcounter >0) {
                mcounter--;
                if (mcounter == 0) {
                    m2arrow.change_visibility();
                    m3arrow.change_visibility();
                    mProgress.set(10, 10, (130) * (500 / (width / 12)));
                    mProgress.soundright.start();
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
            //paint.setColor(Color.WHITE);
            paint.setColor(Color.BLACK);
            paint.setAntiAlias(true);
            //drawing a background color for canvas
            //canvas.drawColor(Color.parseColor("#0099cc"));
            canvas.drawColor(Color.WHITE);

            String text;
            if(mstage==0) {
                TextPaint mTextPaint=new TextPaint();
                mTextPaint.setTextSize(width/30);
                mTextPaint.setColor(Color.BLACK);
                mTextPaint.setAntiAlias(true);
                text = getContext().getString(R.string.Tut0);
                StaticLayout mTextLayout = new StaticLayout(text, mTextPaint, canvas.getWidth()-50, Layout.Alignment.ALIGN_CENTER, 1.0f, 10.0f, true);
                canvas.translate(0,height/3);
                mTextLayout.draw(canvas);
                text = getContext().getString(R.string.Tut01);
                canvas.translate(0,height/3);
                mTextLayout = new StaticLayout(text, mTextPaint, canvas.getWidth()-50, Layout.Alignment.ALIGN_CENTER, 1.0f, 10.0f, true);
                mTextLayout.draw(canvas);

            }
            if(mstage==1) {
                h1.draw(canvas);
                text = getContext().getString(R.string.Tut1);
                canvas.drawText(text, width / 20, 9 * height / 12, paint);

                marrow.draw(canvas);
            }
            else if(mstage == 2) {
                mLevel.draw(canvas);
                text = getContext().getString(R.string.Tut2);
                canvas.drawText(text,  width / 20, 9 * height / 12, paint);
                m2arrow.draw(canvas);
            }
            else if(mstage ==3) {
                mLevel.draw(canvas);
                text = getContext().getString(R.string.Tut3);
                canvas.drawText(text, width/20, 9 * height / 12, paint);
                m3arrow.draw(canvas);
            }
            else if(mstage ==3) {
                mLevel.draw(canvas);
                text = getContext().getString(R.string.Tut3);
                canvas.drawText(text, width/20, 9 * height / 12, paint);
            }
            else if(mstage == 4){
                text = getContext().getString(R.string.Tut4);
                canvas.drawText(text, width/20, 4 * height / 12, paint);
                mProgress.draw(canvas);
                marrow.draw(canvas);
            }
            else if (mstage == 5){
                mLevel.draw(canvas);
                mProgress.draw(canvas);
                text = getContext().getString(R.string.Tut5);
                canvas.drawText(text, width/20, 17 * height / 24, paint);
                if(mcounter > 0 ) {
                    Paint gpaint = new Paint();
                    gpaint.setTextSize(width/30);
                    gpaint.setColor(Color.GREEN);
                    canvas.drawText("20", 24 * width / 30, height / 10, gpaint);
                    m2arrow.draw(canvas);
                }
                else{
                    m3arrow.draw(canvas);
                }
                //There are points for configruations
            }
            else if(mstage == 6){
                mLevel.draw(canvas);
                mProgress.draw(canvas);
                text = getContext().getString(R.string.Tut6);
                canvas.drawText(text, width/20, 17 * height / 24, paint);
                if(mcounter > 0 ) {
                    Paint gpaint = new Paint();
                    gpaint.setTextSize(width/30);
                    gpaint.setColor(Color.GREEN);
                    canvas.drawText("10", 24 * width / 30, height / 10, gpaint);
                    m2arrow.draw(canvas);
                }
                else{
                    m3arrow.draw(canvas);
                }
                // There are fewer points for imperfect configurations
            }
            else if(mstage == 7){
                text = getContext().getString(R.string.Tut324);
                canvas.drawText(text, width / 20, 4 * height / 12, paint);
            }
            else if(mstage == 8) {
                text = getContext().getString(R.string.Tut324);
                canvas.drawText(text, width / 20, 4 * height / 12, paint);

                text = getContext().getString(R.string.Tut325);
                canvas.drawText(text, width / 20, 6 * height / 12, paint);
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
            System.out.println(String.valueOf(playing));
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