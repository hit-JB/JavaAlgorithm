package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.*;
public class Student extends Person implements Cloneable  {
    private int score;
    public Student(int age,int score,String gender){
        super(age, gender);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public static void main(String[] args) throws  ClassNotFoundException {
        try {
            Student a = new Student(23,99,"male");
            Class cl = a.getClass();
            Class superCl = cl.getSuperclass();
            Method m1 = cl.getMethod("getScore");
            Method m2 = superCl.getMethod("getAge");
            System.out.println((Integer) m1.invoke(a));
            System.out.println(Math.pow(2,2));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
class Person {
    private int age;
    private String gender;
    public Person(int age, String gender) {
        this.age = age;
        this.gender = gender;
    }
    public Person()
    {
        age = 23;
        gender = "male";
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
