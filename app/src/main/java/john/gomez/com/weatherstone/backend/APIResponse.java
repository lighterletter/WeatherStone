package john.gomez.com.weatherstone.backend;

import java.util.List;

import john.gomez.com.weatherstone.model.WeatherResponse;

/**
 * Created by john on 12/21/16.
 */

public class APIResponse {

    private static final String JSON_SUCCESS = "success";
    private static final String JSON_ESPONSE = "response";
    private boolean success;
    private List<WeatherResponse> response;


    public static APIResponse parse(String response){

        return buildFailureCase();
    }

    private static APIResponse buildFailureCase() {
        APIResponse result = new APIResponse();

        return null;
    }
}
