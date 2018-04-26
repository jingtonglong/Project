package com.base.sdk.base.model;


import java.io.Serializable;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/3/2/002.
 */

public class BaseModel implements Serializable {

    public RequestBody createBody(String object) {
       return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),object);
    }
}
