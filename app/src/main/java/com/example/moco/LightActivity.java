package com.example.moco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LightActivity extends AppCompatActivity {

    Button on_off_light;
    LottieAnimationView lottie_light;

    RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        on_off_light = findViewById(R.id.on_off_light);
        lottie_light = findViewById(R.id.lottie_light);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        on_off_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(on_off_light.getText().equals("OFF")){
                    on_off_light.setText("ON");
                    on_off_light.setBackgroundResource(R.drawable.btn_on);
                    lottie_light.resumeAnimation();

                    String url = "http://172.30.1.34:8090/MocoServer/api/getResult?none=0&hot=0&cold=0&spin=0&sleep=1";

                    request = new StringRequest(
                            Request.Method.GET,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    requestQueue.add(request);

                } else {
                    on_off_light.setText("OFF");
                    on_off_light.setBackgroundResource(R.drawable.btn_off);
                    lottie_light.pauseAnimation();

                    String url = "http://172.30.1.34:8090/MocoServer/api/getResult?none=0&hot=0&cold=0&spin=0&sleep=0";

                    request = new StringRequest(
                            Request.Method.GET,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    requestQueue.add(request);
                }
            }
        });




    }
}