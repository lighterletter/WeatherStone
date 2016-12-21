package john.gomez.com.weatherstone;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import john.gomez.com.weatherstone.backend.APIResponse;
import john.gomez.com.weatherstone.model.Period;

/**
 * Created by john on 12/21/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<ForecastViewHolder> {


    private final List<Period> forecast;

    public void setCelsius(boolean celsius) {
        isCelsius = celsius;
    }

    private boolean isCelsius;

    public WeatherAdapter(List<Period> dailyForeCast, boolean isCelsius) {
        this.forecast = dailyForeCast;
        this.isCelsius = isCelsius;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ForecastViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.bind(forecast.get(position), isCelsius);
    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }
}
