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

    public GroceryItem[] items;


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
            mc11 = new GroceryItem((x+3)*mwidth/10, 3*mheight/23, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc11 = new GroceryItem((x)*mwidth/10, 3*mheight/23, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc21 = new GroceryItem((x+3)*mwidth/10, 6*mheight/23, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc21 = new GroceryItem((x)*mwidth/10, 6*mheight/23, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc31 = new GroceryItem((x+3)*mwidth/10, 9*mheight/23, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc31 =  new GroceryItem((x)*mwidth/10, 9*mheight/23, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc41 =  new GroceryItem((x+3)*mwidth/10, 12*mheight/23, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc41 =  new GroceryItem((x)*mwidth/10, 12*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc51 = new GroceryItem((x+3)*mwidth/10, 15*mheight/23, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc51 = new GroceryItem((x)*mwidth/10, 15*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }



        x += 1;

        if(mcustomer1.length > x){
            mc12 = new GroceryItem((x+3)*mwidth/10, 3*mheight/23, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc12 = new GroceryItem((x)*mwidth/10, 3*mheight/23, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc22 = new GroceryItem((x+3)*mwidth/10, 6*mheight/23, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc22 = new GroceryItem((x)*mwidth/10, 6*mheight/23, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc32 = new GroceryItem((x+3)*mwidth/10, 9*mheight/23, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc32 =  new GroceryItem((x)*mwidth/10, 9*mheight/23, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc42 =  new GroceryItem((x+3)*mwidth/10, 12*mheight/23, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc42 =  new GroceryItem((x)*mwidth/10, 12*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc52 = new GroceryItem((x+3)*mwidth/10, 15*mheight/23, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc52 = new GroceryItem((x)*mwidth/10, 15*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }





        x += 1;

        if(mcustomer1.length > x){
            mc13 = new GroceryItem((x+3)*mwidth/10, 3*mheight/23, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc13 = new GroceryItem((x)*mwidth/10, 3*mheight/23, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc23 = new GroceryItem((x+3)*mwidth/10, 6*mheight/23, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc23 = new GroceryItem((x)*mwidth/10, 6*mheight/23, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc33 = new GroceryItem((x+3)*mwidth/10, 9*mheight/23, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc33 =  new GroceryItem((x)*mwidth/10, 9*mheight/23, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc43 =  new GroceryItem((x+3)*mwidth/10, 12*mheight/23, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc43 =  new GroceryItem((x)*mwidth/10, 12*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc53 = new GroceryItem((x+3)*mwidth/10, 15*mheight/23, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc53 = new GroceryItem((x)*mwidth/10, 15*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        x += 1;

        if(mcustomer1.length > x){
            mc14 = new GroceryItem((x+3)*mwidth/10, 3*mheight/23, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc14 = new GroceryItem((x)*mwidth/10, 3*mheight/23, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc24 = new GroceryItem((x+3)*mwidth/10, 6*mheight/23, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc24 = new GroceryItem((x)*mwidth/10, 6*mheight/23, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc34 = new GroceryItem((x+3)*mwidth/10, 9*mheight/23, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc34 =  new GroceryItem((x)*mwidth/10, 9*mheight/23, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc44 =  new GroceryItem((x+3)*mwidth/10, 12*mheight/23, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc44 =  new GroceryItem((x)*mwidth/10, 12*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc54 = new GroceryItem((x+3)*mwidth/10, 15*mheight/23, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc54 = new GroceryItem((x)*mwidth/10, 15*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }


        x += 1;

        if(mcustomer1.length > x){
            mc15 = new GroceryItem((x+3)*mwidth/10, 3*mheight/23, 0.1, mcontext, mitems[mcustomer1[x]]);
        }
        else{
            mc15 = new GroceryItem((x)*mwidth/10, 3*mheight/23, 0.1, mcontext, 0);
        }


        if(mcustomer2.length > x){
            mc25 = new GroceryItem((x+3)*mwidth/10, 6*mheight/23, 0.1, mcontext, mitems[mcustomer2[x]]);
        }
        else{
            mc25 = new GroceryItem((x)*mwidth/10, 6*mheight/23, 0.1, mcontext, 0);
        }

        if(mcustomer3.length > x){
            mc35 = new GroceryItem((x+3)*mwidth/10, 9*mheight/23, 0.1, mcontext, mitems[mcustomer3[x]]); //new Picture(mitems[mcustomer3[x]], mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc35 =  new GroceryItem((x)*mwidth/10, 9*mheight/23, 0.1, mcontext, 0); //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,5*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer4.length > x){
            mc45 =  new GroceryItem((x+3)*mwidth/10, 12*mheight/23, 0.1, mcontext, mitems[mcustomer4[x]]);  //new Picture(mitems[mcustomer4[x]], mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc45 =  new GroceryItem((x)*mwidth/10, 12*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,7*mheight/20,mheight/radius,mheight/radius);
        }


        if(mcustomer5.length > x){
            mc55 = new GroceryItem((x+3)*mwidth/10, 15*mheight/23, 0.1, mcontext, mitems[mcustomer5[x]]);  // new Picture(mitems[mcustomer5[x]], mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }
        else{
            mc55 = new GroceryItem((x)*mwidth/10, 15*mheight/23, 0.1, mcontext, 0);  //new Picture(R.drawable.none, mcontext, (4+x)*mwidth/13,9*mheight/20,mheight/radius,mheight/radius);
        }

        items = new GroceryItem[] {mc11, mc12,mc13,mc14,mc15, mc21, mc22,mc23,mc24,mc25, mc31, mc32,mc33,mc34,mc35, mc41, mc42,mc43,mc44,mc45, mc51, mc52,mc53,mc54,mc55};


        reset();

    }

    public void reset(){
        clicked = false;
        entered = new boolean[] {false,false,false,false,false,false,false,false,false,false,false};

    }

    @Override
    public void draw(Canvas canvas) {



        for(GroceryItem gi: items){
            gi.draw(canvas);
        }


        Paint paint = new Paint();

        paint.setTextSize(mheight/23);
        canvas.drawText(mcontext.getString(R.string.C1),2*mwidth/40, 2*mheight/23,paint);
        canvas.drawText(mcontext.getString(R.string.C2),2*mwidth/40, 5*mheight/23,paint);
        canvas.drawText(mcontext.getString(R.string.C3),2*mwidth/40, 8*mheight/23,paint);
        canvas.drawText(mcontext.getString(R.string.C4),2*mwidth/40, 11*mheight/23,paint);
        canvas.drawText(mcontext.getString(R.string.C5),2*mwidth/40, 14*mheight/23,paint);
        
        
        canvas.drawRect(new Rect(mwidth/40, 3*mheight/23, 5*mwidth/7, 3*mheight/23+2), paint);
        canvas.drawRect(new Rect(mwidth/40, 6*mheight/23, 5*mwidth/7, 6*mheight/23+2), paint);
        canvas.drawRect(new Rect(mwidth/40, 9*mheight/23, 5*mwidth/7, 9*mheight/23+2), paint);
        canvas.drawRect(new Rect(mwidth/40, 12*mheight/23, 5*mwidth/7, 12*mheight/23+2), paint);


    }

    @Override
    public void update() {

        for(GroceryItem gi: items){
            gi.update();
        }


    }

    public void checkClicked(Point point){

        int px = point.x;
        int py = point.y;

        for(GroceryItem gi:items){
            gi.checkClicked(point);
            if(gi.toggled == true){
                int k = 0;
                for (int itm:mitems){
                    if(gi.mgiindex == itm){
                        entered[k] ^= true;
                    }
                    k+= 1;
                }
                for(GroceryItem gi_:items){
                    if(gi_.mgiindex == gi.mgiindex && gi_.toggled != true){
                        gi_.toggle();
                    }
                    if(gi_.mgiindex != gi.mgiindex && gi_.mclicked == true){
                        // Sync blinking
                        gi_.toggle();
                        gi_.toggle();
                    }
                }
                gi.toggled = false;
            }
        }


        if(point.x > 15*mwidth/20 && point.x < 19*mwidth/20){
            if(point.y >3*mheight/20 && point.y < 3*mheight/5) {
                System.out.print("Clicked finish");
                clicked = true;
            }
        }

    }


    public void random_new(){
        mcustomer1 = new int[mrand.nextInt(5)+2];
        mcustomer2 = new int[mrand.nextInt(5)+2];
        mcustomer3 = new int[mrand.nextInt(5)+2];
        mcustomer4 = new int[mrand.nextInt(5)+2];
        mcustomer5 = new int[mrand.nextInt(5)+2];
        int next = 0;
        int j = 0;
        boolean no_duplicate = false;
        boolean all = false;

        for(int i = 0; i<5; i++){
            if(i<mcustomer1.length) {
                no_duplicate = false;
                while(no_duplicate == false) {
                    next = mrand.nextInt(mitems.length);
                    j = 0;
                    all = true;
                    while (j < i) {
                        if(next==mcustomer1[j]){
                            all = false;
                        }
                        j++;
                    }
                    if(all == true){
                        no_duplicate = true;
                    }
                }
                mcustomer1[i] = next;
            }
            if(i<mcustomer2.length) {
                no_duplicate = false;
                while(no_duplicate == false) {
                    next = mrand.nextInt(mitems.length);
                    j = 0;
                    all = true;
                    while (j < i) {
                        if(next==mcustomer2[j]){
                            all = false;
                        }
                        j++;
                    }
                    if(all == true){
                        no_duplicate = true;
                    }
                }
                mcustomer2[i] = next;
            }
            if(i<mcustomer3.length) {
                no_duplicate = false;
                while(no_duplicate == false) {
                    next = mrand.nextInt(mitems.length);
                    j = 0;
                    all = true;
                    while (j < i) {
                        if(next==mcustomer3[j]){
                            all = false;
                        }
                        j++;
                    }
                    if(all == true){
                        no_duplicate = true;
                    }
                }
                mcustomer3[i] = next;
            }
            if(i<mcustomer4.length) {
                no_duplicate = false;
                while(no_duplicate == false) {
                    next = mrand.nextInt(mitems.length);
                    j = 0;
                    all = true;
                    while (j < i) {
                        if(next==mcustomer4[j]){
                            all = false;
                        }
                        j++;
                    }
                    if(all == true){
                        no_duplicate = true;
                    }
                }
                mcustomer4[i] = next;
            }
            if(i<mcustomer5.length) {
                no_duplicate = false;
                while(no_duplicate == false) {
                    next = mrand.nextInt(mitems.length);
                    j = 0;
                    all = true;
                    while (j < i) {
                        if(next==mcustomer5[j]){
                            all = false;
                        }
                        j++;
                    }
                    if(all == true){
                        no_duplicate = true;
                    }
                }
                mcustomer5[i] = next;
            }
        }

        init_new_images();
    }



    public void compute_score(){
        // We compute tf-idf score
        // tf = #(t,D0) / max#(t',D)

        int score = 0;
        int cardinality = -3;

        for(boolean e: entered){
            if(e==true){
                cardinality += 2;
            }
        }
        if(cardinality != -3) {
            int i = 0;
            boolean count = true;
            for (boolean e : entered) {
                boolean in = true;
                if (e == true) {
                    in = false;
                    for (int itm : mcustomer1) {
                        if (itm == i) {
                            in = true;
                        }
                    }
                }
                if (in == false) {
                    count = false;
                }
                i++;
            }
            if (count == true) {
                score += cardinality;
            }


            i = 0;
            count = true;
            for (boolean e : entered) {
                boolean in = true;
                if (e == true) {
                    in = false;
                    for (int itm : mcustomer2) {
                        if (itm == i) {
                            in = true;
                        }
                    }
                }
                if (in == false) {
                    count = false;
                }
                i++;
            }
            if (count == true) {
                score += cardinality;
            }


            i = 0;
            count = true;
            for (boolean e : entered) {
                boolean in = true;
                if (e == true) {
                    in = false;
                    for (int itm : mcustomer3) {
                        if (itm == i) {
                            in = true;
                        }
                    }
                }
                if (in == false) {
                    count = false;
                }
                i++;
            }
            if (count == true) {
                score += cardinality;
            }


            i = 0;
            count = true;
            for (boolean e : entered) {
                boolean in = true;
                if (e == true) {
                    in = false;
                    for (int itm : mcustomer4) {
                        if (itm == i) {
                            in = true;
                        }
                    }
                }
                if (in == false) {
                    count = false;
                }
                i++;
            }
            if (count == true) {
                score += cardinality;
            }


            i = 0;
            count = true;
            for (boolean e : entered) {
                boolean in = true;
                if (e == true) {
                    in = false;
                    for (int itm : mcustomer5) {
                        if (itm == i) {
                            in = true;
                        }
                    }
                }
                if (in == false) {
                    count = false;
                }
                i++;
            }
            if (count == true) {
                score += cardinality;
            }


        }

        mscore = score;


    }

}
