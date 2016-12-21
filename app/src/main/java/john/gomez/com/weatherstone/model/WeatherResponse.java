package john.gomez.com.weatherstone.model;

import java.util.List;

/**
 * Created by john on 12/21/16.
 */
public class WeatherResponse {

    private String interval;
    private List<Period> periods;
    private WeatherProfile profile;

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public WeatherProfile getProfile() {
        return profile;
    }

    public void setProfile(WeatherProfile profile) {
        this.profile = profile;
    }
}
