package ch.ethz.tgumbschbsse.findcomb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;

/**
 * Created by tgumbsch on 8/14/17.
 */

public class Level implements  GameObject{
    private boolean[] mhealthy1;
    private boolean[] mhealthy2;
    private boolean[] mhealthy3;
    private boolean[] msick1;
    private boolean[] msick2;
    private boolean[] msick3;
    private Picture h1;





    private int[] red = { Color.rgb(128,0,0),Color.rgb(255,0,0)};
    private int[] blue = { Color.rgb(0,56,96),Color.rgb(0,112,192)};
    private int[] green = { Color.rgb(0,88,40),Color.rgb(0,176,80)};
    private int[] purple = { Color.rgb(56,24,80),Color.rgb(112, 48, 160)};
    private int[] yellow = { Color.rgb(128,128,0),Color.rgb(255,255,0)};

    private int mwidth;
    private int mheight;


    private Circle mRed, mBlue, mGreen, mPurple, mYellow;

    public boolean clicked;

    public Level(Context context, int width, int height, boolean[] healthy1, boolean[] healthy2, boolean[] healthy3, boolean[] sick1, boolean[] sick2, boolean[] sick3, boolean[] colors){
        mhealthy1 = healthy1;
        mhealthy2 = healthy2;
        mhealthy3 = healthy3;
        msick1 = sick1;
        msick2 = sick2;
        msick3 = sick3;
        mwidth = width;
        mheight = height;
        clicked = false;

        h1 = new Picture(R.drawable.leftside, context,0,0,3*mheight/7,2*mheight/3);




        int i = 2;

        if (colors[0] == true) {
            mRed = new Circle(i * width / 16, 15 * height / 20, width / 18, red);
            i += 3;
        }
        else{
            mRed = new Circle(width*2, 15 * height / 20, width / 18, red);
        }

        if( colors[1] == true) {
            mBlue = new Circle(i * width / 16, 15 * height / 20, width / 18, blue);
            i += 3;
        }
        else{
            mBlue = new Circle(width*2, 15 * height / 20, width / 18, red);
        }

        if(colors[2] == true) {
            mGreen = new Circle(i * width / 16, 15 * height / 20, width / 18, green);
            i += 3;
        }
        else{
            mGreen = new Circle(width*2, 15 * height / 20, width / 18, red);
        }

        if(colors[4] == true) {
            mYellow = new Circle(i * width / 16, 15 * height / 20, width / 18, yellow);
            i += 3;
        }
        else{
            mYellow = new Circle(width*2, 15 * height / 20, width / 18, red);
        }

        if(colors[3]==true) {
            mPurple = new Circle(i * width / 16, 15 * height / 20, width / 18, purple);
        }
        else{
            mPurple = new Circle(width*2, 15 * height / 20, width / 18, red);
        }




    }

    public void reset(){
        clicked = false;
        mRed.clicked = false;
        mBlue.clicked = false;
        mGreen.clicked = false;
        mPurple.clicked = false;
        mYellow.clicked = false;

    }

    @Override
    public void draw(Canvas canvas) {
        h1.draw(canvas);
        mRed.draw(canvas);
        mBlue.draw(canvas);
        mGreen.draw(canvas);
        mPurple.draw(canvas);
        mYellow.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#0099cc"));

        canvas.drawRect(new Rect(19*mwidth/20,3*mheight/20,15*mwidth/20, 3*mheight/5),paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        canvas.drawText("Go!",16*mwidth/20, 8*mheight/20,paint);

        int xh1 = 0;
        int xh2 = 0;
        int xh3 = 0;
        int xs1 = 0;
        int xs2 = 0;
        int xs3 = 0;

        //Red
        if(mRed.clicked == true){
            paint.setColor(red[1]);
        }
        else{
            paint.setColor(red[0]);
        }
        if(mhealthy1[0] == true){
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/20,paint);
        }
        if(mhealthy2[0] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/20,paint);
        }
        if(mhealthy3[0] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/20,paint);
        }
        if(msick1[0] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/20,paint);
        }
        if(msick2[0] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/20,paint);
        }
        if(msick3[0] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/20,paint);
        }
        xh1 += 1;
        xh2 += 1;
        xh3 += 1;
        xs1 += 1;
        xs2 += 1;
        xs3 += 1;

        //blue
        if(mBlue.clicked == true){
            paint.setColor(blue[1]);
        }
        else{
            paint.setColor(blue[0]);
        }
        if(mhealthy1[1] == true){
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/20,paint);
        }
        if(mhealthy2[1] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/20,paint);
        }
        if(mhealthy3[1] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/20,paint);
        }
        if(msick1[1] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/20,paint);
        }
        if(msick2[1] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/20,paint);
        }
        if(msick3[1] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/20,paint);
        }
        xh1 += 1;
        xh2 += 1;
        xh3 += 1;
        xs1 += 1;
        xs2 += 1;
        xs3 += 1;

        //green
        if(mGreen.clicked == true){
            paint.setColor(green[1]);
        }
        else{
            paint.setColor(green[0]);
        }
        if(mhealthy1[2] == true){
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/20,paint);
        }
        if(mhealthy2[2] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/20,paint);
        }
        if(mhealthy3[2] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/20,paint);
        }
        if(msick1[2] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/20,paint);
        }
        if(msick2[2] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/20,paint);
        }
        if(msick3[2] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/20,paint);
        }
        xh1 += 1;
        xh2 += 1;
        xh3 += 1;
        xs1 += 1;
        xs2 += 1;
        xs3 += 1;
        //yellow
        if(mYellow.clicked == true){
            paint.setColor(yellow[1]);
        }
        else{
            paint.setColor(yellow[0]);
        }
        if(mhealthy1[4] == true){
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/20,paint);
        }
        if(mhealthy2[4] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/20,paint);
        }
        if(mhealthy3[4] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/20,paint);
        }
        if(msick1[4] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/20,paint);
        }
        if(msick2[4] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/20,paint);
        }
        if(msick3[4] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/20,paint);
        }


        xh1 += 1;
        xh2 += 1;
        xh3 += 1;
        xs1 += 1;
        xs2 += 1;
        xs3 += 1;



        //purple
        if(mPurple.clicked == true){
            paint.setColor(purple[1]);
        }
        else{
            paint.setColor(purple[0]);
        }
        if(mhealthy1[3] == true){
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/20,paint);
        }
        if(mhealthy2[3] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/20,paint);
        }
        if(mhealthy3[3] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/20,paint);
        }
        if(msick1[3] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/20,paint);
        }
        if(msick2[3] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/20,paint);
        }
        if(msick3[3] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/20,paint);
        }



    }

    @Override
    public void update() {

    }

    public void checkClicked(Point point){

        mGreen.checkClicked(point);
        mRed.checkClicked(point);
        mBlue.checkClicked(point);
        mPurple.checkClicked(point);
        mYellow.checkClicked(point);

        if(point.x > 15*mwidth/20 && point.x < 19*mwidth/20){
            if(point.y >3*mheight/10 && point.y < 12*mheight/20) {
                clicked = true;
            }
        }

    }

    public boolean[] getClicked(){
        boolean[] solution = {mRed.clicked, mBlue.clicked, mGreen.clicked, mPurple.clicked, mYellow.clicked};
        mBlue.processClicked();
        mRed.processClicked();
        mGreen.processClicked();
        mPurple.processClicked();
        mYellow.processClicked();

        return solution;
    }
}
