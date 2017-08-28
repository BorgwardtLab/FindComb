package ch.ethz.tgumbschbsse.findcomb;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by tgumbsch on 8/28/17.
 */

public class TutorialCombActivity extends AppCompatActivity {

    //declaring gameview

    private TutorialComb gameView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gameView2 = new TutorialComb(this, TutorialCombActivity.this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        //adding it to contentview
        setContentView(gameView2);



    }

    //pausing the game when activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        gameView2.pause();
    }

    //running the game when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        gameView2.resume();
    }
}