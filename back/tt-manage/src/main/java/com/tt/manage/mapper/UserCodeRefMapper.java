package com.tt.manage.mapper;

import com.tt.manage.entity.UserCodeRef;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserCodeRefMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserCodeRef record);

    int insertSelective(UserCodeRef record);

    UserCodeRef selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserCodeRef record);

    int updateByPrimaryKey(UserCodeRef record);

    int addUserCodeRefs(List<UserCodeRef> list);
}