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


    public Picture(int picture, Context context){
        mscratch = BitmapFactory.decodeResource(context.getResources(), picture);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels * 2 / 3;
        int height = metrics.heightPixels* 3 / 4;
        mscratch = Bitmap.createScaledBitmap(mscratch, width, height, false);

        mframeToDraw = new Rect(0, 0, width, height);
        mwhereToDraw = new RectF(0, 0, width, height);

        clicked = false;
    }

    public void checkClicked(Point point){
        if(mframeToDraw.contains(point.x,point.y)){
            clicked = true;
            System.out.println("Changed");
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
