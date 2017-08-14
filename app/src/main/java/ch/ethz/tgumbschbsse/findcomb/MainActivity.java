package ch.ethz.tgumbschbsse.findcomb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

//public class MainActivity extends Activity {
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mNameEntry;
    private ImageView mImageView;
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
        //mImageView = (ImageView) findViewById(R.id.Logo);
        //Bitmap ethlogo = BitmapFactory.decodeResource(getResources(), R.drawable.eth);
        //ethlogo = getResizedBitmap(ethlogo,800,200);
        //mImageView.setImageBitmap(ethlogo);
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
                if(score > 0) {
                    String name = mNameEntry.getText().toString();
                    //String email = mEmail.getText().toString();
                    Intent intent = new Intent(this, HighScore.class);
                    intent.putExtra("name", name);
                    intent.putExtra("score", score);
                    this.startActivity(intent);
                }
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
