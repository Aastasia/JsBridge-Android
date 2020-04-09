package com.alvin.jsbridge_android;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class JsBridgeInterface {

    private WebView webView;
    public JsBridgeInterface(WebView webView) {
        this.webView = webView;
    }

    private static HashMap<String, Method> registerNativeFunction(Class<?> clazz) {
        HashMap<String, Method> registerMethods = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method: methods) {
            boolean isIgnoreMethod = method.getModifiers()!=(Modifier.PUBLIC | Modifier.STATIC);
            if(!isIgnoreMethod) {
                Class[] parameters = method.getParameterTypes();
                if(parameters != null && parameters.length == 3) {
                    // 将函数注册进map当中等待调用
                    registerMethods.put(method.getName(), method);
                }
            }
        }
        return registerMethods;
    }


    @JavascriptInterface
    public String callHandler(String methodName, String args) {

        try {
            HashMap<String, Method> registerMethods = registerNativeFunction(NativeMethodRegister.class);

            JSONObject arguments = new JSONObject(args);
            // 传入的参数
            JSONObject data = new JSONObject(arguments.getString("data"));
            // JS回调函数名
            String callbackMethodName = arguments.getString("callback");

            if(registerMethods.containsKey(methodName)) {
                Method method = registerMethods.get(methodName);
                method.invoke(null, webView, data, new CallBackJsFunction(webView, callbackMethodName));
            }

        } catch (JSONException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
