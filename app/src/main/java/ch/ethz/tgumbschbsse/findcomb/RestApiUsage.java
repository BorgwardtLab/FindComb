package ch.ethz.tgumbschbsse.findcomb;
import org.json.*;
import com.loopj.android.http.*;

import java.util.ArrayList;

import cz.msebera.android.httpclient.entity.mime.Header;

/**
 * Created by tomatteo on 8/9/17.
 */

public class RestApiUsage {
    public ArrayList<String> topTen = new ArrayList<>();
    private boolean done = false;

    public void getTopTenTimeline() throws JSONException{
        Api.get_top_n(10, null, topTen, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                System.out.println("In onSuccess");
                for (int i = 0; i < response.length(); i++) {
                    String lbString = String.valueOf(i+1) + ". ";
                    try {
                        JSONObject firstEvent = (JSONObject) response.get(i);
                        lbString += firstEvent.getString("user") + " : \t" + firstEvent.getString("score");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Do something with the response
                    System.out.println(lbString);
                    topTen.add(lbString);
                }
            }
        });
    }

    public void postResult(String username, Float score) throws JSONException {
        RequestParams params = new RequestParams();
        params.put("username", "admin");
        params.put("password", "mlcb2017");
        Api.post(params, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // If the response is JSONObject instead of expected JSONArray
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
//                // Pull out the first event on the public timeline
//                JSONObject firstEvent = timeline.get(0);
//                String tweetText = firstEvent.getString("text");
//
//                // Do something with the response
//                System.out.println(tweetText);
//            }
        });
    }
}
