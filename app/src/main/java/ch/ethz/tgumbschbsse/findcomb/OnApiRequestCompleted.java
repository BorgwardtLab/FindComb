package ch.ethz.tgumbschbsse.findcomb;

import org.json.JSONArray;

/**
 * Created by tomatteo on 8/14/17.
 */

public interface OnApiRequestCompleted {
    public void taskCompleted(JSONArray results);
}
