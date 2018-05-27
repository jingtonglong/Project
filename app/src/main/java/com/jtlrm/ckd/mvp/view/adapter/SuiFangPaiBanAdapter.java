package com.jtlrm.ckd.mvp.view.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.entity.NewsEntity;

public class SuiFangPaiBanAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {

    MenuListener menuListener;
    public SuiFangPaiBanAdapter() {
        super(R.layout.suifang_paiban_item);
    }


    public void setMenuListener(MenuListener listener) {
        this.menuListener = listener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, NewsEntity item) {
        helper.getView(R.id.change_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuListener != null)
                menuListener.click(helper.getLayoutPosition());
            }
        });
    }

    public interface MenuListener{
        void click(int position);
    }
}
