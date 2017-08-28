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

    private Random mrand;

    //private Picture hh1,hh2,hh3,hs1,hs2,hs3;






    private int[] red = { Color.rgb(128,0,0),Color.rgb(255,0,0)};
    private int[] blue = { Color.rgb(0,56,96),Color.rgb(0,112,192)};
    private int[] green = { Color.rgb(0,88,40),Color.rgb(0,176,80)};
    private int[] purple = { Color.rgb(56,24,80),Color.rgb(112, 48, 160)};
    private int[] yellow = { Color.rgb(128,128,0),Color.rgb(255,255,0)};

    private int mwidth;
    private int mheight;


    public Circle mRed, mBlue, mGreen, mPurple, mYellow;

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
        mcolors = colors;
        clicked = false;
        Tut = 0;
        h1 = new Picture(R.drawable.leftside, context,0,0,3*mheight/7,2*mheight/3);



        mrand = new Random();
        mrand.setSeed(42);



        int i = 2;
        int radius = 18;

        if (colors[0] == true) {
            mRed = new Circle(i * width / 16, 15 * height / 20, width / radius, red);
            i += 3;
        }
        else{
            mRed = new Circle(width*2, 15 * height / 20, width / radius, red);
        }

        if( colors[1] == true) {
            mBlue = new Circle(i * width / 16, 15 * height / 20, width / radius, blue);
            i += 3;
        }
        else{
            mBlue = new Circle(width*2, 15 * height / 20, width / radius, red);
        }

        if(colors[2] == true) {
            mGreen = new Circle(i * width / 16, 15 * height / 20, width / radius, green);
            i += 3;
        }
        else{
            mGreen = new Circle(width*2, 15 * height / 20, width / radius, red);
        }

        if(colors[4] == true) {
            mYellow = new Circle(i * width / 16, 15 * height / 20, width / radius, yellow);
            i += 3;
        }
        else{
            mYellow = new Circle(width*2, 15 * height / 20, width / radius, red);
        }

        if(colors[3]==true) {
            mPurple = new Circle(i * width / 16, 15 * height / 20, width / radius, purple);
        }
        else{
            mPurple = new Circle(width*2, 15 * height / 20, width / radius, red);
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

        if(Tut == 0) {
            mRed.draw(canvas);
            mBlue.draw(canvas);
            mGreen.draw(canvas);
            mPurple.draw(canvas);
            mYellow.draw(canvas);
        }


        int xh1 = 0;
        int xh2 = 0;
        int xh3 = 0;
        int xs1 = 0;
        int xs2 = 0;
        int xs3 = 0;

        int radius = 22;

        Paint paint = new Paint();

        canvas.drawRect(new Rect(3*mheight/7, mheight/10-1, 5*mwidth/7, mheight/10+1), paint);
        canvas.drawRect(new Rect(3*mheight/7, 2*mheight/10-1, 5*mwidth/7, 2*mheight/10+1), paint);
        canvas.drawRect(new Rect(3*mheight/7, 9*mheight/20-1, 5*mwidth/7, 9*mheight/20+1), paint);
        canvas.drawRect(new Rect(3*mheight/7, 11*mheight/20-1, 5*mwidth/7, 11*mheight/20+1), paint);

        //Red
        if(mRed.clicked == true){
            paint.setColor(red[1]);
        }
        else{
            paint.setColor(red[0]);
        }
        if(mhealthy1[0] == true){
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/radius,paint);
        }
        if(mhealthy2[0] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,paint);
        }
        if(mhealthy3[0] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,paint);
        }
        if(msick1[0] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,paint);
        }
        if(msick2[0] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,paint);
        }
        if(msick3[0] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,paint);
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
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/radius,paint);
        }
        if(mhealthy2[1] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,paint);
        }
        if(mhealthy3[1] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,paint);
        }
        if(msick1[1] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,paint);
        }
        if(msick2[1] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,paint);
        }
        if(msick3[1] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,paint);
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
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/radius,paint);
        }
        if(mhealthy2[2] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,paint);
        }
        if(mhealthy3[2] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,paint);
        }
        if(msick1[2] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,paint);
        }
        if(msick2[2] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,paint);
        }
        if(msick3[2] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,paint);
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
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/radius,paint);
        }
        if(mhealthy2[4] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,paint);
        }
        if(mhealthy3[4] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,paint);
        }
        if(msick1[4] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,paint);
        }
        if(msick2[4] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,paint);
        }
        if(msick3[4] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,paint);
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
            canvas.drawCircle((4+xh1)*mwidth/13,mheight/20,mheight/radius,paint);
        }
        if(mhealthy2[3] == true){
            canvas.drawCircle((4+xh2)*mwidth/13,3*mheight/20,mheight/radius,paint);
        }
        if(mhealthy3[3] == true){
            canvas.drawCircle((4+xh3)*mwidth/13,5*mheight/20,mheight/radius,paint);
        }
        if(msick1[3] == true){
            canvas.drawCircle((4+xs1)*mwidth/13,8*mheight/20,mheight/radius,paint);
        }
        if(msick2[3] == true){
            canvas.drawCircle((4+xs2)*mwidth/13,10*mheight/20,mheight/radius,paint);
        }
        if(msick3[3] == true){
            canvas.drawCircle((4+xs3)*mwidth/13,12*mheight/20,mheight/radius,paint);
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


















    public int[] AND(int[] checkeda, int[] checkedb){
        int[] result = {0,0,0,0,0,0};
        for(int i = 0; i < 6; i++){
            result[i] = (int) ((checkeda[i] + checkedb[i])/2.);
        }
        return result;
    }


    public int[] NAND(int[] checkeda, int[] checkedb){
        int[] result = {1,1,1,1,1,1};
        for(int i = 0; i < 6; i++){
            if(checkeda[i] == 0 || checkedb[i] == 0){
                if(checkeda[i] != checkedb[i]) {
                    result[i] = 0;
                }
            }
        }
        return result;
    }

    public int[] OR(int[] checkeda, int[] checkedb){
        int[] result = {0,0,0,0,0,0};
        for(int i = 0; i < 6; i++){
            if(checkeda[i] == 1 || checkedb[i] == 1){
                result[i] = 1;
            }
        }
        return result;
    }

    public int[] XOR(int[] checkeda, int[] checkedb){
        int[] result = {0,0,0,0,0,0};
        for(int i = 0; i < 6; i++){
            if(checkeda[i] == 1 || checkedb[i] == 1){
                if(checkeda[i] != checkedb[i]) {
                    result[i] = 1;
                }
            }
        }
        return result;
    }



    public int computecomb(int[] checked){
        int n11 = checked[0]+checked[1]+checked[2];
        int n12 = 3-n11;
        int n21 = checked[3]+checked[4]+checked[5];
        int n22 =  3- n21;


        StatTests Fisher = new StatTests(n11, n12, n21, n22);
        return (int) (10* Fisher.logp);
    }

    public void eval(boolean[] cchecked){
        int[] checked = {0,0,0,0,0};
        for(int i = 0; i < 5; i++){
            if(cchecked[i] == true){
                checked[i] = 1;
            }
        }
        if(checked.length == 0){
            logp = 1;
        }
        else if(checked.length == 1) {
            logp = computecomb(CAND(checked));
        }
        else if(checked.length == 2){
            int templogpa = computecomb(CAND(checked));
            int templogpb = computecomb(COR(checked));
            int templogpc = computecomb(XOR(CAND(new int[] {checked[0]}),CAND(new int[] {checked[1]})));
            int templogpd = computecomb(NAND(CAND(new int[] {checked[0]}),CAND(new int[] {checked[1]})));

            logp = Math.max(Math.max(Math.max(templogpa,templogpb),templogpc),templogpd);
        }
        else if(checked.length == 3){
            logp = computecomb(CAND(checked));
        }
    }


    public int[] COR(int[] checked){
        //int n11 = 0;
        //int n12 = 0;
        //int n21 = 0;
        //int n22 = 0;

        int ch1 = 0;
        int ch2 = 0;
        int ch3 = 0;
        int cs1 = 0;
        int cs2 = 0;
        int cs3 = 0;

        for(int i:checked){
            if(mhealthy1[i] == true){
                ch1 = 1;
            }
            if(mhealthy2[i] == true){
                ch2 = 1;
            }
            if(mhealthy3[i] == true){
                ch3 = 1;
            }
            if(msick1[i] == true){
                cs1 = 1;
            }
            if(msick2[i] == true){
                cs2 = 1;
            }
            if(msick3[i] == true){
                cs3 = 1;
            }
        }

        //n11 = ch1+ch2+ch3;
        //n12 = 3- n11;
        //n22 = cs1 + cs2 + cs3;
        //n21 = 3-n22;

        return new int[] {ch1, ch2, ch3, cs1, cs2, cs3};

        //StatTests Fisher = new StatTests(n11, n12, n21, n22);
        //logp = (int) (10* Fisher.logp);


    }

    public int[] CAND(int[] checked){
        //int n11 = 0;
        //int n12 = 0;
        //int n21 = 0;
        //int n22 = 0;

        int ch1 = 1;
        int ch2 = 1;
        int ch3 = 1;
        int cs1 = 1;
        int cs2 = 1;
        int cs3 = 1;

        for(int i:checked){
            if(mhealthy1[i] != true){
                ch1 = 0;
            }
            if(mhealthy2[i] != true){
                ch2 = 0;
            }
            if(mhealthy3[i] != true){
                ch3 = 0;
            }
            if(msick1[i] != true){
                cs1 = 0;
            }
            if(msick2[i] != true){
                cs2 = 0;
            }
            if(msick3[i] != true){
                cs3 = 0;
            }
        }

        return new int[] {ch1, ch2, ch3, cs1, cs2, cs3};

        //n11 = ch1+ch2+ch3;
        //n12 = 3- n11;
        //n22 = cs1 + cs2 + cs3;
        //n21 = 3-n22;

        //StatTests Fisher = new StatTests(n11, n12, n21, n22);
        //logp = (int) (10* Fisher.logp);


    }



    public void randomize(int num){
        for(int i = 0; i < 5;i++){
            if(i < num){
                mcolors[i] = true;
                mhealthy1[i] = mrand.nextBoolean();
                mhealthy2[i] = mrand.nextBoolean();
                mhealthy3[i] = mrand.nextBoolean();
                msick1[i] = mrand.nextBoolean();
                msick2[i] = mrand.nextBoolean();
                msick3[i] = mrand.nextBoolean();
            }
            else{
                mcolors[i] = false;
                mhealthy1[i] = false;
                mhealthy2[i] = false;
                mhealthy3[i] = false;
                msick1[i] = false;
                msick2[i] = false;
                msick3[i] = false;
            }
        }

    }

    public void inject(int[] patient, int[] feature, boolean[] presence){
        int i = 0;
        while(i < patient.length){
            if(patient[i] == 0){
                mhealthy1[feature[i]] = presence[i];
            }
            else if(patient[i] == 1){
                mhealthy2[feature[i]] = presence[i];
            }
            else if(patient[i] == 2){
                mhealthy3[feature[i]] = presence[i];
            }
            else if(patient[i] == 3){
                msick1[feature[i]] = presence[i];
            }
            else if(patient[i] == 4){
                msick2[feature[i]] = presence[i];
            }
            else if(patient[i] == 5){
                msick3[feature[i]] = presence[i];
            }

            i++;
        }
    }

}
