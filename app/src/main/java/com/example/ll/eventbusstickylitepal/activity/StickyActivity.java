package com.example.ll.eventbusstickylitepal.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ll.eventbusstickylitepal.R;
import com.example.ll.eventbusstickylitepal.base.BaseAcitivity;
import com.example.ll.eventbusstickylitepal.event.EventBean;
import com.example.ll.eventbusstickylitepal.utils.SPutils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class StickyActivity extends BaseAcitivity {
    private static final String TAG = "StickyActivity";

    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        mTvShow = (TextView) findViewById(R.id.tv_sticky_show);
        /*
        * 执行顺序:0,3,1,2
        * */
        Log.e(TAG, "onCreate:  0 ");
        //注册eventbus事件
        EventBus.getDefault().register(StickyActivity.this);
        Log.e(TAG, "onCreate:  1 ");
        String string = SPutils.getString(StickyActivity.this, "temp", "0");
        mTvShow.setText(string);
        Log.e(TAG, "onCreate:  2 ");
    }

    //注解必须写，注意设置sticky属性
    @Subscribe(sticky = true)
    public void showSticky(EventBean bean){
        String s = mTvShow.getText().toString();
        int res = Integer.valueOf(s) + bean.number;
        SPutils.putString(StickyActivity.this,"temp",res+"");
        Log.e(TAG, "showSticky: 3 ");
    }

    @Override
    protected void onDestroy() {
        //移除全部黏性事件
        EventBus.getDefault().removeAllStickyEvents();
//        移除单个粘性事件
//        EventBus.getDefault().removeStickyEvent(StickyActivity.class);
        //反注册eventbus事件
        EventBus.getDefault().unregister(StickyActivity.this);
        super.onDestroy();
    }
}
