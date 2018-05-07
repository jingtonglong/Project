package com.jtlrm.ckd.util.ContactList;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.utils.EaseUserUtils;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择联系人
 */
public class PicContactsAdapter extends ContactAdapter {
    /** members already in the group */
    private List<UserEntity> existMembers;

    public PicContactsAdapter(Context context, int resource, List<UserEntity> objects) {
        super(context, resource, objects);
        if(existMembers == null)
            existMembers = new ArrayList<>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            if(res == 0)
                convertView = layoutInflater.inflate(R.layout.row_contact_with_checkbox, parent, false);
            else
                convertView = layoutInflater.inflate(res, null);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.nameView = (TextView) convertView.findViewById(R.id.name);
            holder.headerView = (TextView) convertView.findViewById(R.id.header);
            holder.select = convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        final UserEntity user = getItem(position);
        if(user == null)
            Log.d("ContactAdapter", position + "");
        final String username = user.getUsername();
        final String header = user.getInitialLetter();

        if (position == 0 || header != null && !header.equals(getItem(position - 1).getInitialLetter())) {
            if (TextUtils.isEmpty(header)) {
                holder.headerView.setVisibility(View.GONE);
            } else {
                holder.headerView.setVisibility(View.VISIBLE);
                holder.headerView.setText(header);
            }
        } else {
            holder.headerView.setVisibility(View.GONE);
        }
        EaseUserUtils.setUserNick(username, holder.nameView);
        EaseUserUtils.setUserAvatar(getContext(), username, holder.avatar);

        if (holder.select != null) {
            if(existMembers != null && existMembers.contains(user)){
                holder.select.setChecked(true);
            }else{
                holder.select.setChecked(false);
            }
            holder.select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // check the exist members
                    if (holder.select.isChecked()) {
                        if (!existMembers.contains(user)) {
                            existMembers.add(user);
                        }
                    }else {
                        if (existMembers.contains(user)) {
                            existMembers.remove(user);
                        }
                    }
                }
            });
        }
        return convertView;
    }

    private static class ViewHolder {
        CheckBox select;
        ImageView avatar;
        TextView nameView;
        TextView headerView;
    }

    public void selectAll() {
        if (existMembers != null && userList != null) {
            existMembers.clear();
            for (UserEntity userEntity: userList) {
                existMembers.add(userEntity);
            }
            notifyDataSetChanged();
        }
    }

    public void clearAll() {
        if (existMembers != null && userList != null) {
            existMembers.clear();
            notifyDataSetChanged();
        }
    }
}
