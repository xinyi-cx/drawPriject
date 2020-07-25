package com.tt.prize.service;


import java.util.List;

/**
 * - 服务层
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
public interface IUserManageService {
    /**
     * 查询-信息
     *
     * @param userId Integer
     * @return -信息
     */
    public UserManage selectUserManageById(Integer userId);

    /**
     * 查询-列表
     *
     * @param userManage -信息
     * @return -集合
     */
    public List<UserManage> selectUserManageList(UserManage userManage);

    /**
     * 新增-
     *
     * @param userManage -信息
     * @return 结果
     */
    public int insertUserManage(UserManage userManage);

    /**
     * 修改-
     *
     * @param userManage -信息
     * @return 结果
     */
    public int updateUserManage(UserManage userManage);

    /**
     * 删除-信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserManageByIds(String[] userIds);

}
