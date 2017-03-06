package com.wangxw.fragmentlazyloaddemo;

import android.util.Log;

/**
 * @author
 * @time 2017/3/6  15:23
 * @desc ${TODD}
 */
public class HFragment extends BasefragmentO {
    @Override
    protected String getFragmentTextviewContent() {
        return "第二个fragment可见";
    }

    @Override
    protected void loadData() {
        Log.i("wxw","第二个fragment可见,可以加载数据了");
    }
}
