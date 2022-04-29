package com.example.moco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment2 extends Fragment {

    GridView device_list;
    ArrayList<DeviceVO> dataset;
    GridAdapter gridAdapter;

    Button btn_add_device;
    Button user2;
    ImageView logo2;

    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home2, container, false);

        device_list = view.findViewById(R.id.device_list);
        user2 = view.findViewById(R.id.user2);
        logo2 = view.findViewById(R.id.logo2);
        btn_add_device = view.findViewById(R.id.btn_add_device);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("moco");
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

        dataset = new ArrayList<DeviceVO>();
        dataset.add(new DeviceVO(R.drawable.airconditioner, "에어컨"));
        dataset.add(new DeviceVO(R.drawable.dish_washer, "세척기"));
        dataset.add(new DeviceVO(R.drawable.oven, "오븐"));
        dataset.add(new DeviceVO(R.drawable.refrigerators, "냉장고"));
        dataset.add(new DeviceVO(R.drawable.water_heater, "에어컨"));

        gridAdapter = new GridAdapter(view.getContext(),R.layout.grid_device, dataset);
        device_list.setAdapter(gridAdapter);

        mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    UserAccount userAccount = task.getResult().getValue(UserAccount.class);
                    Log.d("firebase", "userAccount : " + userAccount);
                    if(userAccount.isFan()){
                        //중복허용 안하는 로직
                        if( isExist("선풍기")  == false ) {
                            dataset.add(new DeviceVO(R.drawable.fan, "선풍기"));
                        }
                    }
                    if(userAccount.isLight()){
                        if( isExist("전등")  == false ) {
                            dataset.add(new DeviceVO(R.drawable.light, "전등"));
                        }
                    }
                    if(userAccount.isWashingmachine()) {
                        if( isExist("세탁기")  == false ) {
                            dataset.add(new DeviceVO(R.drawable.washing_machine, "세탁기"));
                        }
                    }
                    gridAdapter.notifyDataSetChanged();
                }
            }
        });

        device_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(dataset.get(i).getAdddevice_name().equals("세탁기")) {
                    Intent intent = new Intent(view.getContext(), MachineActivity.class);
                    startActivity(intent);
                }
                if(dataset.get(i).getAdddevice_name().equals("선풍기")) {
                    Intent intent = new Intent(view.getContext(), FanActivity.class);
                    startActivity(intent);
                }
                if(dataset.get(i).getAdddevice_name().equals("전등")) {
                    Intent intent = new Intent(view.getContext(), LightActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AdddeviceActivity.class);
                startActivity(intent);
            }
        });

        user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity homeActivity = (HomeActivity) getActivity();
                homeActivity.changeFragment(new HomeFragment4());
            }
        });

        logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity homeActivity = (HomeActivity) getActivity();
                homeActivity.changeFragment(new HomeFragment1());
            }
        });

        return view;
    }

    boolean isExist(String devicename){

        for( DeviceVO dv : dataset) {
            if( dv.getAdddevice_name().equals( devicename ) ){
                return true;
            }
        }

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();

        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    UserAccount userAccount = task.getResult().getValue(UserAccount.class);
                    Log.d("firebase", "userAccount : " + userAccount);
                    if(userAccount.isFan()){
                        //중복허용 안하는 로직
                        if( isExist("선풍기")  == false ) {
                            dataset.add(new DeviceVO(R.drawable.fan, "선풍기"));
                        }
                    }
                    if(userAccount.isLight()){
                        if( isExist("전등")  == false ) {
                            dataset.add(new DeviceVO(R.drawable.light, "전등"));
                        }
                    }
                    if(userAccount.isWashingmachine()) {
                        if( isExist("세탁기")  == false ) {
                            dataset.add(new DeviceVO(R.drawable.washing_machine, "세탁기"));
                        }
                    }
                    gridAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
