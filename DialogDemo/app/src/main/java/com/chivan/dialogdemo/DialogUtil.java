package com.chivan.dialogdemo;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.flyco.dialog.widget.base.BaseDialog;

/**
 * @author Chivan
 * @description:
 * @date : 2019-08-21 21:39
 */
public class DialogUtil extends BaseDialog {

    private Context context;
    private View view;
    private ImageView iv_ad;
    private ImageView back;

    public DialogUtil(Context context) {
        super(context);
        this.context = context;
    }

    //该方法用来出来数据初始化代码
    @Override
    public View onCreateView() {
        widthScale(0.85f);
        //填充弹窗布局
        return view;
    }

    //该方法用来处理逻辑代码
    @Override
    public void setUiBeforShow() {

    }

    public DialogUtil setView(View view) {
        this.view = view;
        return this;
    }
}
