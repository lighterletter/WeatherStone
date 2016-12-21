package john.gomez.com.weatherstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import john.gomez.com.weatherstone.backend.APIResponse;
import john.gomez.com.weatherstone.backend.CallAPI;


public class MainActivity extends AppCompatActivity {

    RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void makeNetworkRequest(){
        request = Volley.newRequestQueue(getApplicationContext());
        Consumer<APIResponse> listener = buildDataReceiver();
        request.add(CallAPI.buildDataRequest(listener));
    }

    private Consumer<APIResponse> buildDataReceiver() {
        return new Consumer<APIResponse>() {
            @Override
            public void acceptData(APIResponse data) {
                //setLayoutmanager
                //set adapter
            }
        };
    }


}
