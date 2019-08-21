package com.chivan.realmdemo.realm;

import io.realm.RealmObject;

/**
 * @author Chivan
 * @description:
 * @date : 2019-08-19 21:02
 */
public class Cat extends RealmObject {
    private String name;
    private int age;

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
