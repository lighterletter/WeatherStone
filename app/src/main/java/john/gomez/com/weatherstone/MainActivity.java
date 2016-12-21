package john.gomez.com.weatherstone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.util.List;
import john.gomez.com.weatherstone.backend.APIResponse;
import john.gomez.com.weatherstone.backend.CallAPI;
import john.gomez.com.weatherstone.model.Period;


public class MainActivity extends AppCompatActivity {

    private RequestQueue request;
    private RecyclerView forecastRecyclerView;
    private boolean isCelsius = false;
    private FloatingActionButton celsiusFlag;
    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isNetworkAvailable()) {
            forecastRecyclerView = (RecyclerView) findViewById(R.id.forecast_Recycler_View);
            makeNetworkRequest();
            setCelsiusListener();
        } else {
            Toast.makeText(this,"Could not connect to service, check network connection and try again.", Toast.LENGTH_LONG).show();
        }

    }

    private void setCelsiusListener() {
        celsiusFlag = (FloatingActionButton) findViewById(R.id.celsius_flag);
        celsiusFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weatherAdapter != null) {
                    if (isCelsius == true) {
                        isCelsius = false;
                        celsiusFlag.setImageResource(R.drawable.celsius);
                        weatherAdapter.setCelsius(isCelsius);
                        weatherAdapter.notifyDataSetChanged();
                    } else {
                        isCelsius = true;
                        celsiusFlag.setImageResource(R.drawable.fahrenheit);
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
                if (data.isSuccess()) {
                    forecastRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    List<Period> dailyForeCast = data.getResponse().get(0).getPeriods();
                    weatherAdapter = new WeatherAdapter(dailyForeCast, isCelsius);
                    forecastRecyclerView.setAdapter(weatherAdapter);
                }
            }
        };
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }


}
