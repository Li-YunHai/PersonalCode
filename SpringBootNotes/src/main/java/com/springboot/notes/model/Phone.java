package com.springboot.notes.model;

import lombok.Data;

@Data
public class Phone {

    private String brand;

    private Long price;

    private Person person;
}
