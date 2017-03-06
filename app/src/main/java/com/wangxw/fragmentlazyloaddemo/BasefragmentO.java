package com.wangxw.fragmentlazyloaddemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author
 * @time 2017/3/6  15:03
 * @desc ${TODD}
 */
public abstract class BasefragmentO extends Fragment {

    private ZengActivity activity;
    private String content;
    private boolean isViewCreated;
    private boolean isUIVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        控件初始化可见的时候
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;
        printLog(content + "页面销毁了");
    }

    //    懒加载  加载数据
    protected void lazyLoad() {
     //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,
     // 必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData();  //开始加载数据 加载完毕后  恢复标记防止重新加载
            isViewCreated = false;
            isUIVisible = false;
            printLog(content + "可见加载，加载了数据");
        }

    }


    //加载控件的操作
    private void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.textview);
        content = getFragmentTextviewContent();
        textView.setText(content);

        activity = (ZengActivity) getActivity();

        printLog(content + "加载了。。。。");

    }

    private void printLog(String logStr) {
        TextView logView = new TextView(getContext());
        logView.setText(logStr);
        activity.addLog(logView);
    }


    /**
     * Fragment中TextView显示的内容
     */
    protected abstract String getFragmentTextviewContent();


    //加载数据的操作
    protected abstract void loadData();
}
