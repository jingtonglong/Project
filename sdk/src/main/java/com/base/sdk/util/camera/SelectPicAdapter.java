package com.base.sdk.util.camera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.base.sdk.R;


/**
 * 图片选择适配器
 * Created by Tyrese on 2016/7/19.
 */
public class SelectPicAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context context;

    private String[] data = new String[]{"相机", "图片"};

    public SelectPicAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_select_pic, null);
            viewHolder.mText = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mText.setText(data[position]);
        return convertView;
    }

    class ViewHolder {
        TextView mText;
    }
}
