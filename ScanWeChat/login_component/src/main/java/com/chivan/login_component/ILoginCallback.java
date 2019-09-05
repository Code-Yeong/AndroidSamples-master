package com.chivan.login_component;

import com.chivan.login_component.model.UserInfoModel;

/**
 * @author Chivan
 * @description:
 * @date : 2019-09-04 15:52
 */
public interface ILoginCallback {
    void onLoginSuccess(UserInfoModel userInfoModel);
//    void onLoginFailed();
}
