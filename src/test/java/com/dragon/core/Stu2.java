package com.dragon.core;

/**
 * @ClassName: Stu
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/1 21:46
 * @Version V1.0
 */
public class Stu2{

    private String uid;
    private String name;
    private int age;
    private Stu2 stu2;

    public Stu2(){}


    public Stu2(String uid, String name, int age){
        this.uid = uid;
        this.name = name;
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public Stu2 getStu2() {
        return stu2;
    }

    public void setStu2(Stu2 stu2) {
        this.stu2 = stu2;
    }

    @Override
    public String toString() {
        return "Stu2{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", stu2=" + stu2 +
                '}';
    }
}
