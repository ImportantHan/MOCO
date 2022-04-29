package com.example.moco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스

    EditText join_id, join_pw, join_serial, join_phone; // 회원가입 입력필드
    Button join_do; // 회원가입 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("moco");

        join_id = findViewById(R.id.join_id);
        join_pw = findViewById(R.id.join_pw);
        join_serial = findViewById(R.id.join_serial);
        join_phone = findViewById(R.id.join_phone);
        join_do = findViewById(R.id.join_do);

        join_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = join_id.getText().toString();
                String pw = join_pw.getText().toString();
                String serial = join_serial.getText().toString();
                String phone = join_phone.getText().toString();

                mFirebaseAuth.createUserWithEmailAndPassword(id, pw).addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();

                            account.setIdToken(firebaseUser.getUid());
                            account.setId(firebaseUser.getEmail());
                            account.setPw(pw);
                            account.setSerial(serial);
                            account.setPhone(phone);

                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "회원가입 실패 ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}