package com.jtlrm.ckd.util.ContactList;

import android.text.TextUtils;
import android.util.Log;

import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.util.HanziToPinyin;
import com.jtlrm.ckd.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class InitialLetterUitl {

    /**
     * set initial letter of according user's nickname( username if no nickname)
     *
     * @param user
     */
    public static void setUserInitialLetter(UserEntity user) {
        final String DefaultLetter = "#";
        String letter = DefaultLetter;

        final class GetInitialLetter {
            String getLetter(String name) {
                if (TextUtils.isEmpty(name)) {
                    return DefaultLetter;
                }
                char char0 = name.toLowerCase().charAt(0);
                if (Character.isDigit(char0)) {
                    return DefaultLetter;
                }
                ArrayList<HanziToPinyin.Token> l = HanziToPinyin.getInstance().get(name.substring(0, 1));
                if (l != null && l.size() > 0 && l.get(0).target.length() > 0) {
                    HanziToPinyin.Token token = l.get(0);
                    String letter = token.target.substring(0, 1).toUpperCase();
                    char c = letter.charAt(0);
                    if (c < 'A' || c > 'Z') {
                        return DefaultLetter;
                    }
                    return letter;
                }
                return DefaultLetter;
            }
        }

        if (!TextUtils.isEmpty(user.getUsername())) {
            letter = new GetInitialLetter().getLetter(user.getUsername());
            user.setInitialLetter(letter);
            return;
        }
        if (letter.equals(DefaultLetter) && !TextUtils.isEmpty(user.getUsername())) {
            letter = new GetInitialLetter().getLetter(user.getUsername());
        }
        user.setInitialLetter(letter);
    }


    /**
     * 使用选择排序算法
     *
     * @param userEntities
     */
    public static void sortList(List<UserEntity> userEntities) {
        if (userEntities == null ){
            return;
        }
        UserEntity[] sort = new UserEntity[userEntities.size()];
        userEntities.toArray(sort);
        int minIndex;
        UserEntity temp;
        for (int i = 0; i < sort.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < sort.length; j++) {
                if (!compareUser(sort[j], sort[minIndex])) { // 如果小于就交换位置
                    minIndex = j;
                }
            }
            temp = sort[i];
            sort[i] = sort[minIndex];
            sort[minIndex] = temp;
        }
        userEntities.clear();
        for (UserEntity userEntity : sort) {
            userEntities.add(userEntity);
        }
    }

    /**
     * 判断两个用户姓名排序， 如果one 大于 two 返回true ，反之返回false
     *
     * @param one
     * @param two
     * @return
     */
    public static boolean compareUser(UserEntity one, UserEntity two) {
        Log.e("Test",  one.getPinyin() + "--" + two.getPinyin()+"=" + comparePinYin(one.getPinyin(), two.getPinyin()));
        return comparePinYin(one.getPinyin(), two.getPinyin());
    }

    /**
     * 判断 one 是否大于two， 是返回true 否返回false
     *
     * @param one
     * @param two
     * @return
     */
    public static boolean comparePinYin(String one, String two) {
        char[] ones = one.toCharArray();
        char[] twos = two.toCharArray();
        int oneLength = ones.length;
        int twoLength = twos.length;
        if (oneLength < twoLength) {
            for (int i = 0; i < oneLength; i++) {
                if (ones[i] > twos[i]) {
                    return true;
                } else if (ones[i] < twos[i]){
                    return false;
                }
            }
        } else {
            for (int i = 0; i < twoLength; i++) {
                if (ones[i] > twos[i]) {
                    return true;
                } else if (ones[i] < twos[i]){
                    return false;
                }
            }
        }
        return false;
    }
}
