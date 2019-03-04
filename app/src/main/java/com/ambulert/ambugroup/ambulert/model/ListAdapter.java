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

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<AmbulertList> ambulertList;
    LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList<AmbulertList> ambulertList) {
        this.context = context;
        this.ambulertList = ambulertList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ambulertList.size();
    }

    @Override
    public Object getItem(int position) {
        return ambulertList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        view = (view == null) ? inflater.inflate(R.layout.layout_settings_item,null) : view;

        ImageView listIcon = view.findViewById(R.id.settingIcon);
        TextView listTitle = view.findViewById(R.id.settingTitle);
        AmbulertList s = ambulertList.get(position);

        listIcon.setImageResource(s.getListIcon());
        listTitle.setText(s.getListTitle());

        return view;
    }
}
