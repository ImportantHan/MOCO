package com.example.moco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnnouncementAdapter extends BaseAdapter {
    // Adapter가 가져야 하는 필요 객체 지정하기
    private Context context;
    private int layout;
    private ArrayList<AnnouncementVO> dataset;

    // 화면(xml) : view 타입으로 변경을 도와주는 객체가 필요
    private LayoutInflater inflater;

    // Adapter 생성자 메소드
    public AnnouncementAdapter(Context context, int layout, ArrayList<AnnouncementVO> dataset){
        this.context = context;
        this.layout = layout;
        this.dataset = dataset;
    }

    @Override
    public int getCount() {
        // 현재 데이터셋의 총 갯수를 반환해주는 메소드
        // 갯수를 알아야 크기만큼 반복하여
        return dataset.size();
    }

    @Override
    public Object getItem(int i) {
        // item의 위치 정보값을 가져오는 메소드
        return dataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        // item에 대한 id정보값을 가져오는 메소드
        return 0;
    }

    // 무조건 필수!! 꼭 확인해야 하는 메소드
    // community_list에 들어갈 item 내용을 초기화 하고 화면에 view가 보이도록 리턴하는 메소드
    // 아이템 화면의 xml화면과 데이터셋을 연결해주는 역할
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // i : position
        // view : 화면에 띄워지는 view
        if(view == null) {
            // LayoutInflater != Object 서로 다른 형태로 존재하기 때문에 다운캐스팅
            // 화면을 변환할 수 있는 서비스 기능 불러오기
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // xml -> view 변환 작업
            // layout : list_community.xml
            view = inflater.inflate(layout, viewGroup, false);
        }
        // 변환된 view들의 id정보값(위치) -> findViewById()
        TextView announcement_title = view.findViewById(R.id.announcement_title);
        TextView announcement_date = view.findViewById(R.id.announcement_date);

        // 해당 위치에 데이터 연결
        AnnouncementVO vo = dataset.get(i);
        announcement_title.setText(vo.getTitle());
        announcement_date.setText(String.valueOf(vo.getDate()));

        return view;
    }
}
