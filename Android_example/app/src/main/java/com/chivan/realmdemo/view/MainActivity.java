package com.chivan.realmdemo.view;

import android.os.Bundle;
import android.util.Log;

import com.chivan.realmdemo.R;
import com.chivan.realmdemo.object.Cat;
import com.chivan.realmdemo.object.Dog;

import io.realm.Realm;
import io.realm.RealmObject;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {
        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        Dog dog = realm.createObject(Dog.class);
//        dog.setAge(2);
//        dog.setName("金毛");
//        realm.commitTransaction();

        Cat cat = new Cat("小奶猫");
        cat.setAge(5);
        realm.beginTransaction();
        Cat catRealm = realm.copyToRealm(cat);
        realm.commitTransaction();

//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Dog dog1 = realm.createObject(Dog.class);
//                dog1.setName("牧羊犬");
//                dog1.setAge(2);
//            }
//        });
//
//        Dog dog2 = realm.where(Dog.class).equalTo("age",2).findFirst();
//        Log.i("Age","dog age is:"+dog2.getAge()); // 输出结果: 2
//
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Dog dog3 = realm.where(Dog.class).equalTo("age",2).findFirst();
//                dog3.setAge(4);
//            }
//        });
//
//        Log.i("Age","Now dog age is:"+dog2.getAge()); // 输出结果: 4（已经不是2了哦）
//
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Cat cat = realm.createObject(Cat.class);
//                cat.name = "波斯猫";
//                cat.age = 2;
//
//            }
//        });
    }
}
