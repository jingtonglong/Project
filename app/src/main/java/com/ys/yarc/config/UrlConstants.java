package com.ys.yarc.config;

/**
 * date:    2017/9/25
 * description: 网络地址常量类
 */

public interface UrlConstants {

   // String BASE_URL = "http://192.168.1.132:8080";
//   String PROJECT_URL = BASE_URL + "/Rencai";

    String BASE_URL = "http://192.168.1.74:8080";

    String PROJECT_URL = BASE_URL;


    String NEWS_LIST = "/news/app/getNewsListByCategoryId";

    /**
     * 登录
     */
    String LOGIN = "/user/app/login";

    /**
     * 注册
     */
    String REGISTER = "/user/app/register";

    /**
     * 热推新闻
     */
    String NEWS_LIST_SORT = "/news/app/getNewsListBySortKey";

    /**
     * 获取用户信息
     */
    String GET_USER_INFO = "/user/app/user/getUserInfo";
}
