package com.jtlrm.ckd.mvp.view.activity;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.base.sdk.base.api.PositiveOrCancelInterface;
import com.base.sdk.util.NotificationsUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMClientListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMultiDeviceListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chatuidemo.Constant;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.db.InviteMessgeDao;
import com.hyphenate.chatuidemo.runtimepermissions.PermissionsManager;
import com.hyphenate.chatuidemo.runtimepermissions.PermissionsResultAction;
import com.hyphenate.chatuidemo.ui.*;
import com.hyphenate.chatuidemo.ui.LoginActivity;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.mvp.model.MainPresent;
import com.jtlrm.ckd.mvp.view.api.IMainView;
import com.jtlrm.ckd.mvp.view.fragment.HomeFragment;
import com.jtlrm.ckd.mvp.view.fragment.NewChatFragment;
import com.jtlrm.ckd.mvp.view.fragment.PersonFragment;
import com.jtlrm.ckd.mvp.view.fragment.HuanZheGuanLiFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainView, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_group)
    RadioGroup radioGroup;
    FragmentManager manager;
    HomeFragment homeFragment;
    HuanZheGuanLiFragment renCaiFragment;
    NewChatFragment chatFragment;
    PersonFragment personFragment;
    private List<Fragment> fragments = new ArrayList<>();
    private int currentFragmentIndex = 0;
    MainPresent present;
    private android.app.AlertDialog.Builder exceptionBuilder;
    private boolean isExceptionDialogShow = false;
    private BroadcastReceiver internalDebugReceiver;
    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager broadcastManager;
    public boolean isConflict = false;
    private InviteMessgeDao inviteMessgeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                try {
                    //some device doesn't has activity to handle this intent
                    //so add try catch
                    Intent intent = new Intent();
                    intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                    intent.setData(Uri.parse("package:" + packageName));
                    startActivity(intent);
                } catch (Exception e) {
                }
            }
        }
        //make sure activity will not in background if user is logged into another device or removed
        if (getIntent() != null &&
                (getIntent().getBooleanExtra(Constant.ACCOUNT_REMOVED, false) ||
                        getIntent().getBooleanExtra(Constant.ACCOUNT_KICKED_BY_CHANGE_PASSWORD, false) ||
                        getIntent().getBooleanExtra(Constant.ACCOUNT_KICKED_BY_OTHER_DEVICE, false))) {
            DemoHelper.getInstance().logout(false, null);
            finish();
            startActivity(new Intent(this, com.hyphenate.chatuidemo.ui.LoginActivity.class));
            return;
        } else if (getIntent() != null && getIntent().getBooleanExtra("isConflict", false)) {
            finish();
            startActivity(new Intent(this, com.hyphenate.chatuidemo.ui.LoginActivity.class));
            return;
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initBarColor() {
        //super.initBarColor();
    }

    @Override
    protected void initView() {
        requestPermissions();
        manager = getSupportFragmentManager();
        present = new MainPresent(this);
        inviteMessgeDao = new InviteMessgeDao(this);
        showExceptionDialogFromIntent(getIntent());
        //register broadcast receiver to receive the change of group from DemoHelper
        registerBroadcastReceiver();


        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
        EMClient.getInstance().addClientListener(clientListener);
        EMClient.getInstance().addMultiDeviceListener(new MyMultiDeviceListener());
        //debug purpose only
        registerInternalDebugReceiver();
    }

    @Override
    protected void obtainData() {
        FragmentTransaction ft = manager.beginTransaction();
        if (homeFragment == null) homeFragment = new HomeFragment();
        if (renCaiFragment == null) renCaiFragment = new HuanZheGuanLiFragment();
        if (chatFragment == null) chatFragment = NewChatFragment.newInstance();
        if (personFragment == null) personFragment = new PersonFragment();
        fragments.add(homeFragment);
        fragments.add(renCaiFragment);
        fragments.add(chatFragment);
        fragments.add(personFragment);
        ft.add(R.id.main_container, fragments.get(currentFragmentIndex));
        ft.commit();
    }

    @Override
    protected void initEvent() {
        radioGroup.setOnCheckedChangeListener(this);
        checkPermission();
        // checkJpush();
    }


    /**
     * 检查推送状态
     */
    private void checkJpush() {
//        if (JPushInterface.isPushStopped(getApplicationContext())) {
//            JPushInterface.resumePush(getApplicationContext());
//        }
    }


    /**
     * 查看权限检测
     */
    private void checkPermission() {
        if (!NotificationsUtils.isNotificationEnabled(getApplicationContext())) {
            // 如果不能使用
            showCustomDialog("通知权限被关闭，将会影响部分功能使用，马上去打开", new PositiveOrCancelInterface() {
                @Override
                public void result(boolean result) {
                    if (result) {
                        NotificationsUtils.requestPermission(2007, context);
                    }
                }
            });
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int temp = 0;
        switch (checkedId) {
            case R.id.main_group_button_1:
                temp = 0;    // 首页
                break;
            case R.id.main_group_button_2:
                temp = 1;    // 通知公告
                break;
            case R.id.main_group_button_3:
                temp = 2;    // 在线随访
                break;
            case R.id.main_group_button_4:
                temp = 3;    // 个人中心
                break;
        }
        if (temp == currentFragmentIndex) {
            return;
        }
        Fragment fragment = fragments.get(temp);
        FragmentTransaction ft = manager.beginTransaction();
        if (fragment.isAdded()) {
            ft.show(fragment);
        } else {
            ft.add(R.id.main_container, fragment);
        }
        ft.hide(fragments.get(currentFragmentIndex));
        currentFragmentIndex = temp;
        ft.commit();
    }


    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
//				Toast.makeText(MainActivity.this, "All permissions have been granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permission) {
                //Toast.makeText(MainActivity.this, "Permission " + permission + " has been denied", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * show the dialog when user met some exception: such as login on another device, user removed or user forbidden
     */
    private void showExceptionDialog(String exceptionType) {
        isExceptionDialogShow = true;
        DemoHelper.getInstance().logout(false, null);
        String st = getResources().getString(com.hyphenate.chatuidemo.R.string.Logoff_notification);
        if (!MainActivity.this.isFinishing()) {
            // clear up global variables
            try {
                if (exceptionBuilder == null)
                    exceptionBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
                exceptionBuilder.setTitle(st);
                exceptionBuilder.setMessage(getExceptionMessageId(exceptionType));
                exceptionBuilder.setPositiveButton(com.hyphenate.chatuidemo.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        exceptionBuilder = null;
                        isExceptionDialogShow = false;
                        finish();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                exceptionBuilder.setCancelable(false);
                exceptionBuilder.create().show();
                isConflict = true;
            } catch (Exception e) {

            }
        }
    }

    private int getExceptionMessageId(String exceptionType) {
        if (exceptionType.equals(Constant.ACCOUNT_CONFLICT)) {
            return com.hyphenate.chatuidemo.R.string.connect_conflict;
        } else if (exceptionType.equals(Constant.ACCOUNT_REMOVED)) {
            return com.hyphenate.chatuidemo.R.string.em_user_remove;
        } else if (exceptionType.equals(Constant.ACCOUNT_FORBIDDEN)) {
            return com.hyphenate.chatuidemo.R.string.user_forbidden;
        }
        return com.hyphenate.chatuidemo.R.string.Network_error;
    }

    private void showExceptionDialogFromIntent(Intent intent) {
        if (!isExceptionDialogShow && intent.getBooleanExtra(Constant.ACCOUNT_CONFLICT, false)) {
            showExceptionDialog(Constant.ACCOUNT_CONFLICT);
        } else if (!isExceptionDialogShow && intent.getBooleanExtra(Constant.ACCOUNT_REMOVED, false)) {
            showExceptionDialog(Constant.ACCOUNT_REMOVED);
        } else if (!isExceptionDialogShow && intent.getBooleanExtra(Constant.ACCOUNT_FORBIDDEN, false)) {
            showExceptionDialog(Constant.ACCOUNT_FORBIDDEN);
        } else if (intent.getBooleanExtra(Constant.ACCOUNT_KICKED_BY_CHANGE_PASSWORD, false) ||
                intent.getBooleanExtra(Constant.ACCOUNT_KICKED_BY_OTHER_DEVICE, false)) {
            this.finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    private void registerBroadcastReceiver() {
        broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(Constant.ACTION_GROUP_CHANAGED);
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                updateUnreadLabel();
                updateUnreadAddressLable();
                if (currentFragmentIndex == 2) {
                    // refresh conversation list
                    if (chatFragment != null) {
                        chatFragment.refresh();
                    }
                }
                String action = intent.getAction();
//                if (action.equals(Constant.ACTION_GROUP_CHANAGED)) {
//                    if (EaseCommonUtils.getTopActivity(getActivity()).equals(GroupsActivity.class.getName())) {
//                        GroupsActivity.instance.onResume();
//                    }
//                }
            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    EMClientListener clientListener = new EMClientListener() {
        @Override
        public void onMigrate2x(boolean success) {
            Toast.makeText(MainActivity.this, "onUpgradeFrom 2.x to 3.x " + (success ? "success" : "fail"), Toast.LENGTH_LONG).show();
            if (success) {
                refreshUIWithMessage();
            }
        }
    };

    /**
     * update the total unread count
     */
    public void updateUnreadAddressLable() {
        runOnUiThread(new Runnable() {
            public void run() {
                int count = getUnreadAddressCountTotal();
//                if (count > 0) {
//                    unreadAddressLable.setVisibility(View.VISIBLE);
//                } else {
//                    unreadAddressLable.setVisibility(View.INVISIBLE);
//                }
            }
        });

    }

    /**
     * get unread event notification count, including application, accepted, etc
     *
     * @return
     */
    public int getUnreadAddressCountTotal() {
        int unreadAddressCountTotal = 0;
        unreadAddressCountTotal = inviteMessgeDao.getUnreadMessagesCount();
        return unreadAddressCountTotal;
    }


    /**
     * debug purpose only, you can ignore this
     */
    private void registerInternalDebugReceiver() {
        internalDebugReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                DemoHelper.getInstance().logout(false, new EMCallBack() {

                    @Override
                    public void onSuccess() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                finish();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        });
                    }

                    @Override
                    public void onProgress(int progress, String status) {
                    }

                    @Override
                    public void onError(int code, String message) {
                    }
                });
            }
        };
        IntentFilter filter = new IntentFilter(getPackageName() + ".em_internal_debug");
        registerReceiver(internalDebugReceiver, filter);
    }

    public class MyContactListener implements EMContactListener {
        @Override
        public void onContactAdded(String username) {
        }

        @Override
        public void onContactDeleted(final String username) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (ChatActivity.activityInstance != null && ChatActivity.activityInstance.toChatUsername != null &&
                            username.equals(ChatActivity.activityInstance.toChatUsername)) {
                        String st10 = getResources().getString(com.hyphenate.chatuidemo.R.string.have_you_removed);
                        Toast.makeText(MainActivity.this, ChatActivity.activityInstance.getToChatUsername() + st10, Toast.LENGTH_LONG)
                                .show();
                        ChatActivity.activityInstance.finish();
                    }
                }
            });
            //updateUnreadAddressLable();
        }

        @Override
        public void onContactInvited(String username, String reason) {
        }

        @Override
        public void onFriendRequestAccepted(String username) {
        }

        @Override
        public void onFriendRequestDeclined(String username) {
        }
    }

    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
                updateUnreadLabel();
                if (currentFragmentIndex == 2) {
                    // refresh conversation list
                    if (chatFragment != null) {
                        chatFragment.refresh();
                    }
                }
            }
        });
    }

    /**
     * get unread message count
     *
     * @return
     */
    public int getUnreadMsgCountTotal() {
        return EMClient.getInstance().chatManager().getUnreadMsgsCount();
    }

    /**
     * update unread message count
     */
    public void updateUnreadLabel() {
        int count = getUnreadMsgCountTotal();
        // 更新消息
    }

    public class MyMultiDeviceListener implements EMMultiDeviceListener {

        @Override
        public void onContactEvent(int event, String target, String ext) {

        }

        @Override
        public void onGroupEvent(int event, String target, final List<String> username) {
            switch (event) {
                case EMMultiDeviceListener.GROUP_LEAVE:
                    ChatActivity.activityInstance.finish();
                    break;
                default:
                    break;
            }
        }
    }
}
