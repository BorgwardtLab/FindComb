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



    // TODO: @tgumbsch, could you add a 'submit' button that takes the name and the score of the player and calls the restSubmitScore function please?

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

    @Override
    public void onClick(View v) {
        String name = mNameEntry.getText().toString();

        //starting game activity
        startActivity(new Intent(this, GameActivity.class));
    }
}
