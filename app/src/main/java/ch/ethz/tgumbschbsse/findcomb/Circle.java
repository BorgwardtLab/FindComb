package ch.ethz.tgumbschbsse.findcomb;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by tgumbsch on 8/11/17.
 */

public class Circle implements GameObject {
    private int mx,my;
    private float mr;
    private int[] mcolor;
    public boolean clicked;

    public Circle(int x, int y, int r,  int[] color){
        mx = x;
        my = y;
        mr = (float) r;
        mcolor = color;
        clicked = false;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        if (clicked == true) {
            paint.setColor(mcolor[1]);
        }
        else{

            paint.setColor(mcolor[0]);
        }

        //System.out.println(mr);
        canvas.drawCircle(mx,my,mr,paint);
    }


    @Override
    public void update() {

    }

    public void checkClicked(Point point){
        int x = point.x;
        int y = point.y;
        if(Math.sqrt(Math.pow(x-mx,2)+Math.pow(y-my,2))<mr){
            if (clicked == true) {
                clicked = false;
            }
            else{
                clicked = true;
            }
        }
    }


    public void processClicked(){
        clicked = false;
    }

    public void update(Point point){
    }
}
