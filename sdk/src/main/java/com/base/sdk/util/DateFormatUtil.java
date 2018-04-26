package com.base.sdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/12/012.
 */

public class DateFormatUtil {
    /**
     * 时间格式化
     *
     * @return
     */
    public static String dateFormat(Long date) {
        if (date != null) {
            return new SimpleDateFormat("yyyy年MM月").format(new Date(date));
        } else {
            return "";
        }
    }

    /**
     * 时间格式化
     *
     * @return
     */
    public static String dateFormatAll(Long date) {
        if (date != null) {
            return new SimpleDateFormat("yyyy年MM月dd日").format(new Date(date));
        } else {
            return "";
        }
    }

    /**
     * 时间格式化
     *
     * @return
     */
    public static String dateFormatLine(Long date) {
        if (date != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date(date));
        } else {
            return "";
        }
    }

    /**
     * 时间格式化
     *
     * @return
     */
    public static String timeFormat(Long date) {
        if (date != null) {
            return new SimpleDateFormat("MM月dd日 HH:mm").format(new Date(date));
        } else {
            return "";
        }
    }

}
