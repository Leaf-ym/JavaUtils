package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengym
 * @version 1.0
 * @desc HelloWorld - Controller
 * @date 2022/5/12 18:14
 */
@RestController
@RequestMapping(value = "/test")
@Api(tags = "测试相关接口", value = "向世界问好")
public class HelloWorld {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "向世界问好", httpMethod = "GET", response = Object.class, notes = "helloWorld")
    public Object printHelloWorld() {
        String str = "HelloWorld";
        return str;
    }
}
