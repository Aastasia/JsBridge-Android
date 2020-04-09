package com.alvin.jsbridge_android;

import android.webkit.ValueCallback;
import android.webkit.WebView;

import org.json.JSONObject;

public class CallBackJsFunction {
    private String callBackJsFunctionName;
    private WebView webView;

    public CallBackJsFunction(WebView webView, String callBackJsFunctionName) {
        this.webView = webView;
        this.callBackJsFunctionName = callBackJsFunctionName;
    }

    // 模仿Javascript中的call函数调用
    public void call(JSONObject callBackFunctionParameters) {
        if(webView != null) {
            webView.post(() -> {
                webView.evaluateJavascript("javascript:" + this.callBackJsFunctionName + "(" + callBackFunctionParameters.toString() + ")", (String value) -> {
                    System.out.println("Javascript eval result:: " + value);
                });
            });

        }
    }
}
