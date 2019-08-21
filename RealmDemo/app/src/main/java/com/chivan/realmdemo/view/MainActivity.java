package com.chivan.realmdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chivan.realmdemo.R;
import com.chivan.realmdemo.realm.Cat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView(){
        setContentView(R.layout.activity_main);
    }
    private void initData(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Cat cat = realm.createObject(Cat.class);
                cat.setName("小黄猫");
                cat.setAge(3);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // 写入成功
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // 写入失败
            }
        });

        //批量更新对象属性值
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Cat> cats = realm.where(Cat.class).equalTo("name","小奶猫").findAll();//查询所有name = "小奶猫"的对象
                cats.setValue("age",3); //将符合条件到的所有Cat对象age属性值设置为3
            }
        });

        RealmResults<Cat> cats2 = realm.where(Cat.class)
                .equalTo("name","小奶猫")
                .and()
                .equalTo("age",2)
                .findAll();
        RealmResults<Cat> cats3 = realm.where(Cat.class)
                .in("name", new String[]{"小奶猫","小花猫","金毛"})
                .findAll();

        RealmResults<Cat> cats4 = realm.where(Cat.class)
                .like("name","?黄*") //能匹配出前两个
                .or()
                .like("name","*金?") //能匹配出后两个
                .findAll();
    }
}
