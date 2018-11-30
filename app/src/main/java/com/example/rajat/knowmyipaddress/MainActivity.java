package com.example.rajat.knowmyipaddress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.ip_text);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.ipify.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IPService ipService = retrofit.create(IPService.class);
        ipService.getIPaddress().enqueue(new Callback<IPaddress>() {
            @Override
            public void onResponse(Call<IPaddress> call, Response<IPaddress> response) {
                IPaddress ipAddress = response.body();
                textView.setText(ipAddress.ip);
            }

            @Override
            public void onFailure(Call<IPaddress> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Connection error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
