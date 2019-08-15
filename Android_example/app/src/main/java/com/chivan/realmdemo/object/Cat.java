package com.chivan.realmdemo.object;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * @author Chivan
 * @description:
 * @date : 2019-08-15 17:48
 */
public class Cat extends RealmObject {
    private String name;
    private int age;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    //    注意：name、age均是public权限，因此无需getter、setter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
