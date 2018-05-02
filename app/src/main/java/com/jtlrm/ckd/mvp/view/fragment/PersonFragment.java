package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.base.sdk.util.ImageUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.ui.MainActivity;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;
import com.jtlrm.ckd.mvp.view.activity.ChangePasswordActivity;
import com.jtlrm.ckd.mvp.view.activity.LoginActivity;
import com.jtlrm.ckd.mvp.view.activity.MyQRCodeActivity;
import com.jtlrm.ckd.mvp.view.activity.NoticeSettingActivity;
import com.jtlrm.ckd.mvp.view.activity.PersonInfoActivity;
import com.jtlrm.ckd.mvp.view.activity.RenCaiDengJiActivity;
import com.jtlrm.ckd.mvp.view.activity.UpdateAppActivity;
import com.jtlrm.ckd.mvp.view.activity.ZhangHaoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;

/**
 * 个人中心
 */
public class PersonFragment extends BaseFragment implements View.OnClickListener {


    @BindViews({R.id.person_1, R.id.person_2, R.id.person_3, R.id.person_4, R.id.person_5})
    List<RelativeLayout> listRelative;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_person;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        for (RelativeLayout re : listRelative) {
            re.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.person_1:
                startActivity(new Intent(context, MyQRCodeActivity.class));
                break;
            case R.id.person_2:
                startActivity(new Intent(context, PersonInfoActivity.class));
                break;
            case R.id.person_3:
                startActivity(new Intent(context, ZhangHaoActivity.class));
                break;
            case R.id.person_4:
                startActivity(new Intent(context, UpdateAppActivity.class));
                break;
            case R.id.person_5:
                loginout();
                break;
        }
    }

    public void loginout() {
        showLoadingDialog("退出中");
        DemoHelper.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        dismissLoadingDialog();
                        getActivity().finish();
                        startActivity(new Intent(getActivity(), LoginActivity.class));

                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        dismissLoadingDialog();
                        getActivity().finish();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }
                });
            }
        });
    }
}
