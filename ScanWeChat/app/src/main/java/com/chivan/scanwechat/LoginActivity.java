package com.chivan.scanwechat;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.chivan.login_component.ILoginCallback;
import com.chivan.login_component.JsInterfaceImpl;
import com.chivan.login_component.model.UserInfoModel;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class LoginActivity extends AppCompatActivity implements ILoginCallback {

    public static final String TAG = MainActivity.class.getName();
    private static final String wechatAppId = "wx913f02182754ad93";
    private WebView webView;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initBroadcast();
        initData();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void initView() {
//        PackageInfo webViewPackageInfo = WebView.getCurrentWebViewPackage();
//        Log.d("MY_APP_TAG", "WebView version: " + webViewPackageInfo.versionName);
        setContentView(R.layout.activity_login);
        webView = findViewById(R.id.web_view);
        final WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.addJavascriptInterface(new JsInterfaceImpl(this, this), "jsInterface");
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.d(TAG, "loading url = " + url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                Log.d(TAG, "finished url = " + url);
//            }
//
//        });
    }

    private void initData() {
//        String url = "file:///android_asset/web/login.html"; // 注意：asset不能写成了assets
        final String url = "http://wd.chivan.cn/login.html";
        webView.loadUrl(url);
    }

    private void initBroadcast() {
        receiver = new LoginBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(JsInterfaceImpl.USER_AUTHORIZED_ACTION);
        filter.addAction(JsInterfaceImpl.USER_REFUSE_AUTHORIZATION_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    private class LoginBroadcast extends BroadcastReceiver {
        public LoginBroadcast() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (JsInterfaceImpl.USER_AUTHORIZED_ACTION.equals(intent.getAction())) {
                Log.d(TAG, "用户已授权");
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                LoginActivity.this.finish();
            } else if (JsInterfaceImpl.USER_REFUSE_AUTHORIZATION_ACTION.equals(intent.getAction())) {
                Log.d(TAG, "用户拒绝了授权");
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "未识别的Action");
                Toast.makeText(LoginActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLoginSuccess(UserInfoModel userInfoModel) {
        Log.d(TAG, "用户信息:" + userInfoModel.toString());
//        Intent intent = new Intent(this, ProfileActivity)
        //todo 存储用户信息
    }
}