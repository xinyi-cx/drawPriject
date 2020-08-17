package com.tt.manage.service.impl;

import com.tt.manage.entity.UserCodeRef;
import com.tt.manage.entity.UserInfo;
import com.tt.manage.mapper.UserCodeRefMapper;
import com.tt.manage.mapper.UserInfoMapper;
import com.tt.manage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * - 服务层实现
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserCodeRefMapper userCodeRefMapper;

    /**
     * 判断用户
     *
     * @param userInfo
     * @return
     */
    @Override
    public UserInfo judgeUser(UserInfo userInfo) {
        UserInfo userInfo1 = userInfoMapper.selectByPrimaryKey(userInfo.getUserId());
        if (userInfo1 != null && userInfo1.getUserName().equals(userInfo.getUserName())) {
            UserCodeRef userCodeRef = userCodeRefMapper.selectByPrimaryKey(userInfo.getUserId());
            userInfo1.setDrawNum(userCodeRef.getDrawNum());
            userInfo1.setLoginTime(new Date());
            userInfoMapper.updateByPrimaryKey(userInfo1);
            return userInfo1;
        }
        return null;
    }

    @Override
    public int deleteAll() {
        return userInfoMapper.deleteAll();
    }


}
