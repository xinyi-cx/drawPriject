package com.tt.manage.service.impl;

import com.tt.base.Util.ExcelUtils;
import com.tt.manage.entity.UserCodeRef;
import com.tt.manage.mapper.UserCodeRefMapper;
import com.tt.manage.service.UserCodeRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

    @Override
    public void addUserCodeRefsByPath(String path) {
        List<UserCodeRef> userCodeRefs = ExcelUtils.parseFromExcel(path, 1,
                UserCodeRef.class);
        List<UserCodeRef> userCodeRefInsert = new ArrayList<>();
        for (UserCodeRef userCodeRef : userCodeRefs) {
            userCodeRef.setDrawNum(1);
            UserCodeRef userCodeRefOld = userCodeRefMapper.selectByPrimaryKey(userCodeRef.getUserId());
            if (userCodeRefOld == null) {
                userCodeRefInsert.add(userCodeRef);
            } else {
                userCodeRef.setCode(userCodeRefOld.getCode() + userCodeRef.getCode());
                userCodeRefMapper.updateByPrimaryKey(userCodeRef);
            }
        }
        userCodeRefMapper.addUserCodeRefs(userCodeRefInsert);
    }

}
