package ch.ethz.tgumbschbsse.findcomb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

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

        mEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mNameEntry.getText().toString();
                Context context = MainActivity.this;
                Class destinationActivity = EasyGame.class;
                Intent startGame = new Intent(context, destinationActivity);
                startGame.putExtra(Intent.EXTRA_TEXT, name);
                startActivity(startGame);
            }
        });


    }
}
