package john.gomez.com.weatherstone.backend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import john.gomez.com.weatherstone.model.Period;
import john.gomez.com.weatherstone.model.WeatherProfile;
import john.gomez.com.weatherstone.model.WeatherResponse;


public class APIResponse {

    //outer layer
    private static final String JSON_SUCCESS = "success";
    private static final String JSON_RESPONSE = "response";

    //mid layer
    private static final String JSON_INTERVAL = "interval";
    private static final String JSON_PERIODS = "periods";
    private static final String JSON_PROFILE = "profile";
    // profile timezone
    private static final String JSON_TIMEZONE= "tz";

    //lower layer
    private static final String JSON_MIN_C = "minTempC";
    private static final String JSON_AVG_C = "avgTempC";
    private static final String JSON_MAX_C = "maxTempC";

    private static final String JSON_MIN_F = "minTempF";
    private static final String JSON_AVG_F = "avgTempF";
    private static final String JSON_MAX_F = "maxTempF";

    private static final String JSON_DATETIME = "dateTimeISO";
    private static final String JSON_ICON= "icon";


    //response fields
    private boolean success;
    private List<WeatherResponse> response;


    public boolean isSuccess() {
        return success;
    }

    public List<WeatherResponse> getResponse() {
        return response;
    }

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
            timeZone.setTz(profile.getString(JSON_TIMEZONE));
            response.setProfile(timeZone);

            responseList.add(response);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return responseList;
    }

    private static List<Period> parsePeriods(JSONArray periods) {
        List<Period> weeklyWeather = new ArrayList<>();
        for (int index = 0; index < periods.length(); index++){
            try {
                JSONObject current = periods.getJSONObject(index);
                Period thisDay = new Period();

                thisDay.setMinTempC(current.getInt(JSON_MIN_C));
                thisDay.setAvgTempC(current.getInt(JSON_AVG_C));
                thisDay.setMaxTempC(current.getInt(JSON_MAX_C));

                thisDay.setMinTempF(current.getInt(JSON_MIN_F));
                thisDay.setAvgTempF(current.getInt(JSON_AVG_F));
                thisDay.setMaxTempF(current.getInt(JSON_MAX_F));

                thisDay.setDateTimeISO(current.getString(JSON_DATETIME));
                thisDay.setIcon(current.getString(JSON_ICON));

                weeklyWeather.add(thisDay);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return weeklyWeather;
    }

    private static APIResponse buildFailureCase() {
        APIResponse result = new APIResponse();
        result.response = new ArrayList<WeatherResponse>();
        result.success = false;
        return result;
    }
}
