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

public class MachineActivity extends AppCompatActivity {

    Button on_off_washing_machine;
    LottieAnimationView lottie_washing_machine;

    RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);

        on_off_washing_machine = findViewById(R.id.on_off_washing_machine);
        lottie_washing_machine = findViewById(R.id.lottie_washing_machine);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        on_off_washing_machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(on_off_washing_machine.getText().equals("OFF")){
                    on_off_washing_machine.setText("ON");
                    on_off_washing_machine.setBackgroundResource(R.drawable.btn_on);
                    lottie_washing_machine.resumeAnimation();

                    String url = "http://172.30.1.34:8090/MocoServer/api/getResult?none=0&hot=0&cold=0&spin=1&sleep=0";

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
                    on_off_washing_machine.setText("OFF");
                    on_off_washing_machine.setBackgroundResource(R.drawable.btn_off);
                    lottie_washing_machine.pauseAnimation();

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