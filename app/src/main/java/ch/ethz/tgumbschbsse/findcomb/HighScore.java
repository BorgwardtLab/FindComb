package ch.ethz.tgumbschbsse.findcomb;

/**
 * Created by tgumbsch on 8/10/17.
 */
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HighScore extends AppCompatActivity {

    TextView textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);



        //initializing the textViews
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView7 = (TextView) findViewById(R.id.textView9);
        textView8 = (TextView) findViewById(R.id.textView10);

        //Recieve Result
        Intent intent = getIntent();
        //String email = getIntent().getStringExtra("email");
        String name = intent.getExtras().getString("name");
        int score = intent.getExtras().getInt("score");



        // TODO: @tgumbsch, could you add a 'submit' button that takes the name and the score of the player and calls the restSubmitScore function please?
        //RestApiUsage Communication = new RestApiUsage();
        //Communication.postResult(name,(float)score);
        //Communication.getTopTenTimeline();




        textView.setText("1. " + name + " " + String.valueOf(score));




    }
}