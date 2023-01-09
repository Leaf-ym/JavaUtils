package com.example.mysql.service.impl;

import com.example.mysql.dao.VipTypeDAO;
import com.example.mysql.model.VipTypeModel;
import com.example.mysql.service.VipTypeService;
import com.ncepu.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 会员类别服务接口实现类
 * @date 2022/6/8 13:33
 */
@Slf4j
@Service
public class VipTypeServiceImpl implements VipTypeService {
    @Resource
    private VipTypeDAO vipTypeDAO;

    /***
     *
     * 插入
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/6/8 10:53
     *
     * @return java.lang.Integer
     *
     */
    @Override
    public Integer insert(VipTypeModel model) {
        Integer cnt = 0;
        try {
            cnt = this.vipTypeDAO.insert(model);
        } catch (DuplicateKeyException e) {
            log.error("重复键异常！");
            e.printStackTrace();
        }
        return cnt;
    }

    /***
     *
     * 根据查询条件获取一条数据记录
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/6/8 13:34
     *
     * @return VipPayModel
     *
     */
    @Override
    public VipTypeModel getObject(Map<String, Object> queryMap) {
        VipTypeModel model = this.vipTypeDAO.getObject(queryMap, VipTypeModel.class);
        return model;
    }

    /***
     *
     * 根据查询条件获取数据记录列表
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/6/8 13:34
     *
     * @return java.util.List<com.nursehealth.model.vip.VipPayModel>
     *
     */
    @Override
    public List<VipTypeModel> listObjects(Map<String, Object> queryMap) {
        List<VipTypeModel> list = this.vipTypeDAO.listObjects(queryMap, VipTypeModel.class);
        return list;
    }

    /***
     *
     * 根据查询条件获取数据记录列表数目
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/6/8 16:56
     *
     * @return Integer
     *
     */
    @Override
    public Integer countListObjects(Map<String, Object> queryMap) {
        Integer cnt = this.vipTypeDAO.countListObjects(queryMap, VipTypeModel.class);
        return cnt;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Integer
     * @author wengym
     * @date 2022/10/26 15:44
     */
    @Override
    public Integer delete(Integer id) {
        Integer cnt = this.vipTypeDAO.delete(id);
        return cnt;
    }
}
