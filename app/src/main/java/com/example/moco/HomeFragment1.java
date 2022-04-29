package com.example.moco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment1 extends Fragment {

    ListView announcement_list;
    ArrayList<AnnouncementVO> dataset;
    AnnouncementAdapter announcementAdapter;

    Button user1;

    MenuItem mypage_item;
    BottomNavigationView nav;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home1, container, false);

        announcement_list = view.findViewById(R.id.announcement_list);
        user1 = view.findViewById(R.id.user1);
        mypage_item = view.findViewById(R.id.mypage_item);
        nav = view.findViewById(R.id.nav);

        dataset = new ArrayList<AnnouncementVO>();
        dataset.add(new AnnouncementVO("[공지] MOCO 개인정보처리방침 개정 안내", "2022/04/26"));
        dataset.add(new AnnouncementVO("[공지] 모션인식 기능 업데이트", "2022/04/20"));
        dataset.add(new AnnouncementVO("[공지] MOCO app 다운로드 100만 돌파!!", "2022/04/18"));
        dataset.add(new AnnouncementVO("[공지] 시스템 점검에 따른 어플 사용 일시 중단 안내", "2022/04/15"));
        dataset.add(new AnnouncementVO("[공지] MOCO Android app 출시기념 이벤트", "2022/04/12"));

        announcementAdapter = new AnnouncementAdapter(view.getContext(), R.layout.list_announcement, dataset);

        announcement_list.setAdapter(announcementAdapter);

        announcement_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(dataset.get(i).getTitle().equals("[공지] MOCO 개인정보처리방침 개정 안내")){
                    Intent intent = new Intent(view.getContext(), AnnouncementlistActivity.class);
                    startActivity(intent);
                } else if (dataset.get(i).getTitle().equals("[공지] 모션인식 기능 업데이트")){
                    Intent intent = new Intent(view.getContext(), Announcementlist2Activity.class);
                    startActivity(intent);
                }
            }
        });

        user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity homeActivity = (HomeActivity) getActivity();
                homeActivity.changeFragment(new HomeFragment4());
            }
        });

        return view;

    }
}

