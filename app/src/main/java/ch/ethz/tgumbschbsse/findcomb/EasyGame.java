package ch.ethz.tgumbschbsse.findcomb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EasyGame extends AppCompatActivity {
    private TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);

        mName = (TextView) findViewById(R.id.tv_name);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            String name = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mName.setText("Hello " + name);
        }
    }
}
