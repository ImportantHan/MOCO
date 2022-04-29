package com.example.moco;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment4 extends Fragment {

    TextView user_id;
    TextView user_phone;
    TextView user_serial;

    ImageView logo4;
    Button logout;
    Button revise;
    Button delete_device;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home4, container, false);

        user_id = view.findViewById(R.id.user_id);
        user_phone = view.findViewById(R.id.user_phone);
        user_serial = view.findViewById(R.id.user_serial);

        logo4 = view.findViewById(R.id.logo4);
        logout = view.findViewById(R.id.logout);
        revise = view.findViewById(R.id.revise);
        delete_device = view.findViewById(R.id.delete_device);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("moco");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            // user is signed in
            mDatabaseRef.child("UserAccount").child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        UserAccount userAccount = task.getResult().getValue(UserAccount.class);
                        user_id.setText(userAccount.getId());
                        user_phone.setText(userAccount.getPhone());
                        user_serial.setText(userAccount.getSerial());
                    }
                }
            });
        } else {
            // No user is signed in
        }

        logo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity homeActivity = (HomeActivity) getActivity();
                homeActivity.changeFragment(new HomeFragment1());
            }
        });

        revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReviseActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
                getActivity().finish();
            }
        });

        delete_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DeleltedeviceActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            // user is signed in
            mDatabaseRef.child("UserAccount").child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        UserAccount userAccount = task.getResult().getValue(UserAccount.class);
                        user_id.setText(userAccount.getId());
                        user_phone.setText(userAccount.getPhone());
                        user_serial.setText(userAccount.getSerial());
                    }
                }
            });
        } else {
            // No user is signed in
        }
    }
}
