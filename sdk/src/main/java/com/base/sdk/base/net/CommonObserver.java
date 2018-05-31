package com.base.sdk.base.net;


import com.base.sdk.base.entity.RequestResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/1/001.
 */

public abstract class CommonObserver<T> implements Observer<RequestResult<T>> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof Exception) {
            //访问获得对应的Exception
            ExceptionHandler.ResponseThrowable responseThrowable = ExceptionHandler.handleException(e);
            onError(responseThrowable.code, responseThrowable.message);
        } else {
            //将Throwable 和 未知错误的status code返回
            ExceptionHandler.ResponseThrowable responseThrowable = new ExceptionHandler.ResponseThrowable(e, ExceptionHandler.ERROR.UNKNOWN);
            onError(responseThrowable.code, responseThrowable.message);
        }
    }

    @Override
    public void onNext(RequestResult<T> tRequestResult) {
        try {
            if (tRequestResult == null) {
                onError(0, "返回数据为空");
            } else {
                if (tRequestResult.isSuccess() == true ) {
                    onResult(tRequestResult.getResult());
                } else {
                    onError(tRequestResult.getError().getCode(), tRequestResult.getError().getMessage());
                }
            }
        }catch (Exception e) {
            onError(0, "数据解析异常");
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onError(int errType, String errMessage);

    public abstract void onResult(T data);
}
