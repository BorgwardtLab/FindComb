package ch.ethz.tgumbschbsse.findcomb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgumbsch on 8/11/17.
 */


public class GroceryItem implements GameObject {
    //private List<Point> mpoints ;
    //private int mcolor;
    public boolean mclicked;
    private boolean mvisible;
    private Bitmap mbpm;
    private int[] mx, my;
    private int mspeed, mindex,mcount;

    public GroceryItem(int x, int y, double scale, Context context, int rindex){    //int x, int y, int length, double rotation,  int color, boolean visible){
        if(rindex != 0) {
            mbpm = BitmapFactory.decodeResource(context.getResources(), rindex);
            mbpm = scaleflip(mbpm,0,scale);
            mvisible = true;
        }
        else{
            mvisible=false;
        }
        mx = new int[]{x};
        my = new int[]{y};
        mindex = 0;
        mcount = 0;
        mspeed = 10;
        mclicked = false;
    }

    @Override
    public void draw(Canvas canvas) {
        if(mvisible == true) {
            int x, y;
            x = mx[mindex] - mbpm.getWidth();
            y = my[mindex] - mbpm.getHeight();
            canvas.drawBitmap(mbpm, x, y, null);
        }

    }

    public static Bitmap scaleflip(Bitmap bitmap, int type, double scale){
        Matrix matrix =new Matrix();
        if(type==1){
            matrix.preScale(-1.0f, 1.0f);
        }else if(type==2){
            matrix.preScale(1.0f, -1.0f);
        }
        else if(type==3){
            matrix.preScale(-1.0f, -1.0f);
            //matrix.preScale(-1.0f, 1.0f);
        }
        bitmap =  Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth()*scale), (int) (bitmap.getHeight()*scale), false);
    }

    @Override
    public void update() {
        mcount = mcount + 1;
        if(mcount > mspeed){
            mcount = 0;
            mindex = mindex + 1;
            if (mindex >= my.length){
                mindex = 0;
            }
        }

    }

    public void checkClicked(Point point){
    if(mvisible==true){
        int x = mx[0];
        int y = my[0];
        int dx = mbpm.getWidth();
        int dy = mbpm.getHeight();
        System.out.print(x);
        System.out.print(dx);
        System.out.print(point.x);
        if(point.x < x+dx && x > point.x && point.y > y-dy && y > point.y){
            if(mclicked == true){
                mclicked = false;
                mx = new int[] {mx[0]};
                my = new int[] {my[0]};
            }
            else{
                System.out.print("changed");
                mclicked = true;
                mx = new int[] {mx[0],mx[0],100000};
                my = new int[] {my[0],my[0],100000};
            }
        }
    }
        //Locate clicked
        //extend mx and my by coordinates off screen to enable blinking
    }


    public void processClicked(){


    }

    public void update(Point point){
    }
}