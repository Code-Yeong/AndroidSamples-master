package com.chivan.login_component;

import java.util.Map;

/**
 * @author Chivan
 * @description: 扫码登录状态回调
 * @date : 2019-09-03 17:06
 */
public interface IJsInterface {

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

}
