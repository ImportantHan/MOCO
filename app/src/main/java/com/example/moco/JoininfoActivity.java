package com.example.moco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoininfoActivity extends AppCompatActivity {

    Button find_join_btn;
    EditText find_phone;

    static final int SMS_RECEIVE_PERMISSON = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joininfo);

        find_join_btn = findViewById(R.id.find_join_btn);
        find_phone = findViewById(R.id.find_phone);

        int permissonCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if(permissonCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "SMS 권한 있음", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "SMS 권한 없음", Toast.LENGTH_SHORT).show();
            //권한설정 dialog에서 거부를 누르면
            //ActivityCompat.shouldShowRequestPermissionRationale 메소드의 반환값이 true가 된다.

            //단, 사용자가 "Don't ask again"을 체크한 경우
            //거부하더라도 false를 반환하여, 직접 사용자가 권한을 부여하지 않는 이상, 권한을 요청할 수 없게 된다.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                //이곳에 권한이 왜 필요한지 설명하는 Toast나 dialog를 띄워준 후, 다시 권한을 요청한다.
                Toast.makeText(getApplicationContext(), "SMS 권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSON);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSON);
            }
        }

        find_join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = find_phone.getText().toString();
                String sms = "아이디 : ha@gmail.com" + "\n" + "비밀번호 : 123456";

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "전송 완료", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "전송 실패", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}