package com.ys.yarc.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.base.sdk.util.ImageUtil;
import com.ys.yarc.R;
import com.ys.yarc.base.fragment.BaseFragment;
import com.ys.yarc.mvp.view.activity.ChangePasswordActivity;
import com.ys.yarc.mvp.view.activity.NoticeSettingActivity;
import com.ys.yarc.mvp.view.activity.PersonInfoActivity;
import com.ys.yarc.mvp.view.activity.RenCaiDengJiActivity;
import com.ys.yarc.mvp.view.activity.UpdateAppActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;

/**
 * 个人中心
 */
public class PersonFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.person_top_image)
    ImageView personTopImage;
    @BindViews({R.id.person_1, R.id.person_2, R.id.person_3, R.id.person_4, R.id.person_5})
    List<RelativeLayout> listRelative;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_person;
    }

    @Override
    protected void initView() {
        ImageUtil.loadImageGif(personTopImage, R.drawable.person_top);
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
                startActivity(new Intent(context, PersonInfoActivity.class));
                break;
            case R.id.person_2:
                startActivity(new Intent(context, ChangePasswordActivity.class));
                break;
            case R.id.person_3:
                startActivity(new Intent(context, RenCaiDengJiActivity.class));
                break;
            case R.id.person_4:
                startActivity(new Intent(context, UpdateAppActivity.class));
                break;
            case R.id.person_5:
                startActivity(new Intent(context, NoticeSettingActivity.class));
                break;
        }
    }
}
