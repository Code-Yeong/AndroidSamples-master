package com.chivan.dialogdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

/**
 * @author Chivan
 * @description:
 * @date : 2019-08-21 19:58
 */
public class MyDialog extends AppCompatDialogFragment {
    private Context mContext;
    private String mTitle;
    private String message;
    private String mPositiveText;
    private String mNegativeText;
    private View view;
    private int mBgGround;
    private boolean mBackCancel = true;//默认点击返回键关闭dialog
    private boolean mTouchOutsideCancel = true;//默认点击dialog外面屏幕，dialog关闭
    private DialogInterface.OnClickListener mPositiveOnClickListener;
    private DialogInterface.OnClickListener mNegativeOnClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AlertDialog);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        //设置标题
        if (!TextUtils.isEmpty(mTitle)) {
            builder.setTitle(mTitle);
        }
        //设置提示语
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        if (view != null) {
            builder.setView(view);
        }
        builder.setTitle(mTitle)
                .setMessage(message)//getResources().getString(R.string.hello_world)
                //设置返回键
                .setOnKeyListener(new KeyBackListener());
        //设置确定按钮
        if (null != mPositiveText) {
            builder.setPositiveButton(mPositiveText, mPositiveOnClickListener);
        }
        //设置否定按钮
        if (null != mNegativeText) {
            builder.setNegativeButton(mNegativeText, mNegativeOnClickListener);
        }
        AlertDialog dialog = builder.create();
        //设置屏幕外部点击是否可以取消
        dialog.setCanceledOnTouchOutside(mTouchOutsideCancel);
        //设置dialog背景色
        if (mBgGround != 0) {
            dialog.getWindow().setBackgroundDrawable(mContext.getResources().getDrawable(mBgGround));
        }
        return dialog;
    }

    /**
     * 监听返回键的类
     **/
    class KeyBackListener implements DialogInterface.OnKeyListener {
        @Override
        public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                return !mBackCancel;
            }
            return false;
        }
    }

    /**
     * 设置标题
     **/
    public MyDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    /**
     * 设置信息
     **/
    public MyDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置点击返回键是否关闭dialog
     **/
    public MyDialog setCancel(boolean canDismiss) {
        this.mBackCancel = canDismiss;
        return this;
    }

    /**
     * 设置点击屏幕外面是否关闭dialog
     **/
    public MyDialog setCancelOnTouchOutside(boolean canDismiss) {
        this.mTouchOutsideCancel = canDismiss;
        return this;
    }

    /**
     * 设置dialog背景
     **/
    public MyDialog setBackGround(int rid) {
        mBgGround = rid;
        return this;
    }

    /**
     * 设置Positive点击事件
     **/
    public MyDialog setPositiveListener(String positiveText, DialogInterface.OnClickListener onClickListener) {
        this.mPositiveText = positiveText;
        this.mPositiveOnClickListener = onClickListener;
        return this;
    }

    /**
     * 设置Negative点击事件
     **/
    public MyDialog setNegativeListener(String negativeText, DialogInterface.OnClickListener onClickListener) {
        this.mNegativeText = negativeText;
        this.mNegativeOnClickListener = onClickListener;
        return this;
    }

    /***
     * 显示dialog，需要传 fragmentManager=getSupportFragmentManager()
     * @param fragmentManager
     */
    public void showDialog(FragmentManager fragmentManager) {
        try {
            String className = this.getClass().getSimpleName();
            this.show(fragmentManager, className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MyDialog setView(View view) {
        this.view = view;
        return this;
    }
}
