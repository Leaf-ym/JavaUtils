package com.example.demo.controller;

import com.example.demo.service.BaseService;
import com.example.demo.service.UserRegisterService;
import com.ncepu.model.UserRegisterModel;
import com.sun.javafx.collections.MappingChange;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc HelloWorld - Controller
 * @date 2022/5/12 18:14
 */
@RestController
@RequestMapping(value = "/sql")
@Api(tags = "测试SQL接口", value = "向世界问好")
public class TestSQL {

    @Resource
    private UserRegisterService userRegisterService;

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ApiOperation(value = "向世界问好", httpMethod = "GET", response = Object.class, notes = "helloWorld")
    public Object insert() {
        UserRegisterModel model = UserRegisterModel.builder()
                .id("111")
                .userId("17325302081")
                .password("123456")
                .build();
        Integer cnt = this.userRegisterService.insert(model);
        return cnt;
    }

    @RequestMapping(value = "/getObject", method = RequestMethod.GET)
    @ApiOperation(value = "向世界问好", httpMethod = "GET", response = Object.class, notes = "helloWorld")
    public Object getObject() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("userId", "17325302081");
        //UserRegisterModel model = this.userRegisterService.getObject(queryMap, UserRegisterModel.class);
        return this.userRegisterService.getObject(queryMap, UserRegisterModel.class);
    }
}
