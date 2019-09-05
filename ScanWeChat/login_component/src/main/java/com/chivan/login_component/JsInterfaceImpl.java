package com.chivan.login_component;

import android.app.Activity;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.chivan.login_component.model.UserInfoModel;
import com.google.gson.Gson;

/**
 * @author Chivan
 * @description:
 * @date : 2019-09-03 18:01
 */
public class JsInterfaceImpl implements IJsInterface {

    public static final String TAG = JsInterfaceImpl.class.getName();
    public static final String USER_AUTHORIZED_ACTION = "userAuthorizedAction";
    public static final String USER_REFUSE_AUTHORIZATION_ACTION = "userRefuseAuthorizationAction";


    private Activity mContext;
    private ILoginCallback loginCallback;
    private Gson gson = new Gson();

    public JsInterfaceImpl(Activity context, ILoginCallback loginCallback) {
        this.mContext = context;
        this.loginCallback = loginCallback;
    }

    @JavascriptInterface
    @Override
    public void onAuthorized(final String code, final String state) {
        Log.d(TAG, "用户已授权 code = " + code + ", state:" + state);
//        Intent intent = new Intent(USER_AUTHORIZED_ACTION);
//        intent.putExtra("code", code);
//        intent.putExtra("state", state);
//        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        UserInfoModel userInfoModel = gson.fromJson(fakeJson, UserInfoModel.class);
        if (loginCallback != null) {
            loginCallback.onLoginSuccess(userInfoModel);
        }
    }

    @JavascriptInterface
    @Override
    public void onDenied(final String state) {
        Log.i(TAG, "用户拒绝授权: state:" + state);
//        Intent intent = new Intent(USER_REFUSE_AUTHORIZATION_ACTION);
//        intent.putExtra("state", state);
//        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

    private String fakeJson =
            "{" +
                    "    \"openid\":\"0123456789\"," +
                    "    \"nickname\":\"Superman\"," +
                    "    \"sex\":1," +
                    "    \"province\":\"北京市\"," +
                    "    \"city\":\"北京市海淀区\"," +
                    "    \"country\":\"中国\"," +
                    "    \"headimgurl\": \"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0\"," +
                    "    \"privilege\":[" +
                    "        \"PRIVILEGE1\"," +
                    "        \"PRIVILEGE2\"" +
                    "    ]," +
                    "    \"unionid\": \" o6_bmasdasdsad6_2sgVt7hMZOPfL\"" +
                    "}";
}
