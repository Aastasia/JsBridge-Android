package com.alvin.jsbridge_android;

import android.webkit.WebView;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeMethodRegister {

    public static void showToast(WebView webView, JSONObject jsFunctionParamter, CallBackJsFunction callBackJsFunction) {
        if(callBackJsFunction != null) {
            try {
                System.out.println("callFunction parameter::: " + jsFunctionParamter);

                Thread.sleep(0);

                JSONObject result = new JSONObject();
                result.put("result", "js执行Native成功, 这是执行返回的结果");
                callBackJsFunction.call(result);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void showToastNoCallBack(WebView webView, JSONObject jsFunctionParamter, CallBackJsFunction callBackJsFunction) {
        if(callBackJsFunction != null) {
            try {
                System.out.println("callFunction parameter : showToastNoCallBack :: " + jsFunctionParamter);

                JSONObject result = new JSONObject();
                result.put("result", "showToastNoCallBack js执行Native成功, 这是执行返回的结果");
                callBackJsFunction.call(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
