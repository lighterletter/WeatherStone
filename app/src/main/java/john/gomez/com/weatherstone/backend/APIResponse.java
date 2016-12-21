package john.gomez.com.weatherstone.backend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import john.gomez.com.weatherstone.model.Period;
import john.gomez.com.weatherstone.model.WeatherProfile;
import john.gomez.com.weatherstone.model.WeatherResponse;

/**
 * Created by john on 12/21/16.
 */

public class APIResponse {

    private static final String JSON_SUCCESS = "success";
    private static final String JSON_RESPONSE = "response";
    private static final String JSON_INTERVAL = "interval";
    private static final String JSON_PERIODS = "periods";
    private static final String JSON_PROFILE = "profile";
    private boolean success;
    private List<WeatherResponse> response;


    public static APIResponse parse(String response) {

        try {
            JSONObject json = new JSONObject(response);
            boolean successful = json.getBoolean(JSON_SUCCESS);
            JSONArray data = json.getJSONArray(JSON_RESPONSE);

            APIResponse result = new APIResponse();
            result.success = successful;
            result.response = parseResponseList(data);
            return result;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return buildFailureCase();
    }

    private static List<WeatherResponse> parseResponseList(JSONArray data) {
        List<WeatherResponse> responseList = new ArrayList<>();
        try {
            JSONObject current = data.getJSONObject(0);
            WeatherResponse response = new WeatherResponse();

            String interval = current.getString(JSON_INTERVAL);
            response.setInterval(interval);

            JSONArray periods = current.getJSONArray(JSON_PERIODS);
            response.setPeriods(parsePeriods(periods));

            JSONObject profile = current.getJSONObject(JSON_PROFILE);
            WeatherProfile timeZone = new WeatherProfile();
            timeZone.setTz(profile.getString("tz"));
            response.setProfile(timeZone);

            responseList.add(response);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return responseList;
    }

    private static List<Period> parsePeriods(JSONArray periods) {
        for (int index = 0; index < periods.length(); index++){
            try {
                JSONObject current = periods.getJSONObject(index);
                

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    private static APIResponse buildFailureCase() {
        APIResponse result = new APIResponse();
        result.response = new ArrayList<WeatherResponse>();
        result.success = false;
        return result;
    }
}
