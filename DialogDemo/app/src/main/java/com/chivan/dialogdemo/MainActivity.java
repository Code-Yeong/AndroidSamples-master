package com.chivan.dialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
//        View content = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
//        TextView btn = content.findViewById(R.id.buy_key);
//        final MyDialog dialog = new MyDialog()
//                .setView(content)
//                //禁用返回键
//                .setCancel(false)
//                //禁止屏幕外部点击消失
//                .setCancelOnTouchOutside(true)
//                //设置背景色
//                .setBackGround(R.drawable.dialo_bg);
//        //显示dialog
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.showDialog(getSupportFragmentManager());
        View content = LayoutInflater.from(this).inflate(R.layout.dialog_layout2, null);
        TextView btn = content.findViewById(R.id.buy_key);
        final DialogUtil dialog = new DialogUtil(this).setView(content);
        dialog.onCreateView();
        //点击空白区域能不能退出
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
                btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
