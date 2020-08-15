package com.tt.manage.mapper;

import com.tt.manage.entity.SystemConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SystemConfigMapper {
    int deleteByPrimaryKey(Long configId);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByKey(String configKey);

    SystemConfig selectByPrimaryKey(Long configId);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);

    List<SystemConfig> selectUrlList();
}