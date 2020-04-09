package com.alvin.jsbridge_android;

import android.os.Build;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.InputStream;

public class MyWebViewClient extends WebViewClient {

    private Context context;
    public MyWebViewClient(Context context) {
        this.context = context;
    }

    private WebResourceResponse loadLocaFile(WebView view, String url) {
        System.out.println("url:: " + url);

        if(url.contains(".svg")) {
            try {
                // 步骤3:打开需要替换的资源(存放在assets文件夹里)
                // 在app/src/main下创建一个assets文件夹
                // assets文件夹里再创建一个images文件夹,放一个error.png的图片
                InputStream is = this.context.getAssets().open("image/axios.jpg");

                // 参数1：http请求里该图片的Content-Type,此处图片为image/png
                // 参数2：编码类型
                // 参数3：存放着替换资源的输入流（上面创建的那个）
                WebResourceResponse response = new WebResourceResponse("image/png", "utf-8", is);

                System.out.println(" low version API");

                return response;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.shouldInterceptRequest(view, url);
    }

    // API 21 以上用shouldInterceptRequest(WebView view, WebResourceRequest request)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

        return loadLocaFile(view, url);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

        return loadLocaFile(view, request.getUrl().toString());
    }
}
