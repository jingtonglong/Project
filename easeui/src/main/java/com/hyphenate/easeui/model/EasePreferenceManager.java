package com.hyphenate.easeui.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.hyphenate.easeui.EaseUI;

import java.util.HashSet;
import java.util.Set;

public class EasePreferenceManager {
    private SharedPreferences.Editor editor;
    private SharedPreferences mSharedPreferences;
    private static final String KEY_AT_GROUPS = "AT_GROUPS";
    private static final String KEY_NOTIFICATION = "notification_group"; //屏蔽群消息

    @SuppressLint("CommitPrefEdits")
    private EasePreferenceManager(){
        mSharedPreferences = EaseUI.getInstance().getContext().getSharedPreferences("EM_SP_AT_MESSAGE", Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }
    private static EasePreferenceManager instance;
    
    public synchronized static EasePreferenceManager getInstance(){
        if(instance == null){
            instance = new EasePreferenceManager();
        }
        return instance;
        
    }
    
    
    public void setAtMeGroups(Set<String> groups) {
        editor.remove(KEY_AT_GROUPS);
        editor.putStringSet(KEY_AT_GROUPS, groups);
        editor.apply();
    }
    
    public Set<String> getAtMeGroups(){
        return mSharedPreferences.getStringSet(KEY_AT_GROUPS, null);
    }


    /**
     * 关闭群消息
     */
    public void closeNotification(String groupId) {
        Set<String> groups = getGroupNotification();
        if (groups != null) {
            if (!groups.contains(groupId)) {
                groups.add(groupId);
                editor.putStringSet(KEY_NOTIFICATION, groups);
                editor.apply();
                updateNotification();
            }
        } else {
            groups = new HashSet<>();
            groups.add(groupId);
            editor.putStringSet(KEY_NOTIFICATION, groups);
            editor.apply();
            updateNotification();
        }
    }

    /**
     * 打开群消息
     * @param groupId
     */
    public void openNotification(String groupId) {
        Set<String> groups = getGroupNotification();
        if (groups != null && groups.contains(groupId)) {
            groups.remove(groupId);
            editor.putStringSet(KEY_NOTIFICATION, groups);
            editor.apply();
            updateNotification();
        }
    }

    private void updateNotification() {
        groups = mSharedPreferences.getStringSet(KEY_NOTIFICATION, null);
    }

    /**
     * 获取群消息组
     * @return
     */
    Set<String> groups;
    public Set<String> getGroupNotification() {
        return groups;
    }

    /**
     * 判断群消息是否关闭
     * @param groupId
     * @return
     */
    public boolean groupIsClose(String groupId) {
        Set<String> groups = getGroupNotification();
        if (groups != null && groups.contains(groupId)) {
            return true;
        }
        return false;
    }

}
