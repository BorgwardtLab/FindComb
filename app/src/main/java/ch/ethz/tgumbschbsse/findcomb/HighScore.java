package ch.ethz.tgumbschbsse.findcomb;

/**
 * Created by tgumbsch on 8/10/17.
 */
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.hashCode;


public class HighScore extends AppCompatActivity implements OnApiRequestCompleted {

    static TextView textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;
    static TextView header, textView11;
    public ArrayList<TextView> Scores;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        //initializing the textViews
        header = (TextView) findViewById(R.id.header);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView11 = (TextView) findViewById(R.id.textView11);

        Scores = new ArrayList<>(asList(textView, textView2,textView3, textView4,textView5,textView6, textView7,textView8, textView9,textView10));

        //Recieve Result
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        int score = intent.getExtras().getInt("score");
        int position = intent.getExtras().getInt("position");

        header.setText("Leaderboard:");

        if(position > 10){
            textView11.setText(position + ". " + name + " : \t" + score);
            textView11.setTextColor(Color.parseColor("aqua"));
        } else {
            Scores.get(position-1).setTextColor(Color.parseColor("aqua"));
        }

        Api Communication = new Api(this);
        // Retrieve top 10
        try {
            Api.get_top_n(10, null);
            System.out.println("First call ok");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void taskCompleted(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            String lbString = String.valueOf(i+1) + ". ";
            try {
                JSONObject firstEvent = (JSONObject) response.get(i);
                int lbscore = (int) Float.parseFloat(firstEvent.getString("score")); // TODO: change this if we use float scores
                lbString += firstEvent.getString("user") + " : \t" + lbscore;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Scores.get(i).setText(lbString);
            // Do something with the response
        };
        System.out.println("Data Loaded");
    }


}