package com.jtlrm.ckd.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.base.sdk.base.net.CommonObserver;
import com.base.sdk.util.DateFormatUtil;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.mvp.model.HospitalModel;

import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class CompleteInfoActivity extends TitleBarActivity {

    public static void goActivity(Context context, String password, String phone) {
        Intent intent = new Intent(context, SettingPasswordActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("password", password);
        context.startActivity(intent);
    }

    @BindView(R.id.name)
    EditText nameE;
    @BindView(R.id.sex)
    RadioGroup sexR;
    @BindView(R.id.birthday)
    TextView birthdatT;
    @BindView(R.id.hospital)
    EditText hospitalE;
    @BindView(R.id.work)
    RadioGroup workR;
    int sex = -1;
    int work = -1;
    Long birthday;
    TimePickerView birthDay;
    HospitalModel hospitalModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "完善资料";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_complete_info);
    }

    @Override
    protected void initView() {
        birthDay = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                birthdatT.setText(DateFormatUtil.dateFormatAll(date.getTime()));
                birthday = date.getTime();
            }
        }).build();
        hospitalModel = new HospitalModel();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        sexR.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.man) {
                    sex = 0;
                } else if (checkedId == R.id.woman) {
                    sex = 1;
                }
            }
        });

        workR.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.doctor) {
                    work = 0;
                } else if (checkedId == R.id.nurse) {
                    work = 1;
                }
            }
        });
        birthdatT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthDay.show();
            }
        });
        hospitalE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchContent();
            }
        });
    }


    public void searchContent() {
        String content = hospitalE.getText() + "";
        if (notEmpty(content)) {
            hospitalModel.queryHospital(content, new CommonObserver<List<HospitalModel>>() {
                @Override
                public void onError(int errType, String errMessage) {

                }

                @Override
                public void onResult(List<HospitalModel> data) {

                }
            }, lifecycleSubject);
        } else {

        }
    }


    public void submit(View v) {
        startActivity(new Intent(context, RegisterSuccessfulActivity.class));
    }
}
