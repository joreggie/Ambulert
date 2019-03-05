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

public class ReportAdapter extends BaseAdapter {

    Context context ;
    ArrayList<Report> reportList;
    LayoutInflater inflater;

    public ReportAdapter(Context context, ArrayList<Report> reportList) {
        this.context = context;
        this.reportList = reportList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return reportList.size();
    }

    @Override
    public Object getItem(int position) {
        return reportList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        view = (view == null) ? inflater.inflate(R.layout.layout_history_item,null) : view;

        ImageView reportImage = view.findViewById(R.id.reportImage);
        TextView reportTitle = view.findViewById(R.id.reportTitle);
        TextView reportDate = view.findViewById(R.id.reportDate);
        TextView reportTime = view.findViewById(R.id.reportTime);
        TextView reportType = view.findViewById(R.id.reportType);
        Report r = reportList.get(position);

        reportImage.setImageResource(r.getReport_image());
        reportTitle.setText(r.getReport_title());
        reportDate.setText(r.getReport_date());
        reportTime.setText(r.getReport_time());
        reportType.setText(r.getReport_type());
        return view;
    }
}
