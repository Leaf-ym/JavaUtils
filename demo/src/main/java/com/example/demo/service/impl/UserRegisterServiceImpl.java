package com.example.demo.service.impl;

import com.example.demo.dao.BaseDAO;
import com.example.demo.dao.UserRegisterDAO;
import com.example.demo.service.BaseService;
import com.example.demo.service.UserRegisterService;
import com.ncepu.model.UserRegisterModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wengym
 * @version 1.0
 * @desc 基础服务接口实现类
 * @date 2022/5/13 16:22
 */
@Service
public class UserRegisterServiceImpl extends BaseServiceImpl<UserRegisterModel> implements UserRegisterService {
}
