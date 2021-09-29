package com.springboot.notes.model;

import lombok.Data;

@Data
public class Person {

    String name;

    int age;

    String sex;

    Car car;

    Phone phone;

}
