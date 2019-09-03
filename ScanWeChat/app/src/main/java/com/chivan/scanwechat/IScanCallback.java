package com.chivan.scanwechat;

import java.util.Map;

/**
 * @author Chivan
 * @description: 扫码登录状态回调
 * @date : 2019-09-03 17:06
 */
public interface IScanCallback {

    /**
     * 二维码页面初始化完毕
     */
    void onReady();

    /**
     * 扫码成功
     *
     * @param data js端参数
     */
    void onSuccess(Map<String, Object> data);

    /**
     * 扫码失败
     *
     * @param data js端参数
     */
    void onFailed(Map<String, Object> data);

    /**
     * 用户已授权
     *
     * @param code 微信授权之后的code
     * @param state 微信授权之后的state
     */
    void onAuthorized(String code, String state);

    /**
     * 用户已拒绝授权
     *
     * @param state 微信客户端拒绝授权后的state，与 [@link onAuthorized] 的state参数是同一个
     */
    void onDenied(String state);

    /**
     * 页面关闭
     */
    void onClose();
}
