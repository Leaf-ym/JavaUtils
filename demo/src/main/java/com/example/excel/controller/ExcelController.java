package com.example.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.example.excel.listener.CommonExcelDataListener;
import com.example.excel.listener.LiveViewModelListener;
import com.example.excel.model.CommonExcelDataModel;
import com.example.excel.model.IdCardModel;
import com.example.excel.model.LiveViewModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @title:MemberController
 * @Author:Wei
 * @Date:2021/3/17 8:40
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/meetapi/excel")
public class ExcelController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), CommonExcelDataModel.class, new CommonExcelDataListener()).sheet().doRead();
        return "success";
    }

    /**
     * 基于直播观看记录计算签到次数
     *
     * @param file
     *
     * @author wengym
     *
     * @date 2022/10/24 16:28
     *
     * @return java.lang.String
     */
    @RequestMapping(value = "/dealLiveSignature", method = RequestMethod.POST)
    public String dealLiveSignature(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), LiveViewModel.class, new LiveViewModelListener()).sheet().doRead();
        return "success";
    }

    /**
     * 处理身份证
     *
     * @param file
     *
     * @author wengym
     *
     * @date 2022/11/3 9:43
     *
     * @return java.lang.String
     */
    @RequestMapping(value = "/handleIdCardList", method = RequestMethod.POST)
    public String handleIdCardList(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), IdCardModel.class, new LiveViewModelListener()).sheet().doRead();
        return "success";
    }
}
