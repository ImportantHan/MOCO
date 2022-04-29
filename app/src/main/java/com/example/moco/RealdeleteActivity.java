package com.example.moco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class RealdeleteActivity extends AppCompatActivity {

    Button real_delete, real_cancle;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realdelete);

        real_delete = findViewById(R.id.real_delete);
        real_cancle = findViewById(R.id.real_cancle);

        mFirebaseAuth = FirebaseAuth.getInstance();

        real_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.getCurrentUser().delete();
                Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                startActivity(intent);
                finish();
            }
        });

        real_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviseActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}