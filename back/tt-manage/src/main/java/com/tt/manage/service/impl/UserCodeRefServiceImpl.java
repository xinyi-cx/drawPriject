package com.tt.manage.service.impl;

import com.tt.base.Util.ExcelUtils;
import com.tt.manage.entity.UserCodeRef;
import com.tt.manage.entity.UserInfo;
import com.tt.manage.mapper.UserCodeRefMapper;
import com.tt.manage.mapper.UserInfoMapper;
import com.tt.manage.service.UserCodeRefService;
import com.tt.manage.service.UserRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * - 服务层实现
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
@Service
public class UserCodeRefServiceImpl implements UserCodeRefService {

    @Autowired
    private UserCodeRefMapper userCodeRefMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRewardService userRewardService;

    @Override
    public List<UserCodeRef> addUserCodeRefsByPath(InputStream is) {
        List<UserCodeRef> userCodeRefs = ExcelUtils.parseFromExcel(is, 1,
                UserCodeRef.class);
        List<UserCodeRef> userCodeRefInsert = new ArrayList<>();
        List<UserInfo> users = userInfoMapper.selectUserInfoList();
        Set<String> userIds = users.stream().map(UserInfo::getUserId).collect(Collectors.toSet());
        for (UserCodeRef userCodeRef : userCodeRefs) {
            if (userCodeRef != null && userCodeRef.getUserId() != "") {
                if (CollectionUtils.isEmpty(userIds) || !userIds.contains(userCodeRef.getUserId())) {
                    UserInfo newUser = new UserInfo();
                    newUser.setUserId(userCodeRef.getUserId());
                    newUser.setUserName(userCodeRef.getUserName());
                    newUser.setIsVip(userCodeRef.getIsVip());
                    userInfoMapper.insert(newUser);
                }
                userCodeRef.setDrawNum(1);
                userCodeRef.setReward((long) userRewardService.createRewardByUserCodeRef(userCodeRef));
                UserCodeRef userCodeRefOld = userCodeRefMapper.selectByPrimaryKey(userCodeRef.getUserId());
                if (userCodeRefOld == null) {
                    userCodeRefInsert.add(userCodeRef);
                } else {
                    userCodeRef.setCode(userCodeRefOld.getCode() + userCodeRef.getCode());
                    userCodeRefMapper.updateByPrimaryKey(userCodeRef);
                }
            }
        }
        if (!CollectionUtils.isEmpty(userCodeRefInsert)) {
            userCodeRefMapper.addUserCodeRefs(userCodeRefInsert);
        }
        return userCodeRefMapper.selectUserCodeRefList(new UserCodeRef());
    }

    @Override
    public List<UserCodeRef> selectUserCodeRefList(UserCodeRef ref) {
        return userCodeRefMapper.selectUserCodeRefList(ref);
    }

    @Override
    public int deleteByPrimaryKey(String userId) {
        return userCodeRefMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(UserCodeRef record) {
        return userCodeRefMapper.insert(record);
    }

    @Override
    public UserCodeRef selectByPrimaryKey(String userId) {
        return userCodeRefMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKey(UserCodeRef record) {
        return userCodeRefMapper.updateByPrimaryKey(record);
    }

}
