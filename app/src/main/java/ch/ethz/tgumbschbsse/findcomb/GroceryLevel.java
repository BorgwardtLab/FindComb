package ch.ethz.tgumbschbsse.findcomb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by tgumbsch on 1/19/18.
 */


public class GroceryLevel implements  GameObject{
    private int[] mcustomer1;
    private int[] mcustomer2;
    private int[] mcustomer3;
    private int[] mcustomer4;
    private int[] mcustomer5;
    private int[] mcustomer6;


    Context mcontext;
    private int[] mitems = {
            R.drawable.cannedsoda,
            R.drawable.cookie_bite,
            R.drawable.fish_final,
            R.drawable.bananas_multiple,
            R.drawable.bread,
            R.drawable.cake,
            R.drawable.onions,
            R.drawable.orange,
            R.drawable.pear,
            R.drawable.strawberry,
            R.drawable.waterbottle}; // 11 items

    // Pictures of bought items, to be filled according to mcustomer lists
    public Picture mc11, mc12, mc13, mc14, mc15;
    public Picture mc21, mc22, mc23, mc24, mc25;
    public Picture mc31, mc32, mc33, mc34, mc35;
    public Picture mc41, mc42, mc43, mc44, mc45;
    public Picture mc51, mc52, mc53, mc54, mc55;
    public Picture mc61, mc62, mc63, mc64, mc65;


    private Random mrand;




    private int mwidth;
    private int mheight;

    // Circle selected objects?!? in red?!?
    private int mred =Color.rgb(255,0,0);

    public boolean clicked;
    public boolean[] entered;
    public double mtf_idf;



    public GroceryLevel(Context context, int width, int height, boolean type, int[] c1, int[] c2, int[] c3, int[] c4, int[] c5, int[] c6){
        mcustomer1 = c1;
        mcustomer2 = c2;
        mcustomer3 = c3;
        mcustomer4 = c4;
        mcustomer5 = c5;
        mcustomer6 = c6;
        mwidth = width;
        mheight = height;
        clicked = false;
        entered = new boolean[] {false,false,false,false,false};
        mcontext = context;

        mrand = new Random();
        mrand.setSeed(System.currentTimeMillis());

        init_new_images();




    }

    private void init_new_images(){
        int i = 2;
        int radius = 18;

        radius = 22;


        int x = 0;

        if(mcustomer1.length > x){
            mc11 = new Picture(mitems[mcustomer1[x]], mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc11 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }

        if(mcustomer2.length > x){
            mc21 = new Picture(mitems[mcustomer2[x]], mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc21 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer3.length > x){
            mc31 = new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc31 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc41 = new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc41 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc51 = new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc51 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc61 = new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc61 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }


        x += 1;


        if(mcustomer1.length > x){
            mc12 = new Picture(mitems[mcustomer1[x]], mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc12 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }

        if(mcustomer2.length > x){
            mc22 = new Picture(mitems[mcustomer2[x]], mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc22 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer3.length > x){
            mc32 = new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc32 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc42 = new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc42 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc52 = new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc52 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc62 = new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc62 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }

        x += 1;


        if(mcustomer1.length > x){
            mc13 = new Picture(mitems[mcustomer1[x]], mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc13 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }

        if(mcustomer2.length > x){
            mc23 = new Picture(mitems[mcustomer2[x]], mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc23 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer3.length > x){
            mc33 = new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc33 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc43 = new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc43 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc53 = new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc53 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc63 = new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc63 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }

        x += 1;


        if(mcustomer1.length > x){
            mc14 = new Picture(mitems[mcustomer1[x]], mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc14 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }

        if(mcustomer2.length > x){
            mc24 = new Picture(mitems[mcustomer2[x]], mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc24 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer3.length > x){
            mc34 = new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc34 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc44 = new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc44 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc54 = new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc54 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc64 = new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc64 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }

        x += 1;


        if(mcustomer1.length > x){
            mc15 = new Picture(mitems[mcustomer1[x]], mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc15 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,mheight/20,mheight/radius,mheight/radius);
        }

        if(mcustomer2.length > x){
            mc25 = new Picture(mitems[mcustomer2[x]], mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc25 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,3*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer3.length > x){
            mc35 = new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc35 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc45 = new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc45 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc55 = new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc55 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc65 = new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc65 = new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }


        reset();

    }

    public void reset(){
        clicked = false;
        entered = new boolean[] {false,false,false,false,false,false,false,false,false,false,false};

    }

    @Override
    public void draw(Canvas canvas) {

        mc11.draw(canvas);
        mc12.draw(canvas);
        mc13.draw(canvas);
        mc14.draw(canvas);
        mc15.draw(canvas);
        mc21.draw(canvas);
        mc22.draw(canvas);
        mc23.draw(canvas);
        mc24.draw(canvas);
        mc25.draw(canvas);
        mc31.draw(canvas);
        mc32.draw(canvas);
        mc33.draw(canvas);
        mc34.draw(canvas);
        mc35.draw(canvas);
        mc41.draw(canvas);
        mc42.draw(canvas);
        mc43.draw(canvas);
        mc44.draw(canvas);
        mc45.draw(canvas);
        mc51.draw(canvas);
        mc52.draw(canvas);
        mc53.draw(canvas);
        mc54.draw(canvas);
        mc55.draw(canvas);
        mc61.draw(canvas);
        mc62.draw(canvas);
        mc63.draw(canvas);
        mc64.draw(canvas);
        mc65.draw(canvas);


        Paint paint = new Paint();

        canvas.drawRect(new Rect(3*mheight/7, mheight/10-1, 5*mwidth/7, mheight/10+1), paint);
        canvas.drawRect(new Rect(3*mheight/7, 2*mheight/10-1, 5*mwidth/7, 2*mheight/10+1), paint);
        canvas.drawRect(new Rect(3*mheight/7, 9*mheight/20-1, 5*mwidth/7, 9*mheight/20+1), paint);
        canvas.drawRect(new Rect(3*mheight/7, 11*mheight/20-1, 5*mwidth/7, 11*mheight/20+1), paint);


    }

    @Override
    public void update() {
        /*
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
        */

    }

    public void checkClicked(Point point){
        /* REWRITE MECHANICS OF CLICKING PNG IMAGE!!!

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
        */

        if(point.x > 15*mwidth/20 && point.x < 19*mwidth/20){
            if(point.y >3*mheight/20 && point.y < 3*mheight/5) {
                clicked = true;
            }
        }

    }


    public void random_new(){
        for(int i = 0; i<5; i++){
            mcustomer1[i] = mrand.nextInt(mitems.length);
            mcustomer2[i] = mrand.nextInt(mitems.length);
            mcustomer3[i] = mrand.nextInt(mitems.length);
            mcustomer4[i] = mrand.nextInt(mitems.length);
            mcustomer5[i] = mrand.nextInt(mitems.length);
            mcustomer6[i] = mrand.nextInt(mitems.length);
        }

        init_new_images();
    }



    public void compute_score(){
        // We compute tf-idf score
        // tf = #(t,D0) / max#(t',D)

        int term[] = {};
        int k = 0;
        int j = 0;
        for(boolean i : entered){
            if(i==true){
                term[j] = k;
                j++;
            }
            k++;
        }

        double tf = 0.; //tf = #(t,D0) / max#(t',D)
        double idf = 0.; //idf = log(N / sum_t in D   1 )

        mtf_idf = tf * idf;


    }

}
