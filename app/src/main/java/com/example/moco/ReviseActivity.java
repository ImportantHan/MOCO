package com.example.moco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ReviseActivity extends AppCompatActivity {

    EditText revise_pw, revise_serial, revise_phone;
    Button revise_do, delete_do;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise);

        revise_pw = findViewById(R.id.revise_pw);
        revise_serial = findViewById(R.id.revise_serial);
        revise_phone = findViewById(R.id.revise_phone);

        revise_do = findViewById(R.id.revise_do);
        delete_do = findViewById(R.id.delete_do);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("moco");

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        revise_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> childUpdates = new HashMap<>();

                childUpdates.put("pw", revise_pw.getText().toString());
                childUpdates.put("serial", revise_serial.getText().toString());
                childUpdates.put("phone", revise_phone.getText().toString());

                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).updateChildren(childUpdates);

                Toast.makeText(getApplicationContext(), "수정 성공", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        delete_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RealdeleteActivity.class);
                startActivity(intent);
            }
        });

    }
}