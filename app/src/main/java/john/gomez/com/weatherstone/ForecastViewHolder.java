package john.gomez.com.weatherstone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import john.gomez.com.weatherstone.model.Period;

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

    public void bind(Period forecast, boolean isCelsius) {

        if (isCelsius) {
            bindCelsius(forecast);
        } else {
            bindFahrenheit(forecast);
        }

        timeStamp.setText(getDay(forecast.getDateTimeISO()));
        //setIcon
        Context context = iconIV.getContext();
        String iconId = forecast.getIcon();
        int id = context.getResources().getIdentifier(iconId.substring(0, iconId.length() - 4), "drawable", context.getPackageName());
        iconIV.setImageResource(id);
    }

    private void bindFahrenheit(Period forecast) {

        String fahrenheit = rootView.getResources().getString(R.string.fahrenheit);

        averageTemp.setText(String.format("Avg " + fahrenheit + ": %s ", String.valueOf(forecast.getAvgTempF())));
        minTemp.setText(String.format("Min " + fahrenheit + ": %s ", String.valueOf(forecast.getMinTempF())));
        maxTemp.setText(String.format("Max " + fahrenheit + ": %s ", String.valueOf(forecast.getMaxTempF())));
    }

    private void bindCelsius(Period forecast) {

        String celsius = rootView.getResources().getString(R.string.celsius);

        averageTemp.setText(String.format("Avg " + celsius + ": %s ", String.valueOf(forecast.getAvgTempC())));
        minTemp.setText(String.format("Min " + celsius + ": %s ", String.valueOf(forecast.getMinTempC())));
        maxTemp.setText(String.format("Max " + celsius + ": %s ", String.valueOf(forecast.getMaxTempC())));
    }

    private String getDay(String date) {
        date = date.substring(0,10);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        Date dt1 = null;
        try {
            dt1 = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("EEEE");
        String finalDay = format2.format(dt1);
        return finalDay + " " + date.substring(5);
    }

}
