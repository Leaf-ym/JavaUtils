package com.example.demo.dao;

import com.ncepu.model.UserRegisterModel;
import org.springframework.stereotype.Repository;

/**
 * @author wengym
 * @version 1.0
 * @desc DAO层的基础接口
 * @date 2022/5/13 15:26
 */
@Repository
public interface UserRegisterDAO extends BaseDAO<UserRegisterModel> {

    class UserRegisterDaoProvider {

    }
}
