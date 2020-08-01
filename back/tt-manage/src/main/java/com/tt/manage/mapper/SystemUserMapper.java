package com.tt.manage.mapper;

import com.tt.manage.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SystemUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(SystemUser record);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    List<SystemUser> selectSystemUserList(SystemUser record);
}