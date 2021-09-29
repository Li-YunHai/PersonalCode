package com.learn.notes.springboot;

import com.learn.notes.spring.dependencies.B;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "my.car.config")
public class Car {

    private String brand;

    private Long price;

}
