package ch.ethz.tgumbschbsse.findcomb;
import android.net.Uri;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tomatteo on 8/9/17.
 */

public class Api {
    private static final String BASE_URL = "http://127.0.0.1:8000/api/scores/"; // TODO: add final server port URL, this one is for temporary development
    private static AsyncHttpClient client = new AsyncHttpClient();
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void get_top_n(Integer n, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl("top_n/?n="+n.toString()), params, responseHandler);
    }

    public static void get_ranking(Integer id, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(id.toString()+"/ranking"), params, responseHandler);
    }

    public static void post(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(""), params, responseHandler);
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
