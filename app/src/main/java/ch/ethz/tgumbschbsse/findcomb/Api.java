package ch.ethz.tgumbschbsse.findcomb;
import android.net.Uri;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by tomatteo on 8/9/17.
 */

public class Api {
    private static final String BASE_URL = "http://10.2.176.249:8000/api/scores/"; // TODO: add final server port URL, this one is for temporary development, bs-borgwrdt01 is 195.176.122.97:8000/api/scores/, local is 10.0.2.2, matteo computer on eth5 is 10.2.176.249:8000
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static OnApiRequestCompleted apiListener;

    public Api(OnApiRequestCompleted listener) {
        client = new AsyncHttpClient();
        apiListener = listener;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void get_top_n(Integer n, RequestParams params) {
        client.get(getAbsoluteUrl("top_n/?n="+n.toString()), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                apiListener.taskCompleted(response);
            }
        });
    }

    public static void get_ranking(Integer id, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(id.toString()+"/ranking"), params, responseHandler);
    }

    public static void post(RequestParams params) {
        client.post(getAbsoluteUrl(""), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                apiListener.taskCompleted(response);
            }
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                JSONArray mulres = new JSONArray();
                System.out.println(response);
                try{
                    mulres = new JSONArray("["+response.toString()+"]");
                }catch (JSONException e){
                    e.printStackTrace();
                }
                System.out.println(mulres);
                apiListener.taskCompleted(mulres);

            }
        });
    }
    /*
    public static URL buildUrl(String githubSearchQuery) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, SearchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    */

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
