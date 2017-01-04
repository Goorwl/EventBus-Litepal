package com.example.ll.eventbusstickylitepal.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ll.eventbusstickylitepal.R;
import com.example.ll.eventbusstickylitepal.base.BaseAcitivity;
import com.example.ll.eventbusstickylitepal.bean.LitepalBean;
import com.example.ll.eventbusstickylitepal.event.EventBean;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends BaseAcitivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNormal = (Button) findViewById(R.id.btn_send_normal);
        Button btnSticky = (Button) findViewById(R.id.btn_send_sticky);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitepalBean litepalBean = new LitepalBean();
                List<LitepalBean> all = DataSupport.findAll(LitepalBean.class);
                litepalBean.number = all.size();
                litepalBean.save();
                sendNormal(all.size());
            }
        });

        btnSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<LitepalBean> all = DataSupport.findAll(LitepalBean.class);
                LitepalBean bean = new LitepalBean();
                bean.number = all.size();
                bean.save();
                sendSticky(all.size());
            }
        });
    }

    //发送粘性消息
    private void sendSticky(int size) {
        LitepalBean litepalBean = DataSupport.find(LitepalBean.class, size);
        EventBean bean = new EventBean();
        bean.number = litepalBean.number;
        EventBus.getDefault().postSticky(bean);
    }

    //发送普通消息
    private void sendNormal(int i) {
        EventBean ev = new EventBean();
        LitepalBean bean = DataSupport.find(LitepalBean.class, i);
        if (bean == null) {
            ev.number = 1;
        } else {
            ev.number = 20;
        }
        EventBus.getDefault().post(ev);
    }
}
