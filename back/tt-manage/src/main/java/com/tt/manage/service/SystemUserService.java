package com.tt.manage.service;


import com.tt.manage.entity.SystemUser;

import java.util.List;

/**
 * - 服务层
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
public interface SystemUserService {

    int deleteByPrimaryKey(Integer userId);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    List<SystemUser> selectSystemUserList(SystemUser record);
}
