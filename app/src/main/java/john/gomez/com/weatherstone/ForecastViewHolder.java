package john.gomez.com.weatherstone;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

import john.gomez.com.weatherstone.model.Period;

/**
 * Created by john on 12/21/16.
 */
public class ForecastViewHolder extends RecyclerView.ViewHolder {


    private final View rootView;
    private final TextView averageTemp;
    private final TextView minTemp;
    private final TextView maxTemp;
    private final TextView timeStamp;
    private final ImageView iconIV;

    public ForecastViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        rootView = itemView;

        averageTemp = (TextView) rootView.findViewById(R.id.average_temp);
        minTemp = (TextView) rootView.findViewById(R.id.min_temp);
        maxTemp = (TextView) rootView.findViewById(R.id.max_temp);
        timeStamp = (TextView) rootView.findViewById(R.id.timestamp);
        iconIV = (ImageView) rootView.findViewById(R.id.icon);

    }

    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.forecast, parent, false);
    }

    public void bind(Period forecast, boolean isCelsius){

        if(isCelsius){
            bindCelsius(forecast);
        } else {
            bindFahrenheit(forecast);
        }

        timeStamp.setText(forecast.getDateTimeISO());
        //setIcon
        Context context = iconIV.getContext();
        String iconId = forecast.getIcon();
        int id = context.getResources().getIdentifier(iconId.substring(0,iconId.length() - 4), "drawable", context.getPackageName());
        iconIV.setImageResource(id);
    }

    private void bindFahrenheit(Period forecast) {
        averageTemp.setText(String.valueOf(forecast.getAvgTempF()));
        minTemp.setText(String.valueOf(forecast.getMinTempF()));
        maxTemp.setText(String.valueOf(forecast.getMaxTempF()));
    }

    private void bindCelsius(Period forecast) {
        averageTemp.setText(String.valueOf(forecast.getAvgTempC()));
        minTemp.setText(String.valueOf(forecast.getMinTempC()));
        maxTemp.setText(String.valueOf(forecast.getMaxTempC()));
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
