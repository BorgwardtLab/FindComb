package ch.ethz.tgumbschbsse.findcomb;

/**
 * Created by tgumbsch on 8/10/17.
 */



import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    //declaring gameview
    private GameView gameView;
    private int Level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_mlcb);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Initializing game view object
        Intent intent = getIntent();
        Level = intent.getIntExtra("Level",1);
        gameView = new GameView(this, GameActivity.this);
        System.out.println("Here");
        if(Level==1){
            gameView.reset(1,5,120);
        }
        else if(Level==2){
            gameView.reset(6,10,intent.getIntExtra("score",120));
        }
        else if(Level==3){
            gameView.reset(11,15,intent.getIntExtra("score",120));
        }
        else if(Level==100){
            gameView.make_continous();
        }
        setContentView(gameView);


    }

    public void onBackPressed(){
        // finish(); //This makes the app crash!
    }

    //pausing the game when activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    //running the game when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}