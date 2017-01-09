package com.example.ll.eventbusstickylitepal.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ll.eventbusstickylitepal.R;
import com.example.ll.eventbusstickylitepal.base.BaseAcitivity;
import com.example.ll.eventbusstickylitepal.bean.LitepalBean;
import com.example.ll.eventbusstickylitepal.event.EventBean;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.List;
/*
* 逻辑：
*   点击发送按钮之后，从数据库获取数据的总条目，如果没有，默认发送1【练习查询数据库全部数据】；
*   发送数据，根据总条目，作为id查询数据库中的数据【练习根据id进行查询数据库】；
*   通过eventbus发送数据【练习eventbus的发送数据的方法】。
* */
public class MainActivity extends BaseAcitivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNormal = (Button) findViewById(R.id.btn_send_normal);
        Button btnSticky = (Button) findViewById(R.id.btn_send_sticky);

        //发送普通事件
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

        //发送粘性事件
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
        if (litepalBean == null){
            litepalBean = new LitepalBean();
            litepalBean.number = 1;
        }
        EventBean bean = new EventBean();
        bean.number = litepalBean.number;
        EventBus.getDefault().postSticky(bean);
        Toast.makeText(this, "发送粘性数据为："+bean.number, Toast.LENGTH_SHORT).show();
    }

    //发送普通消息
    private void sendNormal(int i) {
        EventBean ev = new EventBean();
        LitepalBean bean = DataSupport.find(LitepalBean.class, i);
        if (bean == null) {
            ev.number = 1;
        } else {
            //每次增加10
            ev.number = 10;
        }
        EventBus.getDefault().post(ev);
        Toast.makeText(this, "发送普通数据为："+ev.number, Toast.LENGTH_SHORT).show();
    }
}
