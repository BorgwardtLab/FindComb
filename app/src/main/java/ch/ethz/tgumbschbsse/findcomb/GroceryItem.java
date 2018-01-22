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
    public boolean mclicked,toggled;
    private boolean mvisible;
    private Bitmap mbpm;
    private int[] mx, my;
    private int minitialx, minitialy;
    private int mspeed, mindex,mcount;
    public int mgiindex;

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
        minitialx = x;
        minitialy = y;
        mindex = 0;
        mcount = 0;
        mspeed = 10;
        mclicked = false;
        toggled=false;
        mgiindex = rindex;
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

    public void toggle(){
        if(mclicked == true){
            mclicked = false;
            mx = new int[] {minitialx};
            my = new int[] {minitialy};
            mindex= 0;
        }
        else{
            mclicked = true;
            mx = new int[] {100000,minitialx,minitialx};
            my = new int[] {100000,minitialy,minitialy};
        }
    }

    public void checkClicked(Point point){
    if(mvisible==true){
        int x = minitialx;
        int y = minitialy;
        int dx = mbpm.getWidth();
        int dy = mbpm.getHeight();
        if(point.x < x+dx-100 && point.x > x-100 && point.y > y-dy && y > point.y){ //-100 IS A HACK OF SOME KIND WHICH I DO NOT UNDSERSTAND WHY I NEED IT
            if(mclicked == true){
                mclicked = false;
                mx = new int[] {minitialx};
                my = new int[] {minitialy};
                mindex= 0;
                toggled=true;
            }
            else{
                System.out.print(point.x);
                mclicked = true;
                mx = new int[] {100000,minitialx,minitialx};
                my = new int[] {100000,minitialy,minitialy};
                toggled=true;
            }
        }
    }
    }


    public void processClicked(){


    }

    public void update(Point point){
    }
}