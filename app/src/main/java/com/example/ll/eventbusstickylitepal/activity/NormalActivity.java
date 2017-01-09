package com.example.ll.eventbusstickylitepal.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ll.eventbusstickylitepal.R;
import com.example.ll.eventbusstickylitepal.base.BaseAcitivity;
import com.example.ll.eventbusstickylitepal.event.EventBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class NormalActivity extends BaseAcitivity {

    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        mTvShow = (TextView) findViewById(R.id.tv_normal_show);
        EventBus.getDefault().register(NormalActivity.this);
    }

    //注解必须写
    @Subscribe()
    public void showNormal(EventBean bean) {
        String s = mTvShow.getText().toString();
        int res = Integer.valueOf(s) + bean.number;
        mTvShow.setText(res + "");
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(NormalActivity.this);
        super.onDestroy();
    }
}
