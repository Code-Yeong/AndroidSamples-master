package com.chivan.pageindicator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ViewPagerIndicator.IEventCallback {

    private ViewPagerIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView(){
        indicator = findViewById(R.id.tab);
    }

    private void initData(){
        String[] texts = {"总分","本月","本周"};

        indicator.setParams(10,5,texts, this);
    }

    @Override
    public void onItemSelected(int position) {
        Log.d("MainActivity", "Selectend:" + position);
    }
}
