package com.example.ll.eventbusstickylitepal.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.ll.eventbusstickylitepal.R;
import com.example.ll.eventbusstickylitepal.activity.MainActivity;
import com.example.ll.eventbusstickylitepal.activity.NormalActivity;
import com.example.ll.eventbusstickylitepal.activity.StickyActivity;

/**
 * Description : 抽取基类
 * Copyright   : (c) 2016
 * Author      : Goorwl
 * Email       : goorwl@163.com
 * Date        : 2017/1/4 18:56
 */

public class BaseAcitivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutId) {
        View baseView = getLayoutInflater().inflate(R.layout.base_activity, null);
        Button btnEvent = (Button) baseView.findViewById(R.id.event);
        Button btnNormal = (Button) baseView.findViewById(R.id.normal);
        Button btnSticky = (Button) baseView.findViewById(R.id.sticky);

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseAcitivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseAcitivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });

        btnSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseAcitivity.this, StickyActivity.class);
                startActivity(intent);
            }
        });

        FrameLayout fl = (FrameLayout) baseView.findViewById(R.id.fl_view);
        View inflate = View.inflate(BaseAcitivity.this, layoutId, null);
        fl.addView(inflate);
        super.setContentView(baseView);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.from_right_in, R.anim.to_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_left_in, R.anim.to_right_out);
    }
}
