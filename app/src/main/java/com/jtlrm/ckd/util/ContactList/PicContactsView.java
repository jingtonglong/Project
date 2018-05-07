package com.jtlrm.ckd.util.ContactList;

import android.content.Context;
import android.util.AttributeSet;

import com.jtlrm.ckd.entity.UserEntity;

import java.util.ArrayList;

public class PicContactsView extends ContactListView {
    public PicContactsView(Context context) {
        super(context);
    }

    public PicContactsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PicContactsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected ContactAdapter getAdapter() {
        return  new PicContactsAdapter(context, 0, new ArrayList<UserEntity>(contactList));
    }

    /**
     * 选中所有
     */
    public void selectAll() {
        if (adapter != null) {
            ((PicContactsAdapter)adapter).selectAll();
        }
    }

    public void clearAll() {
        if (adapter != null) {
            ((PicContactsAdapter)adapter).clearAll();
        }
    }

}
