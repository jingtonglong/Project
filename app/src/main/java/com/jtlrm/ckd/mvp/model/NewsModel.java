package com.jtlrm.ckd.mvp.model;

import com.base.sdk.base.entity.RequestResult;
import com.base.sdk.base.model.BaseModel;
import com.base.sdk.base.net.LifeCycleEvent;
import com.jtlrm.ckd.entity.ListEntity;
import com.jtlrm.ckd.entity.NewsEntity;
import com.jtlrm.ckd.entity.ResultData;
import com.jtlrm.ckd.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Administrator on 2018/3/21/021.
 * 新闻数据模型
 */

public class NewsModel extends BaseModel {

    int categoryId = -1;
    public NewsModel(int categoryId) {
        this.categoryId = categoryId;
    }

    public NewsModel() {
    }

    public void getNews(int num, int size, Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        if (categoryId == -1) {
            // 热推新闻
            getNewsListBySort(num, size, "hits", 1, observer, lifecycleSubject);
        } else {
            getNewsListByCatogary(num, size, categoryId, observer, lifecycleSubject);
        }
    }

    /**
     * 一般新闻
     *
     * @param num
     * @param size
     * @param categoryId
     * @param observer
     * @param lifecycleSubject
     */
    public void getNewsListByCatogary(int num, int size, int categoryId, Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        Observable<RequestResult<ResultData<ListEntity<NewsEntity>>>> observable = RetrofitUtil.getService().getNewsByCategory(num, size, categoryId);
        RetrofitUtil.composeToSubscribe(observable, observer, lifecycleSubject);
    }

    /**
     * 热推新闻
     *
     * @param num
     * @param size
     * @param sortKey          排序字段
     * @param sortDirection    正反正-1  逆-0
     * @param observer
     * @param lifecycleSubject
     */
    public void getNewsListBySort(int num, int size, String sortKey, int sortDirection, Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        Observable<RequestResult<ResultData<ListEntity<NewsEntity>>>> observable = RetrofitUtil.getService().getNewsBySort(num, size, sortKey, sortDirection);
        RetrofitUtil.composeToSubscribe(observable, observer, lifecycleSubject);
    }
}
