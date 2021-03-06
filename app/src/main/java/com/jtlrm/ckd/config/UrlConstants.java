package com.jtlrm.ckd.config;

/**
 * date:    2017/9/25
 * description: 网络地址常量类
 */

public interface UrlConstants {

   // String BASE_URL = "http://192.168.1.132:8080";
//   String PROJECT_URL = BASE_URL + "/Rencai";

    String BASE_URL = "http://119.6.97.227:8077/";

    String PROJECT_URL = BASE_URL;


    String NEWS_LIST = "/news/app/getNewsListByCategoryId";

    /**
     * 登录
     */
    String LOGIN = "Token";

    /**
     * 热推新闻
     */
    String NEWS_LIST_SORT = "/news/app/getNewsListBySortKey";

    /**
     * 获取用户信息
     */
    String GET_USER_INFO = "api/services/ckd/user/QueryUserInfo";

    /**
     * 发送验证码
     */
    String SEND_MESSAGE = "api/services/app/user/CheckAndSendMobileCode";

    /**
     * 发送验证码
     */
    String SEND_FORGET_MESSAGE = "api/services/app/user/UpdateAndSendMobileCode";

    /**
     *
     */
    String QUERY_HOSPITAL  = "api/services/ckd/user/QueryHospitals";

    /**
     * 注册
     */
    String REGISTER  = "api/services/ckd/user/Register";

    /**
     * 重置密码
     */
    String REBUILD_PASSWORD  = "api/services/ckd/user/UpdateUserPwd";
}
