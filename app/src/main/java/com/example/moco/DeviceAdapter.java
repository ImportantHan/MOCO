package com.example.moco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DeviceAdapter extends BaseAdapter {

    Context context;
    String[] adddevice_name;
    int[] adddevice_image;

    LayoutInflater inflater;

    public DeviceAdapter(Context context, String[] adddevice_name, int[] adddevice_image) {
        this.context = context;
        this.adddevice_name = adddevice_name;
        this.adddevice_image = adddevice_image;
    }

    @Override
    public int getCount() {
        return adddevice_name.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view = inflater.inflate(R.layout.list_adddevice, viewGroup, false);
        }

        ImageView device_img = view.findViewById(R.id.device_img);
        TextView device_name = view.findViewById(R.id.device_name);

        device_img.setImageResource(adddevice_image[i]);
        device_name.setText(adddevice_name[i]);

        return view;
    }
}
