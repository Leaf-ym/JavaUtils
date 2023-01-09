package com.example.mysql.controller;

import com.example.mysql.model.VipTypeModel;
import com.example.mysql.service.VipTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc 会员类别 - controller
 * @date 2022/6/8 14:14
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/vip/vipType")
public class VipTypeController {

    @Resource
    private VipTypeService vipTypeService;

    /***
     *
     * 新增会员类别
     *
     * @param vipTypeModel
     *
     * @author wengym
     *
     * @date 2022/6/27 12:00
     *
     * @return java.lang.Object
     *
     */
    @RequestMapping(value = "/insert")
    public Object insert(@RequestBody VipTypeModel vipTypeModel) {
        vipTypeModel.setCreateBy("111");
        Integer cnt = this.vipTypeService.insert(vipTypeModel);
        return cnt;
    }
}
