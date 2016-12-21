package john.gomez.com.weatherstone.backend;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import john.gomez.com.weatherstone.BuildConfig;
import john.gomez.com.weatherstone.Consumer;
import static android.content.ContentValues.TAG;

public class CallAPI {

    private final static String URL = "http://api.aerisapi.com/forecasts/11101?client_id="
            + BuildConfig.CLIENT_ID
            + "&client_secret="
            + BuildConfig.CLIENT_SECRET;

    public static Request<String> buildDataRequest(Consumer<APIResponse> listener) {
        StringRequest request = new StringRequest(Request.Method.GET,
                URL,
                buildSuccessListener(listener),
                buildErrorListener());
        return request;
    }

    private static Response.Listener<String> buildSuccessListener(final Consumer<APIResponse> listener) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                APIResponse result = APIResponse.parse(response);
                listener.acceptData(result);
            }
        };
    }

    private static Response.ErrorListener buildErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        };
    }


}
