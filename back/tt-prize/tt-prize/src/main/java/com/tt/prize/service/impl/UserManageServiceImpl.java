package com.tt.prize.service.impl;

import com.tt.prize.service.IUserManageService;
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
public class UserManageServiceImpl implements IUserManageService {
    @Autowired
    private UserManageMapper userManageMapper;

    /**
     * 查询-信息
     *
     * @param userId Integer
     * @return -信息
     */
    @Override
    public UserManage selectUserManageById(Integer userId) {
        return userManageMapper.selectUserManageById(userId);
    }

    /**
     * 查询-列表
     *
     * @param userManage UserManage 信息
     * @return -集合
     */
    @Override
    public List<UserManage> selectUserManageList(UserManage userManage) {
        return userManageMapper.selectUserManageList(userManage);
    }

    /**
     * 新增-
     *
     * @param userManage ${classname} -信息
     * @return 结果
     */
    @Override
    public int insertUserManage(UserManage userManage) {
        return userManageMapper.insertUserManage(userManage);
    }

    /**
     * 修改-
     *
     * @param userManage UserManage -信息
     * @return 结果
     */
    @Override
    public int updateUserManage(UserManage userManage) {
        return userManageMapper.updateUserManage(userManage);
    }

    /**
     * 删除-对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserManageByIds(String[] ids) {
        return userManageMapper.deleteUserManageByIds(ids);
    }


}
