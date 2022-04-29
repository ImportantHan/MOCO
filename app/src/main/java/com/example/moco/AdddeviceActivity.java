package com.example.moco;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdddeviceActivity extends AppCompatActivity {

    ListView adddevice_list;
    DeviceAdapter deviceAdapter;

    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevice);

        adddevice_list = findViewById(R.id.adddevice_list);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("moco");

        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

        Map<String, Object> taskMap = new HashMap<String, Object>();

        int[] device_img = {R.drawable.airconditioner, R.drawable.dish_washer, R.drawable.fan, R.drawable.light, R.drawable.oven, R.drawable.refrigerators, R.drawable.washing_machine, R.drawable.water_heater};
        String[] device_name = {"에어컨", "세척기", "선풍기", "전등", "오븐", "냉장고", "세탁기", "보일러"};

        deviceAdapter = new DeviceAdapter(getApplicationContext(), device_name, device_img);
        adddevice_list.setAdapter(deviceAdapter);

        adddevice_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(device_name[i].equals("세탁기")) {
                    Toast.makeText(getApplicationContext(), "세탁기 추가", Toast.LENGTH_SHORT).show();

                    taskMap.put("washingmachine",true);
                    mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).updateChildren(taskMap);

                    finish();

                } else if(device_name[i].equals("전등")) {
                    Toast.makeText(getApplicationContext(), "전등 추가", Toast.LENGTH_SHORT).show();

                    taskMap.put("light",true);
                    mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).updateChildren(taskMap);

                    finish();

                } else if(device_name[i].equals("선풍기")) {
                    Toast.makeText(getApplicationContext(), "선풍기 추가", Toast.LENGTH_SHORT).show();

                    taskMap.put("fan",true);
                    mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).updateChildren(taskMap);

                    finish();

                }
            }
        });
    }
}