package ch.ethz.tgumbschbsse.findcomb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class EasyGame extends Activity {
    //private TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GamePanel(this));

        //setContentView(R.layout.activity_easy_game);

        //mName = (TextView) findViewById(R.id.tv_name);

        //Intent intentThatStartedThisActivity = getIntent();

        //if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            //String name = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            //mName.setText("Hello " + name);
        //}
    }
}
