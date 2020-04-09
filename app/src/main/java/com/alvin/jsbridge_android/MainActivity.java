package com.alvin.jsbridge_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);

        webView.setWebContentsDebuggingEnabled(true);

        webView.setWebViewClient(new MyWebViewClient(getApplicationContext()));

        webView.setWebChromeClient(new WebChromeClient());


        WebSettings webSettings = webView.getSettings();

        // 开启App缓存
        webSettings.setAppCacheEnabled(true);

        // 开启dom缓存
        webSettings.setDomStorageEnabled(true);

        // 开启数据库存储机制
        webSettings.setDatabaseEnabled(true);

        // 打开支持JS之后，自动支持IndexDb
        webSettings.setJavaScriptEnabled(true);

        // 设置webView缓存模式
        // webSettings.setCacheMode(webSettings.LOAD_DEFAULT);

        webView.addJavascriptInterface(new JsBridgeInterface(webView), "JavaScriptBridge");

         webView.loadUrl("file:///android_asset/index.html");
//        webView.loadUrl("http://192.168.1.103:3000");

    }

    // 通过拦截 onKeyDown 事件实现网页回退
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
