package com.example.moco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<DeviceVO> dataset;

    LayoutInflater inflater;

    public GridAdapter(Context context, int layout, ArrayList<DeviceVO> dataset) {
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
        return dataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.grid_device, viewGroup, false);
        }

        ImageView grid_image = view.findViewById(R.id.grid_image);
        TextView device = view.findViewById(R.id.device);

        DeviceVO vo = dataset.get(i);
        grid_image.setImageResource(vo.getAdddevice_image());
        device.setText(vo.getAdddevice_name());

        return view;
    }
}
