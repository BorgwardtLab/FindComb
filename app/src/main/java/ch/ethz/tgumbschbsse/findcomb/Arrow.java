package ch.ethz.tgumbschbsse.findcomb;

import android.graphics.Canvas;
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
    private List<Point> mpoints ;
    private int mcolor;
    private boolean mvisible;

    public Arrow(int x, int y, int length, double rotation,  int color, boolean visible){
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
        System.out.println(mpoints.size());

        for (int index=0; index < mpoints.size(); index++) {
            int new_x = (int) (translation.x + r11*mpoints.get(index).x + r12 * mpoints.get(index).y);
            int new_y =  (int) (translation.y+ r21*mpoints.get(index).x +  r22 * mpoints.get(index).y);
            mpoints.set(index, new Point(new_x, new_y));
        }

        mcolor = color;
        mvisible = visible;

    }

    @Override
    public void draw(Canvas canvas) {
        if(mvisible == true) {
            Paint paint = new Paint();
            paint.setColor(mcolor);


            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            //paint.setStyle(Paint.Style.FILL);

            System.out.print(mpoints.size());
            for (int index=0; index < mpoints.size(); index++) {
                System.out.print(index);
                path.moveTo(mpoints.get(index).x, mpoints.get(index).y);
            }
            path.close();

            canvas.drawPath(path, paint);
        }

    }


    @Override
    public void update() {

    }

    public void checkClicked(Point point){
    }


    public void processClicked(){

    }

    public void update(Point point){
    }
}