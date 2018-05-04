package com.jtlrm.ckd.util.ContactList;

import android.text.TextUtils;

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
                if (l != null && l.size() > 0 && l.get(0).target.length() > 0)
                {
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

        if ( !TextUtils.isEmpty(user.getUsername()) ) {
            letter = new GetInitialLetter().getLetter(user.getUsername());
            user.setInitialLetter(letter);
            return;
        }
        if (letter.equals(DefaultLetter) && !TextUtils.isEmpty(user.getUsername())) {
            letter = new GetInitialLetter().getLetter(user.getUsername());
        }
        user.setInitialLetter(letter);
    }


    public static void sortList(List<UserEntity> userEntities) {
        int minIndex;
        UserEntity temp;
        for (int i = 0; i < userEntities.size()- 1; i++){
            minIndex = i;
//            for (int j = i + 1; j< userEntities.size(); j ++) {
//                if (userEntities.get(j) < userEntities.get(minIndex)) {
//                    minIndex = j;
//                }
//            }
//            temp = userEntities.get(i);
//            userEntities. = userEntities.get(minIndex);
        }
    }
}
