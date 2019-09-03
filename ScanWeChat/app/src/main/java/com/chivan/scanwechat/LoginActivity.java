package com.chivan.scanwechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();
    private static final String wechatAppId = "wx913f02182754ad93";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.web_view);
        final WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.addJavascriptInterface(new JsInterface(this), "jsInterface");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "loading url = " + url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG, "finished url = " + url);
            }

        });
    }

    private void initData() {
//        String url = "file:///android_asset/web/login.html"; // 注意：asset不能写成了assets
        final String url = "http://wd.chivan.cn/login.html";
        webView.loadUrl(url);
    }
}
