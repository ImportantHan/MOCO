package com.example.moco;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class IntroAdapter extends FragmentStateAdapter {

    public int mCount;

    public IntroAdapter(@NonNull FragmentActivity fragmentActivity, int count) {
        super(fragmentActivity);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        if(index==0) return new IntroFragment1();
        else if(index==1) return new IntroFragment2();
        else return new IntroFragment3();
    }

    @Override
    public int getItemCount() {
        return 3000; // 총 이미지 수
    }

    public int getRealPosition(int position) {
        return position % mCount;
    }
}
