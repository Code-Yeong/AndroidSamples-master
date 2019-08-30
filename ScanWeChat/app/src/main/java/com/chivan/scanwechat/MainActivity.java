package com.chivan.scanwechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";
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
        webView.addJavascriptInterface(new MyJsInterface(), "jsInterface");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("URL", "u = " + url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {


                super.onPageFinished(view, url);
                Log.d("URL", "url = " + url);
            }

        });
    }

    public class MyJsInterface {

        @JavascriptInterface
        public void loginCallback(final String data) {
            webView.post(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "data = " + data + ", url=" + webView.getUrl());
                }
            });

        }
    }

    private void initData() {
        String url = "file:///android_asset/web/login.html"; // 注意：asset不能写成了assets
        webView.loadUrl(url);

    }
}
