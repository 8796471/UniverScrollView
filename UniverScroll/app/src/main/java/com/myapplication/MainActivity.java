package com.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements UniversalScrollCall {
    UniversalScrollView scrollView;
    TextView t1,t2;
    LinearLayout ly_title;
    ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.transparent);//通知栏所需颜色
        }
        setContentView(R.layout.activity_main);
        scrollView = (UniversalScrollView) findViewById(R.id.scrollView);
        t1 = (TextView) findViewById(R.id.textview);
        t2 = (TextView) findViewById(R.id.textview2);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        ly_title = (LinearLayout) findViewById(R.id.ly_title);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        scrollView.setupTitleView(ly_title);
        scrollView.setupByWhichView(iv);
        scrollView.setZoomView(iv);
        Glide.with(this).load("http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490772659550&di=e30fce363719696373e3f1f814bda007&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F60%2F27%2F72h58PICgzY_1024.jpg").into(iv);
        scrollView.setCallback(this);
    }
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    @Override
    public void stopScroll(boolean isStop) {
        if(isStop){
            t1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.GONE);
            iv_back.setBackgroundResource(R.color.colorAccent);
        }else {
            t2.setVisibility(View.VISIBLE);
            t1.setVisibility(View.GONE);
            iv_back.setBackgroundResource(R.color.colorPrimary);
        }
    }
}
