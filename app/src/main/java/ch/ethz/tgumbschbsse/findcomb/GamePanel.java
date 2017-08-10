package ch.ethz.tgumbschbsse.findcomb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Arrays;

/**
 * Created by tgumbsch on 7/27/17.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    private MainThread thread;
    private Rect r = new Rect();

    private String mName;
    private Rectangle mrectRed;
    private Rectangle mrectBlue;
    private Rectangle mrectGreen;
    private Point mPlayer;
    private Picture mLevel;
    private int[] rbgPlayer;
    private int[] rbgLevel;

    public int result;

    public GamePanel(Context context, String name){
        super(context);


        mName = name;
        mrectRed = new Rectangle((new Rect(1400,100,1800,300)),Color.rgb(255,0,0));
        mrectBlue = new Rectangle((new Rect(1400,350,1800,550)),Color.rgb(0,112,192));
        mrectGreen = new Rectangle((new Rect(1400,600,1800,800)),Color.rgb(0,176,80));
        mPlayer = new Point(1500,300);
        mLevel = new Picture(R.drawable.level1,context);


        rbgPlayer = new int[] {0,0,0};
        rbgLevel = new int[] {1,0,0};

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(),this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new MainThread(getHolder(),this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while(true){
            try {
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {e.printStackTrace();}
            retry = false;
        }
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

    public void update(){

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
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

        // This is not working
        if(mLevel.clicked == true) {

            System.out.println("draw2");
            // mLevel.clicked = false;
            Paint paint =  new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.MAGENTA);
            if (Arrays.equals(rbgLevel,rbgPlayer)){
                canvas.drawText("WIN", 1200, 400, paint);
            }
            else{
                canvas.drawText("LOSE", 1200, 400, paint);
            }

        }
        else{
            mLevel.draw(canvas);
            mrectRed.draw(canvas);
            mrectBlue.draw(canvas);
            mrectGreen.draw(canvas);

            System.out.println("draw1");
        }
        //super.onDraw(canvas);
    }


}
