package com.ambulert.ambugroup.ambulert.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ambulert.ambugroup.ambulert.R;

import java.util.ArrayList;

public class SettingAdapter extends BaseAdapter {

    Context context;
    ArrayList<Setting> list;
    LayoutInflater inflater;

    public SettingAdapter(Context context, ArrayList<Setting> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        view = (view == null) ? inflater.inflate(R.layout.layout_settings_item,null) : view;

        ImageView settingIcon = view.findViewById(R.id.settingIcon);
        TextView settingTitle = view.findViewById(R.id.settingTitle);
        Setting s = list.get(position);

        settingIcon.setImageResource(s.getSettingIcon());
        settingTitle.setText(s.getSettingTitle());

        return view;
    }
}
