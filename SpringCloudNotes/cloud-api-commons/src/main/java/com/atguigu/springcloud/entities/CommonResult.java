package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)  //链式
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T Data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
