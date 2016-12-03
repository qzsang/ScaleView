package com.qzsang.scaleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qzsang.scaleviewlibrary.ScaleImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll_parent = (LinearLayout) findViewById(R.id.ll_parent);


        //用代码的方式添加一个宽高比为1:2的ImageView  默认充满屏幕  如有特殊需要可以自行改动

        ScaleImageView scaleImageView = new ScaleImageView(this,5,8);
        scaleImageView.setImageResource(R.drawable.test_img);
        ll_parent.addView(scaleImageView);
        TextView textView = new TextView(this);
        textView.setText("用代码的方式添加一个宽高比为5:8的ImageView");
        ll_parent.addView(textView);
    }
}
