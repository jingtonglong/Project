package com.jtlrm.ckd.huanxin;

import com.hyphenate.chatuidemo.ui.ConversationListFragment;
import com.jtlrm.ckd.mvp.view.activity.MainActivity;

/**
 * 最近会话
 */
public class MyConversationFragment extends ConversationListFragment {

    public static MyConversationFragment newInstance() {
        MyConversationFragment fragment = new MyConversationFragment();
        return fragment;
    }

    @Override
    public void updateUnread() {
        ((MainActivity) getActivity()).updateUnreadLabel();
    }
}
