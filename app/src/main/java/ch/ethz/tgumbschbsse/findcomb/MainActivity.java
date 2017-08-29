package ch.ethz.tgumbschbsse.findcomb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.text.TextWatcher;
import android.text.Editable;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//public class MainActivity extends Activity {
public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnApiRequestCompleted {

    private EditText mNameEntry;
    private ImageView mImageView;
    //private EditText mEmail;
    private Button mEasy;
    //private Button mHard;

    public final static int REQUEST_CODE = 1;
    public final static int REQUEST_ONE = 5;
    public final static int REQUEST_TWO = 6;
    public final static int REQUEST_THREE = 7;
    public final static int REQUEST_FOUR = 8;
    public final static int REQUEST_FIVE = 9;

    private Intent intent;
    private static int LGLOBAL = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mNameEntry = (EditText) findViewById(R.id.et_name);
        //mEmail = (EditText) findViewById(R.id.et_email);
        mEasy = (Button) findViewById(R.id.b_easy);
        mNameEntry.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    mEasy.setEnabled(false);
                    mEasy.setTextColor(Color.parseColor("#FF999A9A"));
                    mEasy.setBackgroundColor(Color.parseColor("#FFCECFCF"));

                } else {
                    mEasy.setEnabled(true);
                    mEasy.setTextColor(Color.parseColor("white"));
                    mEasy.setBackgroundColor(Color.parseColor("#FF373FAC"));
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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
            startActivityForResult(new Intent(this,TutorialActivity.class), REQUEST_ONE);
        }
        //if(v == mHard){
        //    startActivity(new Intent(MainActivity.this, HighScore.class));
        //}
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE:
                int score = data.getExtras().getInt("score");
//                if(score > 0) {
                    String name = mNameEntry.getText().toString();
                    //String email = mEmail.getText().toString();
                    // post score
                    RequestParams params = new RequestParams();
                    params.put("username", "admin");
                    params.put("password", "mlcb2017");
                    params.put("user", name);
                    params.put("score", score);
                    intent =  new Intent(this, HighScore.class);
                    intent.putExtra("name", name);
                    intent.putExtra("score", score);
                    intent.putExtra("global", LGLOBAL);
                    if(LGLOBAL == 1) {
                        Api postApi = new Api(this);
                        try {
                            Api.post(params);
                            System.out.println("Post ok");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        intent.putExtra("position", 15);
                        this.startActivity(intent);
                        //Post to local leaderboard
                    }
                    break;
            case REQUEST_ONE:
                startActivityForResult(new Intent(this, GameActivity.class),REQUEST_CODE);

//                }
        }
    }
    @Override
    public void taskCompleted(JSONArray response) {
        System.out.println("finished posting");
        System.out.println(response);

        int position = 0;
        try {
            JSONObject positionjson = (JSONObject) response.get(0);
            position = positionjson.getInt("position");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(position);
        intent.putExtra("position", position);
        this.startActivity(intent);

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
