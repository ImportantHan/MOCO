package com.example.moco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스

    EditText login_id, login_pw;
    Button login_do;
    TextView find_join_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("moco");

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);
        login_do = findViewById(R.id.login_do);

        find_join_txt = findViewById(R.id.find_join_txt);

        login_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = login_id.getText().toString();
                String pw = login_pw.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(id, pw).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        find_join_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoininfoActivity.class);
                startActivity(intent);
            }
        });
    }
}