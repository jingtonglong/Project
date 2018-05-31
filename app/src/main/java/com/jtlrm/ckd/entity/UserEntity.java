package com.jtlrm.ckd.entity;

import com.github.promeg.pinyinhelper.Pinyin;
import com.jtlrm.ckd.util.ContactList.InitialLetterUitl;

/**
 * Created by Administrator on 2017/12/12/012.
 * 用户类
 */

public class UserEntity  {

    private String headPicture;

    private String name;

    private String sex;

    private String brithday;

    private String mobile;

    private String role;

    private String hospitalName;

    private String initialLetter;

    private String pinyin;

    public String getPinyin() {
        if (pinyin == null) {
            pinyin = Pinyin.toPinyin(name, "");
        }
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getInitialLetter() {
        if(initialLetter == null){
            InitialLetterUitl.setUserInitialLetter(this);
        }
        return initialLetter;
    }

    public void setInitialLetter(String initialLetter) {
        this.initialLetter = initialLetter;
    }


    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
