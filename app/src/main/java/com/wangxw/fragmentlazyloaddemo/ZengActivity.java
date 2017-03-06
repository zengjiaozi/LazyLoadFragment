package com.wangxw.fragmentlazyloaddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.os.CancellationSignal;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangxw.fragmentlazyloaddemo.fragment.AFragment;
import com.wangxw.fragmentlazyloaddemo.fragment.BFragment;
import com.wangxw.fragmentlazyloaddemo.fragment.CFragment;

import java.util.ArrayList;


/**
 * @author
 * @time 2017/3/6  14:32
 * @desc ${TODD}
 */
public class ZengActivity extends AppCompatActivity implements View.OnClickListener {


    private TabLayout tablayout;
    private ViewPager viewpager;
    private LinearLayout mllLogs;
    private static int sOffScreenLimit = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zeng);
        initView();
        initDatas();
        initListener();

    }

    private void initListener() {

        findViewById(R.id.btn_viewpager_mode_default).setOnClickListener(this);
        findViewById(R.id.btn_viewpager_mode_load2page).setOnClickListener(this);
        findViewById(R.id.btn_clear_log).setOnClickListener(this);
    }


    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout1);
        viewpager = (ViewPager) findViewById(R.id.viewpager1);
        mllLogs = (LinearLayout) findViewById(R.id.ll_log_container);
    }

    //    加载数据的处理
    private void initDatas() {
//       加载fragment处理
        //填充数据
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ZFragment());
        fragmentList.add(new HFragment());

//        添加标题
        ArrayList<String> titleList = new ArrayList<>();
        for (int i = 0; i < fragmentList.size(); i++) {
            titleList.add("fragment" + i);
        }
        ZengAdapter adapter = new ZengAdapter(getSupportFragmentManager(), fragmentList, titleList);

        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        if (sOffScreenLimit > 1) {
            viewpager.setOffscreenPageLimit(sOffScreenLimit);
            Log.i("wxw", "偏移量>1...:" + sOffScreenLimit);
        } else {
            Log.i("wxw", "偏移量...:" + sOffScreenLimit);
        }


    }

    public void addLog(TextView logView) {
        mllLogs.addView(logView);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_viewpager_mode_default:       //默认模式
                sOffScreenLimit = 1;
                finish();
                startActivity(new Intent(this, ZengActivity.class));
                overridePendingTransition(0, 0);
                break;

            case R.id.btn_clear_log:                    //清空Log
                mllLogs.removeAllViews();
                break;
            default:
                break;
        }
    }
}
