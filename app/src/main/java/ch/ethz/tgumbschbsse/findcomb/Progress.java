package ch.ethz.tgumbschbsse.findcomb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;

/**
 * Created by tgumbsch on 8/29/17.
 */

public class Progress implements GameObject {

    private int mState;
    private int mspacing;
    private int mhorizontal;
    private int msize;
    private int mLevels;
    private int mwidth;
    private int mheight;

    private boolean below_thres;
    public final MediaPlayer soundwrong;
    public final MediaPlayer soundtick;
    public final MediaPlayer soundright;

    private int[] topblue = {Color.parseColor("#303F9F"),Color.parseColor("#303F9F")};
    private int[] red = {Color.RED, Color.RED};

    private Rectangle R01, R02, R03, R04, R05, R06, R07, R08, R09, R10, R11; //Vertical lines
    private Circle CBegin, CEnd; //Circles on both sides
    private Rectangle RBar; //The actual bar

    public Progress(int Levels, int width, int height, Context context){
        mState = 2;
        mLevels = Levels;
        mwidth = width;
        mheight = height;

        mspacing = width/(mLevels + 2);
        msize = height/20;
        mhorizontal = 4*height/5;

        int[] black = {Color.BLACK, Color.BLACK};
        R01 = new Rectangle(new Rect(mspacing-2, mhorizontal-msize, mspacing+2, mhorizontal+msize), black);
        R02 = new Rectangle(new Rect(2*mspacing-2, mhorizontal-msize, 2*mspacing+2, mhorizontal+msize), black);
        R03 = new Rectangle(new Rect(3*mspacing-2, mhorizontal-msize, 3*mspacing+2, mhorizontal+msize), black);
        R04 = new Rectangle(new Rect(4*mspacing-2, mhorizontal-msize, 4*mspacing+2, mhorizontal+msize), black);
        R05 = new Rectangle(new Rect(5*mspacing-2, mhorizontal-msize, 5*mspacing+2, mhorizontal+msize), black);
        R06 = new Rectangle(new Rect(6*mspacing-2, mhorizontal-msize, 6*mspacing+2, mhorizontal+msize), black);
        R07 = new Rectangle(new Rect(7*mspacing-2, mhorizontal-msize, 7*mspacing+2, mhorizontal+msize), black);
        R08 = new Rectangle(new Rect(8*mspacing-2, mhorizontal-msize, 8*mspacing+2, mhorizontal+msize), black);
        R09 = new Rectangle(new Rect(9*mspacing-2, mhorizontal-msize, 9*mspacing+2, mhorizontal+msize), black);
        R10 = new Rectangle(new Rect(10*mspacing-2, mhorizontal-msize, 10*mspacing+2, mhorizontal+msize), black);
        R11 = new Rectangle(new Rect(11*mspacing-2, mhorizontal-msize, 11*mspacing+2, mhorizontal+msize), black);


        CBegin = new Circle(mspacing,mhorizontal,msize,topblue,true);
        CEnd = new Circle(mState*mspacing,mhorizontal,msize,topblue,true);
        RBar = new Rectangle(new Rect(mspacing,mhorizontal-msize,mState*mspacing,mhorizontal+msize),topblue);

        below_thres = false;
        soundwrong = MediaPlayer.create(context,R.raw.buzz);
        soundtick = MediaPlayer.create(context,R.raw.tick);
        soundright = MediaPlayer.create(context,R.raw.stapler);
    }


    @Override
    public void draw(Canvas canvas) {
        CBegin.draw(canvas);
        CEnd.draw(canvas);
        RBar.draw(canvas);

        R01.draw(canvas);
        R02.draw(canvas);
        R03.draw(canvas);
        R04.draw(canvas);
        R05.draw(canvas);
        R06.draw(canvas);
        R07.draw(canvas);
        R08.draw(canvas);
        R09.draw(canvas);
        R10.draw(canvas);
        R11.draw(canvas);
    }

    @Override
    public void update() {
    }

    public void lower(){
        if (below_thres == false){
            below_thres = true;
            soundtick.start();
        }
    }


    public void upper(){
        if (below_thres == true){
            below_thres = false;
            soundtick.stop();
        }
    }

    public void next(){
        mState++;
        CEnd = new Circle(mState*mspacing,mhorizontal,msize,topblue,true);
        RBar = new Rectangle(new Rect(mspacing,mhorizontal-msize,mState*msize,mhorizontal+msize),topblue);
    }

    public void set(int State, int Level, int Continous){
        mState = State+1;
        mLevels = Level;
        mspacing = (int) (mwidth/(mLevels + 1.5));
        msize = mheight/20;
        mhorizontal = 4*mheight/5;

        int[] black = {Color.BLACK, Color.BLACK};
        R01 = new Rectangle(new Rect(mspacing-2, mhorizontal-msize, mspacing+2, mhorizontal+msize), black);
        R02 = new Rectangle(new Rect(2*mspacing-2, mhorizontal-msize, 2*mspacing+2, mhorizontal+msize), black);
        R03 = new Rectangle(new Rect(3*mspacing-2, mhorizontal-msize, 3*mspacing+2, mhorizontal+msize), black);
        R04 = new Rectangle(new Rect(4*mspacing-2, mhorizontal-msize, 4*mspacing+2, mhorizontal+msize), black);
        R05 = new Rectangle(new Rect(5*mspacing-2, mhorizontal-msize, 5*mspacing+2, mhorizontal+msize), black);
        R06 = new Rectangle(new Rect(6*mspacing-2, mhorizontal-msize, 6*mspacing+2, mhorizontal+msize), black);
        R07 = new Rectangle(new Rect(7*mspacing-2, mhorizontal-msize, 7*mspacing+2, mhorizontal+msize), black);
        R08 = new Rectangle(new Rect(8*mspacing-2, mhorizontal-msize, 8*mspacing+2, mhorizontal+msize), black);
        R09 = new Rectangle(new Rect(9*mspacing-2, mhorizontal-msize, 9*mspacing+2, mhorizontal+msize), black);
        R10 = new Rectangle(new Rect(10*mspacing-2, mhorizontal-msize, 10*mspacing+2, mhorizontal+msize), black);
        R11 = new Rectangle(new Rect(11*mspacing-2, mhorizontal-msize, 11*mspacing+2, mhorizontal+msize), black);


        if(Continous==-1) {
            CBegin = new Circle(mspacing,mhorizontal,msize,topblue,true);
            CEnd = new Circle(mState*mspacing,mhorizontal,msize,topblue,true);
            RBar = new Rectangle(new Rect(mspacing, mhorizontal - msize, mState * mspacing, mhorizontal + msize), topblue);
        }
        else{
            if(Continous > mwidth/12) {
                CBegin = new Circle(mspacing, mhorizontal, msize, topblue, true);
                CEnd = new Circle(Math.min(mspacing + Continous, mspacing * mLevels), mhorizontal, msize, topblue, true);
                RBar = new Rectangle(new Rect(mspacing, mhorizontal - msize, Math.min(mspacing + Continous, mspacing * mLevels), mhorizontal + msize), topblue);
            }
            else{
                CBegin = new Circle(mspacing, mhorizontal, msize, red, true);
                CEnd = new Circle(Math.min(mspacing + Continous, mspacing * mLevels), mhorizontal, msize, red, true);
                RBar = new Rectangle(new Rect(mspacing, mhorizontal - msize, Math.min(mspacing + Continous, mspacing * mLevels), mhorizontal + msize), red);

            }
        }
    }





}