package com.example.moco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    HomeFragment1 homeFragment1;
    HomeFragment2 homeFragment2;
    HomeFragment3 homeFragment3;
    HomeFragment4 homeFragment4;

    BottomNavigationView nav;

    Button user1;

    long backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeFragment1 = new HomeFragment1();
        homeFragment2 = new HomeFragment2();
        homeFragment3 = new HomeFragment3();
        homeFragment4 = new HomeFragment4();

        nav = findViewById(R.id.nav);

        user1 = findViewById(R.id.user1);

        // frame영역에 화면 띄우기
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment1).commit();

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_item) {
                    // fragment1
                    changeFragment(homeFragment1);
                } else if (item.getItemId() == R.id.device_item) {
                    // fragment2
                    changeFragment(homeFragment2);
                } else if (item.getItemId() == R.id.motion_item) {
                    // fragment3
                    changeFragment(homeFragment3);
                } else if (item.getItemId() == R.id.mypage_item) {
                    // fragment4
                    changeFragment(homeFragment4);
                }
                return true;
            }
        });
    }

    public void changeFragment(Fragment fragment){

        if (fragment instanceof HomeFragment1){
            nav.getMenu().getItem(0).setChecked(true);
        }else if(fragment instanceof HomeFragment2){
            nav.getMenu().getItem(1).setChecked(true);
        }else if(fragment instanceof HomeFragment3){
            nav.getMenu().getItem(2).setChecked(true);
        }else if(fragment instanceof HomeFragment4){
            nav.getMenu().getItem(3).setChecked(true);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }

    @Override
    public void onBackPressed() {

        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

        if(System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
        }

    }
}