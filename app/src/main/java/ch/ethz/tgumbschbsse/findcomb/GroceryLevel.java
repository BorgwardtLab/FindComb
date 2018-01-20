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
    public GroceryItem mc11,mc12, mc13, mc14, mc15;
    public GroceryItem mc21, mc22, mc23, mc24, mc25;
    public GroceryItem mc31, mc32, mc33, mc34, mc35;
    public GroceryItem mc41, mc42, mc43, mc44, mc45;
    public GroceryItem mc51, mc52, mc53, mc54, mc55;
    public GroceryItem mc61, mc62, mc63, mc64, mc65;


    private Random mrand;




    private int mwidth;
    private int mheight;

    public boolean clicked;
    public boolean[] entered;
    public int mscore;



    public GroceryLevel(Context context, int width, int height, int[] c1, int[] c2, int[] c3, int[] c4, int[] c5, int[] c6){
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
            mc11 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc11 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc21 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc21 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc31 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc31 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc41 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc41 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc51 = new GroceryItem((2+x)*mwidth/13, 11*mheight/20, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc51 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc61 = new GroceryItem((2+x)*mwidth/13, 13*mheight/20, 0.1, mcontext, mitems[mcustomer6[x]]);  //new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc61 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }


        x += 1;

        if(mcustomer1.length > x){
            mc12 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc12 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc22 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc22 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc32 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc32 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc42 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc42 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc52 = new GroceryItem((2+x)*mwidth/13, 11*mheight/20, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc52 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc62 = new GroceryItem((2+x)*mwidth/13, 13*mheight/20, 0.1, mcontext, mitems[mcustomer6[x]]);  //new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc62 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }



        x += 1;

        if(mcustomer1.length > x){
            mc13 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc13 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc23 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc23 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc33 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc33 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc43 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc43 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc53 = new GroceryItem((2+x)*mwidth/13, 11*mheight/20, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc53 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc63 = new GroceryItem((2+x)*mwidth/13, 13*mheight/20, 0.1, mcontext, mitems[mcustomer6[x]]);  //new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc63 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }


        x += 1;

        if(mcustomer1.length > x){
            mc14 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc14 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc24 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc24 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc34 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc34 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc44 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc44 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc54 = new GroceryItem((2+x)*mwidth/13, 11*mheight/20, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc54 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc64 = new GroceryItem((2+x)*mwidth/13, 13*mheight/20, 0.1, mcontext, mitems[mcustomer6[x]]);  //new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc64 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }


        x += 1;

        if(mcustomer1.length > x){
            mc15 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc15 = new GroceryItem((2+x)*mwidth/13, 3*mheight/20, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc25 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc25 = new GroceryItem((2+x)*mwidth/13, 5*mheight/20, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc35 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc35 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc45 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc45 =  new GroceryItem((2+x)*mwidth/13, 9*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc55 = new GroceryItem((2+x)*mwidth/13, 11*mheight/20, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc55 = new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer6.length > x){
            mc65 = new GroceryItem((2+x)*mwidth/13, 13*mheight/20, 0.1, mcontext, mitems[mcustomer6[x]]);  //new Picture(mitems[mcustomer6[x]], mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc65 =  new GroceryItem((2+x)*mwidth/13, 7*mheight/20, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,11*mheight/20,mheight/radius,mheight/radius);
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
        mc11.update();
        mc12.update();
        mc13.update();
        mc14.update();
        mc15.update();
        mc21.update();
        mc22.update();
        mc23.update();
        mc24.update();
        mc25.update();
        mc31.update();
        mc32.update();
        mc33.update();
        mc34.update();
        mc35.update();
        mc41.update();
        mc42.update();
        mc43.update();
        mc44.update();
        mc45.update();
        mc51.update();
        mc52.update();
        mc53.update();
        mc54.update();
        mc55.update();
        mc61.update();
        mc62.update();
        mc63.update();
        mc64.update();
        mc65.update();

        //Check now for other clicked similar items


    }

    public void checkClicked(Point point){

        int px = point.x;
        int py = point.y;

        mc11.checkClicked(point);
        mc12.checkClicked(point);
        mc13.checkClicked(point);
        mc14.checkClicked(point);
        mc15.checkClicked(point);
        mc21.checkClicked(point);
        mc22.checkClicked(point);
        mc23.checkClicked(point);
        mc24.checkClicked(point);
        mc25.checkClicked(point);
        mc31.checkClicked(point);
        mc32.checkClicked(point);
        mc33.checkClicked(point);
        mc34.checkClicked(point);
        mc35.checkClicked(point);
        mc41.checkClicked(point);
        mc42.checkClicked(point);
        mc43.checkClicked(point);
        mc44.checkClicked(point);
        mc45.checkClicked(point);
        mc51.checkClicked(point);
        mc52.checkClicked(point);
        mc53.checkClicked(point);
        mc54.checkClicked(point);
        mc55.checkClicked(point);
        mc61.checkClicked(point);
        mc62.checkClicked(point);
        mc63.checkClicked(point);
        mc64.checkClicked(point);
        mc65.checkClicked(point);



        if(point.x > 15*mwidth/20 && point.x < 19*mwidth/20){
            if(point.y >3*mheight/20 && point.y < 3*mheight/5) {
                System.out.print("Clicked finish");
                clicked = true;
            }
        }

    }


    public void random_new(){
        mcustomer1 = new int[mrand.nextInt(5)];
        mcustomer2 = new int[mrand.nextInt(5)];
        mcustomer3 = new int[mrand.nextInt(5)];
        mcustomer4 = new int[mrand.nextInt(5)];
        mcustomer5 = new int[mrand.nextInt(5)];
        mcustomer6 = new int[mrand.nextInt(5)];
        for(int i = 0; i<5; i++){
            if(i<mcustomer1.length) {
                mcustomer1[i] = mrand.nextInt(mitems.length);
                System.out.println("Fill array");

                System.out.println(mcustomer1[i]);
            }
            if(i<mcustomer2.length) {
                mcustomer2[i] = mrand.nextInt(mitems.length);
            }
            if(i<mcustomer3.length) {
                mcustomer3[i] = mrand.nextInt(mitems.length);
            }
            if(i<mcustomer4.length) {
                mcustomer4[i] = mrand.nextInt(mitems.length);
            }
            if(i<mcustomer5.length) {
                mcustomer5[i] = mrand.nextInt(mitems.length);
            }
            if(i<mcustomer6.length) {
                mcustomer6[i] = mrand.nextInt(mitems.length);
            }
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

        //mscore = tf * idf; //Not good

        int score = 0;
        k = 0;
        while(k < 7){
            if (entered[k]==true){
                for(int i: mcustomer1){
                    if(i==k){
                        score += 1;
                    }
                }
                for(int i: mcustomer2){
                    if(i==k){
                        score += 1;
                    }
                }
                for(int i: mcustomer3){
                    if(i==k){
                        score += 1;
                    }
                }
                for(int i: mcustomer4){
                    if(i==k){
                        score += 1;
                    }
                }
                for(int i: mcustomer5){
                    if(i==k){
                        score += 1;
                    }
                }
                for(int i: mcustomer6){
                    if(i==k){
                        score += 1;
                    }
                }
            }
            k++;
        }

        mscore = score;


    }

}
