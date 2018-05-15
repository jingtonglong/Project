package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.mvp.view.adapter.NewsAdapter;
import com.jtlrm.ckd.mvp.view.adapter.PictureBottomAdapter;
import com.jtlrm.ckd.mvp.view.adapter.PictureTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class YinShiTuPianActivity extends BaseActivity {

    @BindView(R.id.list_top)
    RecyclerView topView;
    @BindView(R.id.list_bottom)
    RecyclerView bottomView;
    PictureTopAdapter topAdapter;
    PictureBottomAdapter bottomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_yin_shi_tu_pian);

    }

    @Override
    protected void initView() {
        //设置布局管理器
        LinearLayoutManager topLayoutManager = new LinearLayoutManager(this);
        topLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager bottomLayoutManager = new LinearLayoutManager(this);
        bottomLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topAdapter = new PictureTopAdapter();
        topView.setLayoutManager(topLayoutManager);
        topView.setAdapter(topAdapter);
        bottomAdapter = new PictureBottomAdapter();
        bottomView.setLayoutManager(bottomLayoutManager);
        bottomView.setAdapter(bottomAdapter);
        List<String> images = new ArrayList<>();
        images.add("http://img2.imgtn.bdimg.com/it/u=3588772980,2454248748&fm=27&gp=0.jpg");
        images.add("http://img07.tooopen.com/images/20170818/tooopen_sy_220999936848.jpg");
        images.add("http://img.zcool.cn/community/0191f1589c3469a8012060c83cebf3.jpg@1280w_1l_2o_100sh.png");
        images.add("http://img3.redocn.com/tupian/20160108/lvsehuahuizhizhangfanyexiaoguobeijingsucai_5728265.jpg");
        images.add("http://img.zcool.cn/community/0166e959ac1386a801211d25c63563.jpg@1280w_1l_2o_100sh.jpg");
        images.add("http://pic.58pic.com/58pic/14/64/56/25h58PIC3eG_1024.jpg");
        images.add("http://img.zcool.cn/community/019a97554a26080000009e321be070.jpg@1280w_1l_2o_100sh.jpg");
        images.add("http://img.zcool.cn/community/018e4855496dae0000019ae981c90c.jpg@3000w_1l_2o_100sh.jpg");
        topAdapter.addData(images);
        bottomAdapter.addData(images);

        topAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                bottomView.scrollToPosition(position);
            }
        });
        bottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                topView.scrollToPosition(position);
            }
        });

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }


    public void goBack(View view) {
        finish();
    }
}
