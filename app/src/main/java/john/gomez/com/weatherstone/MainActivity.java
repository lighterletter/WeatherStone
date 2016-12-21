package john.gomez.com.weatherstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.List;

import john.gomez.com.weatherstone.backend.APIResponse;
import john.gomez.com.weatherstone.backend.CallAPI;
import john.gomez.com.weatherstone.model.Period;
import john.gomez.com.weatherstone.model.WeatherResponse;


public class MainActivity extends AppCompatActivity {

    private RequestQueue request;
    private RecyclerView forecastRecyclerView;
    private boolean isCelsius = false;
    private Button celsiusFlag;
    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forecastRecyclerView = (RecyclerView) findViewById(R.id.forecast_Recycler_View);

        makeNetworkRequest();
        setCelsiusListener();
    }

    private void setCelsiusListener() {
        celsiusFlag = (Button) findViewById(R.id.celsius_flag);
        celsiusFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weatherAdapter != null) {
                    if (isCelsius == true) {
                        isCelsius = false;
                        weatherAdapter.setCelsius(isCelsius);
                        weatherAdapter.notifyDataSetChanged();
                    } else {
                        isCelsius = true;
                        weatherAdapter.setCelsius(isCelsius);
                        weatherAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    private void makeNetworkRequest() {
        request = Volley.newRequestQueue(getApplicationContext());
        Consumer<APIResponse> listener = buildDataReceiver();
        request.add(CallAPI.buildDataRequest(listener));
    }

    private Consumer<APIResponse> buildDataReceiver() {
        return new Consumer<APIResponse>() {
            @Override
            public void acceptData(APIResponse data) {
                forecastRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                //set adapter, set flag
                List<Period> dailyForeCast = data.getResponse().get(0).getPeriods();
                weatherAdapter = new WeatherAdapter(dailyForeCast, isCelsius);
                forecastRecyclerView.setAdapter(weatherAdapter);
            }
        };
    }


}
