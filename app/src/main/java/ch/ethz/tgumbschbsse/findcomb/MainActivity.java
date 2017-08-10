package ch.ethz.tgumbschbsse.findcomb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

//public class MainActivity extends Activity {
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mNameEntry;
    //private EditText mEmail;
    private Button mEasy;
    //private Button mHard;

    public final static int REQUEST_CODE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEntry = (EditText) findViewById(R.id.et_name);
        //mEmail = (EditText) findViewById(R.id.et_email);
        mEasy = (Button) findViewById(R.id.b_easy);
        //mHard = (Button) findViewById(R.id.b_hard);

        //setting the orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        //adding a click listener
        mEasy.setOnClickListener(this);
        //mHard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mEasy) {
            //starting game activity
            startActivityForResult(new Intent(this, GameActivity.class),REQUEST_CODE);
        }
        //if(v == mHard){
        //    startActivity(new Intent(MainActivity.this, HighScore.class));
        //}
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE:
                int score = data.getExtras().getInt("score");
                String name = mNameEntry.getText().toString();
                //String email = mEmail.getText().toString();
                Intent intent = new Intent(this, HighScore.class);
                intent.putExtra("name", name);
                intent.putExtra("score", score);
                this.startActivity(intent);
        }
    }
}
