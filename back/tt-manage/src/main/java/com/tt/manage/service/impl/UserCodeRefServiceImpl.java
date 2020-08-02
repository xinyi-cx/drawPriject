package com.tt.manage.service.impl;

import com.tt.base.Util.ExcelUtils;
import com.tt.manage.entity.UserCodeRef;
import com.tt.manage.entity.UserInfo;
import com.tt.manage.mapper.UserCodeRefMapper;
import com.tt.manage.mapper.UserInfoMapper;
import com.tt.manage.service.UserCodeRefService;
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
                    userInfoMapper.insert(newUser);
                }
                userCodeRef.setDrawNum(1);
                UserCodeRef userCodeRefOld = userCodeRefMapper.selectByPrimaryKey(userCodeRef.getUserId());
                if (userCodeRefOld == null) {
                    userCodeRefInsert.add(userCodeRef);
                } else {
                    userCodeRef.setCode(userCodeRefOld.getCode() + userCodeRef.getCode());
                    userCodeRefMapper.updateByPrimaryKey(userCodeRef);
                }
            }
        }
        userCodeRefMapper.addUserCodeRefs(userCodeRefInsert);
        return userCodeRefMapper.selectUserCodeRefList(new UserCodeRef());
    }

    @Override
    public List<UserCodeRef> selectUserCodeRefList(UserCodeRef ref) {
        return userCodeRefMapper.selectUserCodeRefList(ref);
    }

}
