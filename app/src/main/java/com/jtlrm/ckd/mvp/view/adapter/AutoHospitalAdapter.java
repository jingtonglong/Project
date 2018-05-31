package com.jtlrm.ckd.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.entity.HospitalEntity;

import java.util.ArrayList;
import java.util.List;

public class AutoHospitalAdapter extends ArrayAdapter<HospitalEntity> {

    private int mResourceId;
    LayoutInflater inflater ;
    public AutoHospitalAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.mResourceId = resource;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(mResourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(getItem(position).getName());
        return convertView;
    }

    private class ViewHolder{
        TextView textView;
    }
}
