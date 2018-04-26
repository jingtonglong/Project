package com.base.sdk.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;

import com.base.sdk.R;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8/008.
 */

public class PopupWindowUtil {

    /**
     * 显示弹出框方法
     *
     * @param datas
     * @param listener
     */
    public void showPopupwindow(List<String> datas, Context context, final PopupwindowClickListenner listener) {
        if (context != null && datas.size() > 0) {
            ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
            for (String s : datas) {
                DialogMenuItem item = new DialogMenuItem(s, 0);
                mMenuItems.add(item);
            }
            final NormalListDialog dialog = new NormalListDialog(context, mMenuItems);
            dialog.title("请选择")//
                    .titleTextSize_SP(18)//
                    .titleBgColor(Color.parseColor("#409ED7"))//
                    .itemPressColor(Color.parseColor("#85D3EF"))//
                    .itemTextColor(Color.parseColor("#303030"))//
                    .itemTextSize(14)//
                    .cornerRadius(0)//
                    .widthScale(0.8f)//
                    .show(R.style.myDialogAnim);

            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                @Override
                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listener.click(position);
                    dialog.dismiss();
                }
            });
        }
    }

    public interface PopupwindowClickListenner {
        void click(int position);
    }

}
