package com.example.ll.eventbusstickylitepal.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ll.eventbusstickylitepal.R;
import com.example.ll.eventbusstickylitepal.base.BaseAcitivity;
import com.example.ll.eventbusstickylitepal.event.EventBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class StickyActivity extends BaseAcitivity {

    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        mTvShow = (TextView) findViewById(R.id.tv_sticky_show);
        EventBus.getDefault().register(StickyActivity.this);
    }

    @Subscribe(sticky = true)
    public void showSticky(EventBean bean){
        String s = mTvShow.getText().toString();
        int res = Integer.valueOf(s) + bean.number;
        mTvShow.setText(res + "");
    }

    @Override
    protected void onDestroy() {
        //移除全部黏性事件
        EventBus.getDefault().removeAllStickyEvents();
        //移除单个粘性事件
//        EventBus.getDefault().removeStickyEvent(StickyActivity.class);
        EventBus.getDefault().unregister(StickyActivity.this);
        super.onDestroy();
    }
}
