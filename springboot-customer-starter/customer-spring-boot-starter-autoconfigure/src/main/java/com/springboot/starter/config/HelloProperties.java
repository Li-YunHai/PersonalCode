package com.springboot.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("customer.hello")
public class HelloProperties {

    private String prefix;

    private String suffix;

}
