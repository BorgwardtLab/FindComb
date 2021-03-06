package ch.ethz.tgumbschbsse.findcomb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

/**
 * Created by tgumbsch on 7/28/17.
 */

public class Picture implements GameObject {
    private Bitmap mscratch;
    Rect mframeToDraw;
    RectF mwhereToDraw;

    public boolean clicked;


    public Picture(int picture, Context context, int x, int y, int width, int height){
        mscratch = BitmapFactory.decodeResource(context.getResources(), picture);

        //DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        //int width = metrics.widthPixels * 9 / 10;
        //int height = metrics.heightPixels* 3 / 5;

        System.out.println(String.valueOf(width));
        System.out.println(String.valueOf(height));
        mscratch = Bitmap.createScaledBitmap(mscratch, width, height, false);

        mframeToDraw = new Rect(x, y, x+width, y+height);
        mwhereToDraw = new RectF(x, y, x+width, y+height);

        clicked = false;
    }

    public void checkClicked(Point point){
        if(mframeToDraw.contains(point.x,point.y)){
            clicked = true;
        }
    }



    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawBitmap(mscratch,mframeToDraw,mwhereToDraw, paint);

    }

    @Override
    public void update() {

    }
}
