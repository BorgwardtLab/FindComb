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


public class Arrow implements GameObject {
    //private List<Point> mpoints ;
    //private int mcolor;
    private boolean mvisible;
    private Bitmap mbpm;
    int[] mx, my;
    int mcount, mspeed, mindex, mtype;

    public Arrow(int[] x, int[] y, int dir, double scale, int speed, Context context, boolean visible){    //int x, int y, int length, double rotation,  int color, boolean visible){
        /*
        int width = 2*length/5; //proportion of arrow
        Point translation = new Point(x,y);
        //rotation matrix
        double r11 = Math.cos(Math.toRadians(rotation));
        double r22 = r11;
        double r12 = - Math.sin(Math.toRadians(rotation));
        double r21 = -r12;
        //all vertices of an arrow
        mpoints = new ArrayList<Point>();
        Point p1 = new Point(-width/2, -width);
        mpoints.add(p1);
        Point p2 = new Point(0, 0);
        mpoints.add(p2);
        Point p3 = new Point(width/2, width);
        mpoints.add(p3);
        Point p4 = new Point(+width/4,width);
        mpoints.add(p4);
        Point p5 = new Point(+width/4,length);
        mpoints.add(p5);
        Point p6 = new Point(-width/4,length);
        mpoints.add(p6);
        Point p7 = new Point(-width/4,width);
        mpoints.add(p7);

        for (int index=0; index < mpoints.size(); index++) {
            //int new_x = (int) (translation.x + r11*mpoints.get(index).x + r12 * mpoints.get(index).y);
            //int new_y =  (int) (translation.y+ r21*mpoints.get(index).x +  r22 * mpoints.get(index).y);
            int new_x = (int) (translation.x + mpoints.get(index).x);
            int new_y =  (int) (translation.y+ mpoints.get(index).y);

            mpoints.set(index, new Point(new_x, new_y));
        }

        mcolor = color;
        */
        mbpm = BitmapFactory.decodeResource(context.getResources()  ,R.drawable.arrow);
        mbpm = scaleflip(mbpm,dir,scale);
        mx = x;
        my = y;
        mindex = 0;
        mcount = 0;
        mspeed = speed;
        mvisible = visible;
        mtype= dir;
    }

    @Override
    public void draw(Canvas canvas) {
        if(mvisible == true) {
            int x,y;
            if(mtype==1){
                x = mx[mindex];
                y = my[mindex] - mbpm.getHeight();
            }
            else if (mtype ==2){
                x = mx[mindex] - mbpm.getWidth();
                y = my[mindex];
            }
            else if (mtype == 3){
                x = mx[mindex];
                y = my[mindex];
            }
            else{
                x = mx[mindex] - mbpm.getWidth();
                y = my[mindex] - mbpm.getHeight();
            }
            canvas.drawBitmap(mbpm,x,y,null);
            //Paint paint = new Paint();
            //paint.setColor(mcolor);
            //Path path = new Path();
            //path.setFillType(Path.FillType.EVEN_ODD);
            //for (int index=0; index < mpoints.size(); index++) {
            //    System.out.print(index);
            //    path.moveTo(mpoints.get(index).x, mpoints.get(index).y);
            //}
            //path.close();
            //canvas.drawPath(path, paint);
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
    }


    public void processClicked(){

    }

    public void update(Point point){
    }
}