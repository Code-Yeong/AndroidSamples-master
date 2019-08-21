package com.chivan.scanwechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";

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
        webView.addJavascriptInterface(new MyJsInterface(),"jsInterface");
    }

    public class MyJsInterface{

        @JavascriptInterface
        public void loginCallback(String data){
            Log.i(TAG, "data = " + data);
        }
    }

    private void initData() {
        String url = "file:///android_asset/web/login.html"; // 注意：asset不能写成了assets
        webView.loadUrl(url);
    }
}
