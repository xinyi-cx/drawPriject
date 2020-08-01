package com.tt.manage.service.impl;

import com.tt.manage.entity.SystemUser;
import com.tt.manage.mapper.SystemUserMapper;
import com.tt.manage.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * - 服务层实现
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return systemUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(SystemUser record) {
        return systemUserMapper.insert(record);
    }

    @Override
    public int insertSelective(SystemUser record) {
        return systemUserMapper.insertSelective(record);
    }

    @Override
    public SystemUser selectByPrimaryKey(SystemUser record) {
        return systemUserMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SystemUser record) {
        return systemUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SystemUser record) {
        return systemUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SystemUser> selectSystemUserList(SystemUser record) {
        return systemUserMapper.selectSystemUserList(record);
    }

}
