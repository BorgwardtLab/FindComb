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
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;
import android.text.TextPaint;
import android.text.StaticLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.Double.min;

/**
 * Created by tgumbsch on 8/10/17.
 */


public class GroceryTutorial extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    Activity mact;
    Context mcontext;

    public int Level; //For different tutorials
    private Picture h1;

    private Progress mProgress;
    private int mcounter;
    private int[] x_spacing2, y_spacing2, x_spacing3, y_spacing3;

    private Arrow marrow, m2arrow, m3arrow;
    private int mstage;


    public GroceryItem mc11,mc12, mc13;
    public GroceryItem mc21;
    public GroceryItem mc31, mc32;
    public GroceryItem mc41, mc42, mc43;
    public GroceryItem mc51, mc52, mc53;
    public GroceryItem[] items;




    //These objects will be used for drawing
    //private Paint paint;
    private Canvas canvas;
    private int width;
    private int height;
    private SurfaceHolder surfaceHolder;
    private Context mContext;

    public GroceryTutorial(Context context, Activity act) {
        super(context);
        mact = act;
        mcontext = context;

        // The visuals
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;


        //initializing drawing objects
        surfaceHolder = getHolder();
        mContext = context;
        mstage = 0;


        x_spacing3 = new int[] {2*width/13, 2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13,2*width/13};
        y_spacing3 = new int[] {3*height/80,4*height/80,5*height/80,6*height/80,7*height/80,8*height/80,9*height/80,10*height/80,11*height/80,12*height/80,13*height/80,14*height/80,15*height/80,16*height/80,17*height/80,18*height/80,19*height/80,20*height/80,21*height/80,22*height/80,23*height/80,24*height/80,25*height/80,26*height/80,27*height/80,28*height/80,29*height/80,30*height/80,31*height/80,32*height/80,33*height/80,34*height/80,35*height/80,36*height/80,37*height/80,38*height/80,39*height/80,40*height/80,41*height/80,42*height/80,43*height/80,44*height/80,45*height/80,46*height/80,47*height/80};


        marrow = new Arrow(x_spacing3, y_spacing3, 3, 0.8, 1, context, true);

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

        playing = false;


        double scale = 0.05;
        DisplayMetrics mmetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) mcontext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(mmetrics);

        float yInches= mmetrics.heightPixels/mmetrics.ydpi;
        float xInches= mmetrics.widthPixels/mmetrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        if (diagonalInches>=6.5){
            scale = scale*2;
        }

        int x = 0;

            mc11 = new GroceryItem((x+3)*width/10, 3*height/23, scale, mcontext,R.drawable.cake);

            mc21 = new GroceryItem((x+3)*width/10, 6*height/23, scale, mcontext,R.drawable.bananas_multiple);

            mc31 = new GroceryItem((x+3)*width/10, 9*height/23, scale, mcontext, R.drawable.bananas_multiple); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);

            mc41 =  new GroceryItem((x+3)*width/10, 12*height/23, scale, mcontext, R.drawable.onions);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);

            mc51 = new GroceryItem((x+3)*width/10, 15*height/23, scale, mcontext, R.drawable.bananas_multiple);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);


        x += 1;
            mc12 = new GroceryItem((x+3)*width/10, 3*height/23, scale, mcontext,R.drawable.fish_final);

            mc32 = new GroceryItem((x+3)*width/10, 9*height/23, scale, mcontext, R.drawable.waterbottle); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);

            mc42 =  new GroceryItem((x+3)*width/10, 12*height/23, scale, mcontext, R.drawable.bananas_multiple);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);

            mc52 = new GroceryItem((x+3)*width/10, 15*height/23, scale, mcontext, R.drawable.fish_final);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);

        x += 1;
            mc13 = new GroceryItem((x+3)*width/10, 3*height/23, scale, mcontext, R.drawable.bananas_multiple);

            mc43 =  new GroceryItem((x+3)*width/10, 12*height/23, scale, mcontext, R.drawable.fish_final);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);

            mc53 = new GroceryItem((x+3)*width/10, 15*height/23, scale, mcontext, R.drawable.pear);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);

        items = new GroceryItem[] {mc11,mc21,mc31,mc41,mc51,mc12,mc32,mc42,mc52,mc13,mc43,mc53};


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

                        marrow = new Arrow(new int[]{width/5}, new int[]{15* height/20},1,0.8,100,mContext,false);



                        mc12.toggle();
                        mc13.toggle();
                        //mc21.toggle();
                        //mc31.toggle();
                        mc42.toggle();
                        mc43.toggle();
                        mc51.toggle();
                        mc52.toggle();
                    }
                }
                else if(mstage==4){
                    if (back) {
                        m2arrow = new Arrow(x_spacing2,y_spacing2,3,0.8,15, mContext,false);
                        m3arrow = new Arrow(x_spacing3,y_spacing3,3,0.8,2, mContext,false);
                        m2arrow.change_visibility();
                        marrow.change_visibility();
                        mProgress =  new Progress(10, width,height,mContext);
                        mcounter = 150;




                        mc12.toggle();
                        mc13.toggle();
                        //mc21.toggle();
                        //mc31.toggle();
                        mc42.toggle();
                        mc43.toggle();
                        mc51.toggle();
                        mc52.toggle();
                    } else {
                        m3arrow.change_visibility();
                        marrow.change_visibility();
                    }
                }
                else if(mstage==5){
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
                else if (mstage==6){
                    if (back) {
                        m2arrow = new Arrow(new int[]{24 * width / 30}, new int[] {height / 10},2,0.8,80,mContext,true);
                        m3arrow.change_visibility();
                    } else {
                        m2arrow.change_visibility();
                        marrow.change_visibility();
                    }
                    mcounter =100;
                    mProgress.set(10, 10, (mcounter) * (500 / (width / 12)));

                }
                else if(mstage>6){
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


        mc12.update();
        mc13.update();
        mc21.update();
        mc31.update();
        mc42.update();
        mc43.update();
        mc51.update();
        mc52.update();

        if(mstage==5) {
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
        if(mstage==6){
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
            canvas.drawColor(Color.WHITE);

            String text;
            if(mstage==0) {
                // Draw icon
                Bitmap mbpm;
                mbpm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.brain_icon);
                float scale = Math.min((float)((0.3*height)/mbpm.getHeight()),(float) ((0.3*width)/mbpm.getWidth()));
//                Rect originalRect = new Rect(0,0,mbpm.getWidth(),mbpm.getHeight());
//                Rect canvasRect = new Rect(2*width/5, 20, 2*width/3, height/3-20);
                mbpm = Bitmap.createScaledBitmap(mbpm, (int) (mbpm.getWidth()*scale), (int) (mbpm.getHeight()*scale), false);
                int x = width/2 - mbpm.getWidth()/2;
                int y = 20;
                canvas.drawBitmap(mbpm, x, y,null);
                // Text
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
                //h1.draw(canvas);
                text = getContext().getString(R.string.GTut1);
                canvas.drawText(text, width / 20, 9 * height / 12, paint);


                paint.setTextSize(height/23);
                canvas.drawText(mcontext.getString(R.string.C1),2*width/40, 2*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C2),2*width/40, 5*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C3),2*width/40, 8*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C4),2*width/40, 11*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C5),2*width/40, 14*height/23,paint);


                canvas.drawRect(new Rect(width/40, 3*height/23, 5*width/7, 3*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 6*height/23, 5*width/7, 6*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 9*height/23, 5*width/7, 9*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 12*height/23, 5*width/7, 12*height/23+2), paint);

                marrow.draw(canvas);
            }
            else if(mstage == 2) {
                //h1.draw(canvas);
                text = getContext().getString(R.string.GTut2);
                canvas.drawText(text, width / 20, 9 * height / 12, paint);

                for(GroceryItem gi: items){
                    gi.draw(canvas);
                }


                paint.setTextSize(height/23);
                canvas.drawText(mcontext.getString(R.string.C1),2*width/40, 2*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C2),2*width/40, 5*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C3),2*width/40, 8*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C4),2*width/40, 11*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C5),2*width/40, 14*height/23,paint);


                canvas.drawRect(new Rect(width/40, 3*height/23, 5*width/7, 3*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 6*height/23, 5*width/7, 6*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 9*height/23, 5*width/7, 9*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 12*height/23, 5*width/7, 12*height/23+2), paint);

                marrow.draw(canvas);
            }
            else if(mstage ==3) {

                text = getContext().getString(R.string.GTut3);
                canvas.drawText(text, width / 20, 9 * height / 12, paint);

                for(GroceryItem gi: items){
                    gi.draw(canvas);
                }


                paint.setTextSize(height/23);
                canvas.drawText(mcontext.getString(R.string.C1),2*width/40, 2*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C2),2*width/40, 5*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C3),2*width/40, 8*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C4),2*width/40, 11*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C5),2*width/40, 14*height/23,paint);


                canvas.drawRect(new Rect(width/40, 3*height/23, 5*width/7, 3*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 6*height/23, 5*width/7, 6*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 9*height/23, 5*width/7, 9*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 12*height/23, 5*width/7, 12*height/23+2), paint);

                marrow.draw(canvas);
            }
            else if(mstage ==3) {
                text = getContext().getString(R.string.GTut324);
                canvas.drawText(text, width / 20, 4 * height / 12, paint);
            }
            else if(mstage == 4){
                text = getContext().getString(R.string.GTut324);
                canvas.drawText(text, width / 20, 4 * height / 12, paint);

                text = getContext().getString(R.string.GTut325);
                canvas.drawText(text, width / 20, 6 * height / 12, paint);
            }
            else if(mstage == 5){
                text = getContext().getString(R.string.Tut4);
                canvas.drawText(text, width/20, 4 * height / 12, paint);
                mProgress.draw(canvas);
                marrow.draw(canvas);
            }
            else if (mstage == 6){
                mProgress.draw(canvas);
                text = getContext().getString(R.string.Tut5);
                canvas.drawText(text, width/20, 17 * height / 24, paint);

                for(GroceryItem gi: items){
                    gi.draw(canvas);
                }


                paint.setTextSize(height/23);
                canvas.drawText(mcontext.getString(R.string.C1),2*width/40, 2*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C2),2*width/40, 5*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C3),2*width/40, 8*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C4),2*width/40, 11*height/23,paint);
                canvas.drawText(mcontext.getString(R.string.C5),2*width/40, 14*height/23,paint);


                canvas.drawRect(new Rect(width/40, 3*height/23, 5*width/7, 3*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 6*height/23, 5*width/7, 6*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 9*height/23, 5*width/7, 9*height/23+2), paint);
                canvas.drawRect(new Rect(width/40, 12*height/23, 5*width/7, 12*height/23+2), paint);
                //There are points for configruations
                if(mcounter > 0 ) {
                    Paint gpaint = new Paint();
                    gpaint.setTextSize(width/30);
                    gpaint.setColor(Color.GREEN);
                    canvas.drawText("20", 24 * width / 30, height / 10, gpaint);
                    m2arrow.draw(canvas);
                }
                else{
                    mProgress.soundright.start();
                    m3arrow.draw(canvas);
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