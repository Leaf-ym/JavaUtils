package com.ncepu.util.DatabaseUtils.example.service.impl;

import com.ncepu.util.DatabaseUtils.base.service.impl.DatabaseBaseServiceImpl;
import com.ncepu.util.DatabaseUtils.example.CommonTableFormRelationModel;
import com.ncepu.util.DatabaseUtils.example.dao.CommonTableFormRelationDAO;
import com.ncepu.util.DatabaseUtils.example.service.CommonTableFormRelationService;

import javax.annotation.Resource;

/**
 * @author wengym
 * @version 1.0
 * @desc 通用的数据库表和表单字段关联信息 - 接口实现类
 * @date 2023/8/25 14:56
 */
//@Service
public class CommonTableFormRelationServiceImpl extends DatabaseBaseServiceImpl<CommonTableFormRelationModel, Integer> implements CommonTableFormRelationService {

    private CommonTableFormRelationDAO commonTableFormRelationDAO;

    //@Resource
    public void setNurseSchedulingRuleClientDAO(CommonTableFormRelationDAO dao) {
        // 必须要有这一步
        this.commonTableFormRelationDAO = dao;
        super.databaseBaseDAO = dao;
    }
}
