package com.chivan.realmdemo.object;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * @author Chivan
 * @description:
 * @date : 2019-08-15 11:14
 */
public class Dog extends RealmObject {
//    @Required //正确。String类型的属性默认值为null，因此可以声明为required
    private String name;

//    @Required //错误。int类型的默认值为0，声明为required毫无意义
    private int age;

    //注意：不能定义构造函数，否则编译不通过
    //public Dog(String name, int age) {
    //    this.name = name;
    //    this.age = age;
    //}

    //自动生成的标准 getter\setter方法
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
