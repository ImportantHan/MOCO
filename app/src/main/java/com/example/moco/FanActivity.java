package com.example.moco;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
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

public class FanActivity extends AppCompatActivity {

    Button on_off_fan;
    LottieAnimationView lottie_fan;

    RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);

        on_off_fan = findViewById(R.id.on_off_fan);
        lottie_fan = findViewById(R.id.lottie_fan);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        on_off_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (on_off_fan.getText().equals("OFF")) {
                    on_off_fan.setText("ON");
                    on_off_fan.setBackgroundResource(R.drawable.btn_on);
                    lottie_fan.resumeAnimation();

                    String url = "http://172.30.1.34:8090/MocoServer/api/getResult?none=0&hot=1&cold=0&spin=0&sleep=0";

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
                    on_off_fan.setText("OFF");
                    on_off_fan.setBackgroundResource(R.drawable.btn_off);
                    lottie_fan.pauseAnimation();

                    String url = "http://172.30.1.34:8090/MocoServer/api/getResult?none=0&hot=0&cold=1&spin=0&sleep=0";

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