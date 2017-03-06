package com.wangxw.fragmentlazyloaddemo;

import android.util.Log;

/**
 * @author
 * @time 2017/3/6  15:22
 * @desc ${TODD}
 */
public class ZFragment extends BasefragmentO {


    @Override
    protected String getFragmentTextviewContent() {
        return "第一个fragment";
    }

    @Override
    protected void loadData() {
        Log.i("wxw", "AFragment可见,可以加载数据了");

    }
}
