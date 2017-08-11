package ch.ethz.tgumbschbsse.findcomb;
import org.json.*;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.entity.mime.Header;

/**
 * Created by tomatteo on 8/9/17.
 */

public class RestApiUsage {
    public void getTopTenTimeline() throws JSONException{
        System.out.println("Here");
        Api.get_top_n(10, null, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // If the response is JSONObject instead of expected JSONArray
//            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                String tweetText = " ";
                try {
                    JSONArray firstEvent = (JSONArray) response.get(0);
                    tweetText = firstEvent.getString(0);
                }catch (JSONException e){
                    e.printStackTrace();
                }

                // Do something with the response
                System.out.println(tweetText);
            }


        });
    }
    public void postResult(String username, Float score) throws JSONException {
        RequestParams params = new RequestParams();
        params.put("user", "admin");
        params.put("password", "mlcb2017"); //TODO: correct this for the right params (key-value pairs)
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
