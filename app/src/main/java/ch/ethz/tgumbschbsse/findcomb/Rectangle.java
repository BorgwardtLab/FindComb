package ch.ethz.tgumbschbsse.findcomb;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by tgumbsch on 7/28/17.
 */

public class Rectangle implements GameObject {
    private Rect mrectangle;
    private int mcolor;
    public boolean clicked;

    public Rectangle(Rect rectangle, int color){
        mrectangle = rectangle;
        mcolor = color;
        clicked = false;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mcolor);
        canvas.drawRect(mrectangle,paint);
    }

    @Override
    public void update() {

    }

    public void checkClicked(Point point){
        if(mrectangle.contains(point.x,point.y)){
            clicked = true;
        }
    }


    public void processClicked(){
        clicked = false;
    }

    public void update(Point point){
        mrectangle.set(point.x - mrectangle.width()/2, point.y - mrectangle.height()/2, point.x + mrectangle.width()/2, point.y + mrectangle.height()/2);
    }
}
