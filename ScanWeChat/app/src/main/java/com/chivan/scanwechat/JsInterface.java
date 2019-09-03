package com.chivan.scanwechat;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.util.Map;

/**
 * @author Chivan
 * @description:
 * @date : 2019-09-03 18:01
 */
public class JsInterface implements IScanCallback {

    public static final String TAG = JsInterface.class.getName();

    private Activity mContext;

    public JsInterface(Activity context) {
        this.mContext = context;
    }

    @JavascriptInterface
    @Override
    public void onReady() {

    }

    @JavascriptInterface
    @Override
    public void onSuccess(Map<String, Object> data) {

    }

    @JavascriptInterface
    @Override
    public void onFailed(Map<String, Object> data) {

    }

    @JavascriptInterface
    @Override
    public void onAuthorized(final String code, final String state) {
        Log.i(TAG, "code = " + code + ", state:" + state);
        mContext.finish();
    }

    @JavascriptInterface
    @Override
    public void onDenied(final String state) {
        Log.i(TAG, "用户拒绝授权: state:" + state);
    }

    @JavascriptInterface
    @Override
    public void onClose() {

    }
}
