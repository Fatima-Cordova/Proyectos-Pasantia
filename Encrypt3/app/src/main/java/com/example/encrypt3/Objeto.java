package com.example.encrypt3;

import java.util.ArrayList;

public class Objeto  {
    private String name = "";
    private String address = "";
    private String gender = "";
    private String age = "";

    public Objeto() {
    }

    public Objeto(String name, String address, String gender, String age) {
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.age = age;
    }




    @Override
    public String toString() {
        return "Objeto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
