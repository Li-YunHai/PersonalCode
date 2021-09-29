package com.springboot.notes.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ConfigurationProperties自定义组件
 *      1、@ConfigurationProperties 需与 @Component或@EnableConfigurationProperties配合使用
 */
@Data
//@Component
@ConfigurationProperties(prefix = "my.car.config")
@EnableConfigurationProperties(value = {Car.class})
public class Car {

    private String brand;

    private Long price;
}
