package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.base.sdk.widget.TitleBar;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroupOptions;
import com.hyphenate.exceptions.HyphenateException;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;

import butterknife.BindView;

public class CreateGroupActivity extends BaseActivity {
    @BindView(R.id.create_group_title)
    TitleBar titleBar;
    @BindView(R.id.edit_group_name)
     EditText groupNameEditText;
    @BindView(R.id.edit_group_introduction)
    EditText introductionEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_create_group);

    }

    @Override
    protected void initView() {
        titleBar.tvMiddle.setText("修改密码");
        titleBar.imgLeft.setVisibility(View.VISIBLE);
        titleBar.imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.tvRight.setText("保存");
        titleBar.tvRight.setVisibility(View.VISIBLE);
        titleBar.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(context, PickContactsActivity.class), 1002);
            }
        });
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String st1 = getResources().getString(com.hyphenate.chatuidemo.R.string.Is_to_create_a_group_chat);
        final String st2 = getResources().getString(com.hyphenate.chatuidemo.R.string.Failed_to_create_groups);
        if (resultCode == RESULT_OK) {
            //new group
            showLoadingDialog(st1);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String groupName = groupNameEditText.getText().toString().trim();
                    String desc = introductionEditText.getText().toString();
                    String[] members = data.getStringArrayExtra("newmembers");
                    try {
                        EMGroupOptions option = new EMGroupOptions();
                        option.maxUsers = 200;
                        option.inviteNeedConfirm = true;

                        String reason = getString(com.hyphenate.chatuidemo.R.string.invite_join_group);
                        reason  = EMClient.getInstance().getCurrentUser() + reason + groupName;

//                        if(publibCheckBox.isChecked()){
//                            option.style = memberCheckbox.isChecked() ? EMGroupManager.EMGroupStyle.EMGroupStylePublicJoinNeedApproval : EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;
//                        }else{
//                            option.style = memberCheckbox.isChecked()? EMGroupManager.EMGroupStyle.EMGroupStylePrivateMemberCanInvite: EMGroupManager.EMGroupStyle.EMGroupStylePrivateOnlyOwnerInvite;
//                        }
                        EMClient.getInstance().groupManager().createGroup(groupName, desc, members, reason, option);
                        runOnUiThread(new Runnable() {
                            public void run() {
                               dismissLoadingDialog();
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
                    } catch (final HyphenateException e) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                               dismissLoadingDialog();
                                showToast(st2 + e.getLocalizedMessage());
                            }
                        });
                    }

                }
            }).start();
        }
    }
}
