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

public class HospitalAdapter extends BaseAdapter {

    Context context;
    ArrayList<Hospital> list;
    LayoutInflater inflater;

    public HospitalAdapter(Context context, ArrayList<Hospital> list) {
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

        view = (view == null) ? inflater.inflate(R.layout.layout_hospital_item,null) : view;

        ImageView hospitalIcon = view.findViewById(R.id.hospitalIcon);
        TextView hospitalTitle = view.findViewById(R.id.hospitalName);
        Hospital h = list.get(position);

        hospitalIcon.setImageResource(h.getHospitalIcon());
        hospitalTitle.setText(h.getHospitalName());

        return view;
    }
}
