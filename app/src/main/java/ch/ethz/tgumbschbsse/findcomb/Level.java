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

import java.util.Random;

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
    private boolean[] mcolors;
    private Picture h1;
    public int Tut;
    public int logp;
    public boolean Continous;
    private boolean[] mrbgpy;

    private Random mrand;

    //private Picture hh1,hh2,hh3,hs1,hs2,hs3;






    private int[] red = { Color.rgb(128,0,0),Color.rgb(255,0,0)};
    private int[] blue = { Color.rgb(0,56,96),Color.rgb(0,112,192)};
    private int[] green = { Color.rgb(0,88,40),Color.rgb(0,176,80)};
    private int[] purple = { Color.rgb(56,24,80),Color.rgb(112, 48, 160)};
    private int[] yellow = { Color.rgb(128,128,0),Color.rgb(255,255,0)};

    private int mwidth;
    private int mheight;

    public Circle ch11, ch12, ch13, ch14, ch15;
    public Circle ch21, ch22, ch23, ch24, ch25;
    public Circle ch31, ch32, ch33, ch34, ch35;
    public Circle cs11, cs12, cs13, cs14, cs15;
    public Circle cs21, cs22, cs23, cs24, cs25;
    public Circle cs31, cs32, cs33, cs34, cs35;

    //public Circle mRed, mBlue, mGreen, mPurple, mYellow;

    public boolean clicked;
    public boolean[] entered;





    public Level(Context context, int width, int height, boolean type,  boolean[] healthy1, boolean[] healthy2, boolean[] healthy3, boolean[] sick1, boolean[] sick2, boolean[] sick3, boolean[] colors,boolean[] rbgpy){
        mhealthy1 = healthy1;
        mhealthy2 = healthy2;
        mhealthy3 = healthy3;
        msick1 = sick1;
        msick2 = sick2;
        msick3 = sick3;
        mwidth = width;
        mheight = height;
        mcolors = colors;
        clicked = false;
        entered = new boolean[] {false,false,false,false,false};
        Tut = 0;


        if(Continous == false){
            mrbgpy = rbgpy;
        }

        h1 = new Picture(R.drawable.leftside, context,0,0,3*mheight/7,2*mheight/3);


        Continous = type;
        mrand = new Random();
        mrand.setSeed(System.currentTimeMillis());



        int i = 2;
        int radius = 18;

        /*
        if (colors[0] == true) {
            mRed = new Circle(i * width / 16, 15 * height / 20, width / radius, red,false);
            i += 3;
        }
        else{
            mRed = new Circle(width*2, 15 * height / 20, width / radius, red,false);
        }

        if( colors[1] == true) {
            mBlue = new Circle(i * width / 16, 15 * height / 20, width / radius, blue,false);
            i += 3;
        }
        else{
            mBlue = new Circle(width*2, 15 * height / 20, width / radius, red,false);
        }

        if(colors[2] == true) {
            mGreen = new Circle(i * width / 16, 15 * height / 20, width / radius, green,false);
            i += 3;
        }
        else{
            mGreen = new Circle(width*2, 15 * height / 20, width / radius, red,false);
        }

        if(colors[4] == true) {
            mYellow = new Circle(i * width / 16, 15 * height / 20, width / radius, yellow,false);
            i += 3;
        }
        else{
            mYellow = new Circle(width*2, 15 * height / 20, width / radius, red,false);
        }

        if(colors[3]==true) {
            mPurple = new Circle(i * width / 16, 15 * height / 20, width / radius, purple,false);
        }
        else{
            mPurple = new Circle(width*2, 15 * height / 20, width / radius, red,false);
        }
        */


        radius = 22;


        int xh1 = 0;
        int xh2 = 0;
        int xh3 = 0;
        int xs1 = 0;
        int xs2 = 0;
        int xs3 = 0;


        if(mhealthy1[0] == true){
            ch11 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,red, true);
        }
        else{
            ch11 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,red, false);
        }
        if(mhealthy2[0] == true){
            ch21 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,red, true);
        }
        else{
            ch21 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,red, false);
        }
        if(mhealthy3[0] == true){
            ch31 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,red, true);
        }
        else{
            ch31 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,red, false);
        }
        if(msick1[0] == true){
            cs11 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,red, true);
        }
        else{
            cs11 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,red, false);
        }
        if(msick2[0] == true){
            cs21 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,red, true);
        }
        else{
            cs21 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,red, false);
        }
        if(msick3[0] == true){
            cs31 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,red, true);
        }
        else{
            cs31 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,red, false);
        }

        xh1 += 1;
        xh2 += 1;
        xh3 += 1;
        xs1 += 1;
        xs2 += 1;
        xs3 += 1;


        if(mhealthy1[1] == true){
            ch12 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,blue, true);
        }
        else{
            ch12 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,blue, false);
        }
        if(mhealthy2[1] == true){
            ch22 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,blue, true);
        }
        else{
            ch22 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,blue, false);
        }
        if(mhealthy3[1] == true){
            ch32 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,blue, true);
        }
        else{
            ch32 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,blue, false);
        }
        if(msick1[1] == true){
            cs12 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,blue, true);
        }
        else{
            cs12 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,blue, false);
        }
        if(msick2[1] == true){
            cs22 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,blue, true);
        }
        else{
            cs22 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,blue, false);
        }
        if(msick3[1] == true){
            cs32 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,blue, true);
        }
        else{
            cs32 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,blue, false);
        }

        xh1 += 1;
        xh2 += 1;
        xh3 += 1;
        xs1 += 1;
        xs2 += 1;
        xs3 += 1;


        if(mhealthy1[2] == true){
            ch13 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,green, true);
        }
        else{
            ch13 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,green, false);
        }
        if(mhealthy2[2] == true){
            ch23 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,green, true);
        }
        else{
            ch23 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,green, false);
        }
        if(mhealthy3[2] == true){
            ch33 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,green, true);
        }
        else{
            ch33 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,green, false);
        }
        if(msick1[2] == true){
            cs13 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,green, true);
        }
        else{
            cs13 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,green, false);
        }
        if(msick2[2] == true){
            cs23 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,green, true);
        }
        else{
            cs23 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,green, false);
        }
        if(msick3[2] == true){
            cs33 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,green, true);
        }
        else{
            cs33 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,green, false);
        }

        xh1 += 2;
        xh2 += 2;
        xh3 += 2;
        xs1 += 2;
        xs2 += 2;
        xs3 += 2;


        if(mhealthy1[3] == true){
            ch14 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,yellow, true);
        }
        else{
            ch14 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,yellow, false);
        }
        if(mhealthy2[3] == true){
            ch24 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,yellow, true);
        }
        else{
            ch24 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,yellow, false);
        }
        if(mhealthy3[3] == true){
            ch34 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,yellow, true);
        }
        else{
            ch34 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,yellow, false);
        }
        if(msick1[3] == true){
            cs14 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,yellow, true);
        }
        else{
            cs14 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,yellow, false);
        }
        if(msick2[3] == true){
            cs24 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,yellow, true);
        }
        else{
            cs24 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,yellow, false);
        }
        if(msick3[3] == true){
            cs34 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,yellow, true);
        }
        else{
            cs34 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,yellow, false);
        }



        xh1 -= 1;
        xh2 -= 1;
        xh3 -= 1;
        xs1 -= 1;
        xs2 -= 1;
        xs3 -= 1;

        if(mhealthy1[4] == true){
            ch15 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,purple, true);
        }
        else{
            ch15 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,purple, false);
        }
        if(mhealthy2[4] == true){
            ch25 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,purple, true);
        }
        else{
            ch25 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,purple, false);
        }
        if(mhealthy3[4] == true){
            ch35 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,purple, true);
        }
        else{
            ch35 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,purple, false);
        }
        if(msick1[4] == true){
            cs15 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,purple, true);
        }
        else{
            cs15 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,purple, false);
        }
        if(msick2[4] == true){
            cs25 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,purple, true);
        }
        else{
            cs25 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,purple, false);
        }
        if(msick3[4] == true){
            cs35 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,purple, true);
        }
        else{
            cs35 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,purple, false);
        }

        reset();

    }

    public void reset(){
        clicked = false;
        entered = new boolean[] {false,false,false,false,false};
        ch11.clicked = false;
        ch12.clicked = false;
        ch13.clicked = false;
        ch14.clicked = false;
        ch15.clicked = false;
        ch21.clicked = false;
        ch22.clicked = false;
        ch23.clicked = false;
        ch24.clicked = false;
        ch25.clicked = false;
        ch31.clicked = false;
        ch32.clicked = false;
        ch33.clicked = false;
        ch34.clicked = false;
        ch35.clicked = false;
        cs11.clicked = false;
        cs12.clicked = false;
        cs13.clicked = false;
        cs14.clicked = false;
        cs15.clicked = false;
        cs21.clicked = false;
        cs22.clicked = false;
        cs23.clicked = false;
        cs24.clicked = false;
        cs25.clicked = false;
        cs31.clicked = false;
        cs32.clicked = false;
        cs33.clicked = false;
        cs34.clicked = false;
        cs35.clicked = false;

    }

    @Override
    public void draw(Canvas canvas) {
        h1.draw(canvas);

        ch11.draw(canvas);
        ch12.draw(canvas);
        ch13.draw(canvas);
        ch14.draw(canvas);
        ch15.draw(canvas);
        ch21.draw(canvas);
        ch22.draw(canvas);
        ch23.draw(canvas);
        ch24.draw(canvas);
        ch25.draw(canvas);
        ch31.draw(canvas);
        ch32.draw(canvas);
        ch33.draw(canvas);
        ch34.draw(canvas);
        ch35.draw(canvas);
        cs11.draw(canvas);
        cs12.draw(canvas);
        cs13.draw(canvas);
        cs14.draw(canvas);
        cs15.draw(canvas);
        cs21.draw(canvas);
        cs22.draw(canvas);
        cs23.draw(canvas);
        cs24.draw(canvas);
        cs25.draw(canvas);
        cs31.draw(canvas);
        cs32.draw(canvas);
        cs33.draw(canvas);
        cs34.draw(canvas);
        cs35.draw(canvas);


        Paint paint = new Paint();

        canvas.drawRect(new Rect(3*mheight/7, mheight/10-1, 5*mwidth/7, mheight/10+1), paint);
        canvas.drawRect(new Rect(3*mheight/7, 2*mheight/10-1, 5*mwidth/7, 2*mheight/10+1), paint);
        canvas.drawRect(new Rect(3*mheight/7, 9*mheight/20-1, 5*mwidth/7, 9*mheight/20+1), paint);
        canvas.drawRect(new Rect(3*mheight/7, 11*mheight/20-1, 5*mwidth/7, 11*mheight/20+1), paint);


    }

    @Override
    public void update() {
        ch11.clicked = entered[0];
        ch12.clicked = entered[1];
        ch13.clicked = entered[2];
        ch14.clicked = entered[3];
        ch15.clicked = entered[4];
        ch21.clicked = entered[0];
        ch22.clicked = entered[1];
        ch23.clicked = entered[2];
        ch24.clicked = entered[3];
        ch25.clicked = entered[4];
        ch31.clicked = entered[0];
        ch32.clicked = entered[1];
        ch33.clicked = entered[2];
        ch34.clicked = entered[3];
        ch35.clicked = entered[4];
        cs11.clicked = entered[0];
        cs12.clicked = entered[1];
        cs13.clicked = entered[2];
        cs14.clicked = entered[3];
        cs15.clicked = entered[4];
        cs21.clicked = entered[0];
        cs22.clicked = entered[1];
        cs23.clicked = entered[2];
        cs24.clicked = entered[3];
        cs25.clicked = entered[4];
        cs31.clicked = entered[0];
        cs32.clicked = entered[1];
        cs33.clicked = entered[2];
        cs34.clicked = entered[3];
        cs35.clicked = entered[4];

    }

    public void checkClicked(Point point){

        ch11.checkClicked(point);
        if (ch11.processChanged() == true){
            entered[0] ^= true;
        }
        ch21.checkClicked(point);
        if (ch21.processChanged() == true){
            entered[0] ^= true;
        }
        ch31.checkClicked(point);
        if (ch31.processChanged() == true){
            entered[0] ^= true;
        }
        cs11.checkClicked(point);
        if (cs11.processChanged() == true){
            entered[0] ^= true;
        }
        cs21.checkClicked(point);
        if (cs21.processChanged() == true){
            entered[0] ^= true;
        }
        cs31.checkClicked(point);
        if (cs31.processChanged() == true){
            entered[0] ^= true;
        }

        ch12.checkClicked(point);
        if (ch12.processChanged() == true){
            entered[1] ^= true;
        }
        ch22.checkClicked(point);
        if (ch22.processChanged() == true){
            entered[1] ^= true;
        }
        ch32.checkClicked(point);
        if (ch32.processChanged() == true){
            entered[1] ^= true;
        }
        cs12.checkClicked(point);
        if (cs12.processChanged() == true){
            entered[1] ^= true;
        }
        cs22.checkClicked(point);
        if (cs22.processChanged() == true){
            entered[1] ^= true;
        }
        cs32.checkClicked(point);
        if (cs32.processChanged() == true){
            entered[1] ^= true;
        }

        ch13.checkClicked(point);
        if (ch13.processChanged() == true){
            entered[2] ^= true;
        }
        ch23.checkClicked(point);
        if (ch23.processChanged() == true){
            entered[2] ^= true;
        }
        ch33.checkClicked(point);
        if (ch33.processChanged() == true){
            entered[2] ^= true;
        }
        cs13.checkClicked(point);
        if (cs13.processChanged() == true){
            entered[2] ^= true;
        }
        cs23.checkClicked(point);
        if (cs23.processChanged() == true){
            entered[2] ^= true;
        }
        cs33.checkClicked(point);
        if (cs33.processChanged() == true){
            entered[2] ^= true;
        }

        ch14.checkClicked(point);
        if (ch14.processChanged() == true){
            entered[3] ^= true;
        }
        ch24.checkClicked(point);
        if (ch24.processChanged() == true){
            entered[3] ^= true;
        }
        ch34.checkClicked(point);
        if (ch34.processChanged() == true){
            entered[3] ^= true;
        }
        cs14.checkClicked(point);
        if (cs14.processChanged() == true){
            entered[3] ^= true;
        }
        cs24.checkClicked(point);
        if (cs24.processChanged() == true){
            entered[3] ^= true;
        }
        cs34.checkClicked(point);
        if (cs34.processChanged() == true){
            entered[3] ^= true;
        }

        ch15.checkClicked(point);
        if (ch15.processChanged() == true){
            entered[4] ^= true;
        }
        ch25.checkClicked(point);
        if (ch25.processChanged() == true){
            entered[4] ^= true;
        }
        ch35.checkClicked(point);
        if (ch35.processChanged() == true){
            entered[4] ^= true;
        }
        cs15.checkClicked(point);
        if (cs15.processChanged() == true){
            entered[4] ^= true;
        }
        cs25.checkClicked(point);
        if (cs25.processChanged() == true){
            entered[4] ^= true;
        }
        cs35.checkClicked(point);
        if (cs35.processChanged() == true){
            entered[4] ^= true;
        }

        if(point.x > 15*mwidth/20 && point.x < 19*mwidth/20){
            if(point.y >3*mheight/20 && point.y < 3*mheight/5) {
                clicked = true;
            }
        }

    }


    public void random_new(){
        for(int i = 0; i<5; i++){
            mhealthy1[i] = mrand.nextBoolean();
            mhealthy2[i] = mrand.nextBoolean();
            mhealthy3[i] = mrand.nextBoolean();
            msick1[i] = mrand.nextBoolean();
            msick2[i] = mrand.nextBoolean();
            msick3[i] = mrand.nextBoolean();
        }


        int radius = 22;


        int xh1 = 0;
        int xh2 = 0;
        int xh3 = 0;
        int xs1 = 0;
        int xs2 = 0;
        int xs3 = 0;


        if(mhealthy1[0] == true){
            ch11 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,red, true);
        }
        else{
            ch11 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,red, false);
        }
        if(mhealthy2[0] == true){
            ch21 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,red, true);
        }
        else{
            ch21 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,red, false);
        }
        if(mhealthy3[0] == true){
            ch31 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,red, true);
        }
        else{
            ch31 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,red, false);
        }
        if(msick1[0] == true){
            cs11 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,red, true);
        }
        else{
            cs11 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,red, false);
        }
        if(msick2[0] == true){
            cs21 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,red, true);
        }
        else{
            cs21 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,red, false);
        }
        if(msick3[0] == true){
            cs31 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,red, true);
        }
        else{
            cs31 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,red, false);
        }

        xh1 += 1;
        xh2 += 1;
        xh3 += 1;
        xs1 += 1;
        xs2 += 1;
        xs3 += 1;


        if(mhealthy1[1] == true){
            ch12 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,blue, true);
        }
        else{
            ch12 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,blue, false);
        }
        if(mhealthy2[1] == true){
            ch22 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,blue, true);
        }
        else{
            ch22 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,blue, false);
        }
        if(mhealthy3[1] == true){
            ch32 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,blue, true);
        }
        else{
            ch32 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,blue, false);
        }
        if(msick1[1] == true){
            cs12 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,blue, true);
        }
        else{
            cs12 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,blue, false);
        }
        if(msick2[1] == true){
            cs22 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,blue, true);
        }
        else{
            cs22 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,blue, false);
        }
        if(msick3[1] == true){
            cs32 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,blue, true);
        }
        else{
            cs32 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,blue, false);
        }

        xh1 += 1;
        xh2 += 1;
        xh3 += 1;
        xs1 += 1;
        xs2 += 1;
        xs3 += 1;


        if(mhealthy1[2] == true){
            ch13 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,green, true);
        }
        else{
            ch13 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,green, false);
        }
        if(mhealthy2[2] == true){
            ch23 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,green, true);
        }
        else{
            ch23 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,green, false);
        }
        if(mhealthy3[2] == true){
            ch33 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,green, true);
        }
        else{
            ch33 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,green, false);
        }
        if(msick1[2] == true){
            cs13 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,green, true);
        }
        else{
            cs13 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,green, false);
        }
        if(msick2[2] == true){
            cs23 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,green, true);
        }
        else{
            cs23 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,green, false);
        }
        if(msick3[2] == true){
            cs33 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,green, true);
        }
        else{
            cs33 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,green, false);
        }

        xh1 += 2;
        xh2 += 2;
        xh3 += 2;
        xs1 += 2;
        xs2 += 2;
        xs3 += 2;


        if(mhealthy1[3] == true){
            ch14 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,yellow, true);
        }
        else{
            ch14 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,yellow, false);
        }
        if(mhealthy2[3] == true){
            ch24 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,yellow, true);
        }
        else{
            ch24 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,yellow, false);
        }
        if(mhealthy3[3] == true){
            ch34 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,yellow, true);
        }
        else{
            ch34 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,yellow, false);
        }
        if(msick1[3] == true){
            cs14 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,yellow, true);
        }
        else{
            cs14 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,yellow, false);
        }
        if(msick2[3] == true){
            cs24 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,yellow, true);
        }
        else{
            cs24 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,yellow, false);
        }
        if(msick3[3] == true){
            cs34 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,yellow, true);
        }
        else{
            cs34 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,yellow, false);
        }



        xh1 -= 1;
        xh2 -= 1;
        xh3 -= 1;
        xs1 -= 1;
        xs2 -= 1;
        xs3 -= 1;

        if(mhealthy1[4] == true){
            ch15 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,purple, true);
        }
        else{
            ch15 = new Circle((4+xh1)*mwidth/13,mheight/20,mheight/radius,purple, false);
        }
        if(mhealthy2[4] == true){
            ch25 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,purple, true);
        }
        else{
            ch25 = new Circle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,purple, false);
        }
        if(mhealthy3[4] == true){
            ch35 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,purple, true);
        }
        else{
            ch35 = new Circle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,purple, false);
        }
        if(msick1[4] == true){
            cs15 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,purple, true);
        }
        else{
            cs15 = new Circle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,purple, false);
        }
        if(msick2[4] == true){
            cs25 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,purple, true);
        }
        else{
            cs25 = new Circle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,purple, false);
        }
        if(msick3[4] == true){
            cs35 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,purple, true);
        }
        else{
            cs35 = new Circle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,purple, false);
        }

        reset();
    }



    public void compute_score(){

        int length = 0;
        for(boolean i : entered){
            if(i==true){
                length++;
            }
        }
        int[] checked = new int[length];
        int j = 0;
        int k =0;
        while(j<entered.length){
            if(entered[j] ==true){
                checked[k]=j;
                k++;
            }
            j++;
        }
        int ch1 = 0;
        int ch2 = 0;
        int ch3 = 0;
        int cs1 = 0;
        int cs2 = 0;
        int cs3 = 0;
        //Store reps in binary mode
        int power = 10;
        int base = 1;
        for (int i : checked) {
            if (mhealthy1[i] == true) {
                ch1 += base;
            }
            if (mhealthy2[i] == true) {
                ch2 += base;
            }
            if (mhealthy3[i] == true) {
                ch3 += base;
            }
            if (msick1[i] == true) {
                cs1 += base;
            }
            if (msick2[i] == true) {
                cs2 += base;
            }
            if (msick3[i] == true) {
                cs3 += base;
            }
            base = base * power;
        }


        int false_neg = 0;
        int false_pos = 0;
        if (ch1 == cs1 || ch1 == cs2 || ch1 == cs3) {
            false_neg++;
        }
        if (ch2 == cs1 || ch2 == cs2 || ch2 == cs3) {
            false_neg++;
        }
        if (ch3 == cs1 || ch3 == cs2 || ch3 == cs3) {
            false_neg++;
        }
        if (ch1 == cs1 || ch2 == cs1 || ch3 == cs1) {
            false_pos++;
        }
        if (ch1 == cs2 || ch2 == cs2 || ch3 == cs2) {
            false_pos++;
        }
        if (ch1 == cs3 || ch2 == cs3 || ch3 == cs3) {
            false_pos++;
        }
        int dimensionality_penalty = checked.length; //should be <=3
        int false_classification_penalty = (Math.min(false_neg, false_pos))-1; //should be <= 3

        // The scoring is in a way that the game mechanics are satisfying
        //StatTests Fisher = new StatTests(3, 0, false_classification_penalty, 3 - false_classification_penalty);
        if(Continous==true) {
            int base_score = 3;//(int) (Fisher.logp + 1.0);
            logp = 5 * (base_score - (dimensionality_penalty + 2 * false_classification_penalty));
        }
        else {
            boolean check = true;
            for(int i=0;i<5;i++){
                if(entered[i]!=mrbgpy[i]){
                    check=false;
                }
            }
            if(check==true || false_classification_penalty==-1){
                logp=5;
            }
            else{
                logp=-5;
            }

        }


    }

}
