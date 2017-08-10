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
    private Button mEasy;
    private Button mHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEntry = (EditText) findViewById(R.id.et_name);
        mEasy = (Button) findViewById(R.id.b_easy);
        mHard = (Button) findViewById(R.id.b_hard);

        //setting the orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        //adding a click listener
        mEasy.setOnClickListener(this);
    }


    /*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        mNameEntry = (EditText) findViewById(R.id.et_name);
        mEasy = (Button) findViewById(R.id.b_easy);
        mHard = (Button) findViewById(R.id.b_hard);
        mEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mNameEntry.getText().toString();
                Context context = MainActivity.this;

                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                startActivity(new Intent(context, GameActivity.class));
                setContentView(new GamePanel(context, name));


                // TODO: @tgumbsch, could you add a 'submit' button that takes the name and the score of the player and calls the restSubmitScore function please?
            }
        });

        mEasy.setOnClickListener(this);
    }
      */

    @Override
    public void onClick(View v) {
        String name = mNameEntry.getText().toString();

        //starting game activity
        startActivity(new Intent(this, GameActivity.class));
    }
}
