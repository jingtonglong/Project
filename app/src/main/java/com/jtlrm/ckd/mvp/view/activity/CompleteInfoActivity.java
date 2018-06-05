package com.jtlrm.ckd.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.jtlrm.ckd.entity.HospitalEntity;
import com.jtlrm.ckd.entity.submitEntity.RegisterData;
import com.jtlrm.ckd.mvp.model.HospitalModel;
import com.jtlrm.ckd.mvp.model.UserModel;
import com.jtlrm.ckd.util.FilterAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class CompleteInfoActivity extends TitleBarActivity {

    public static void goActivity(Context context, String password, String phone) {
        Intent intent = new Intent(context, CompleteInfoActivity.class);
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
    AutoCompleteTextView hospitalE;
    @BindView(R.id.work)
    RadioGroup workR;
    RegisterData submitData = new RegisterData();
    TimePickerView birthDay;
    HospitalModel hospitalModel;
    List<HospitalEntity> hospitalEntities;
    UserModel userModel;

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
                submitData.setBirthday(date.getTime());
            }
        }).build();
        hospitalModel = new HospitalModel();
        userModel = new UserModel();
        Intent intent = getIntent();
        submitData.setPassword(intent.getStringExtra("password"));
        submitData.setPassword2(intent.getStringExtra("password"));
        submitData.setLoginPhone(intent.getStringExtra("phone"));
        submitData.setSex(0);
        submitData.setRole(0);
    }

    @Override
    protected void obtainData() {
        searchContent();
    }

    @Override
    protected void initEvent() {
        sexR.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.man) {
                    submitData.setSex(0);
                } else if (checkedId == R.id.woman) {
                    submitData.setSex(1);
                }
            }
        });

        workR.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.doctor) {
                    submitData.setRole(0);
                } else if (checkedId == R.id.nurse) {
                    submitData.setRole(1);
                }
            }
        });
        birthdatT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthDay.show();
            }
        });
      hospitalE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              submitData.setHospitalId(hospitalEntities.get(position).getId());
          }
      });
    }


    public void searchContent() {
        showLoadingDialog("加载中");
        hospitalModel.queryHospital("", new CommonObserver<List<HospitalEntity>>() {
            @Override
            public void onError(int errType, String errMessage) {
                showToast(errMessage);
                dismissLoadingDialog();
            }

            @Override
            public void onResult(List<HospitalEntity> data) {
                hospitalEntities = data;
                dismissLoadingDialog();
                List<String> list = new ArrayList<>();
                for (HospitalEntity entity:data) {
                    list.add(entity.getName());
                }
                FilterAdapter<String> adapter = new FilterAdapter<>(context,
                        android.R.layout.simple_dropdown_item_1line, list);
                hospitalE.setAdapter(adapter);
            }
        }, lifecycleSubject);
    }


    public void submit(View v) {
        submitData.setName(nameE.getText() + "");
        if (!invalidata()) {
            return;
        }
        showLoadingDialog("提交中");
        userModel.register(submitData, new CommonObserver<Object>() {
            @Override
            public void onError(int errType, String errMessage) {
                dismissLoadingDialog();
                showToast(errMessage);
            }

            @Override
            public void onResult(Object data) {
                dismissLoadingDialog();
                startActivity(new Intent(context, RegisterSuccessfulActivity.class));
                finish();
            }
        }, lifecycleSubject);
    }

    private boolean invalidata() {
        if (!notEmpty(submitData.getName())) {
            showToast("请填写姓名");
            return false;
        }
        if (submitData.getBirthday() == null) {
            showToast("请选择出生日期");
            return false;
        }
        if (submitData.getHospitalId() == null) {
            showToast("请选择所在医院");
            return false;
        }
        return true;
    }
}
