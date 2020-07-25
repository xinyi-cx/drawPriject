package com.tt.manage.mapper;

import com.tt.manage.entity.DrawConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DrawConfigMapper {
    int deleteByPrimaryKey(String drawId);

    int insert(DrawConfig record);

    int insertSelective(DrawConfig record);

    DrawConfig selectByPrimaryKey(String drawId);

    int updateByPrimaryKeySelective(DrawConfig record);

    int updateByPrimaryKey(DrawConfig record);

    int updateByDigit(DrawConfig record);

    List<DrawConfig> selectAll();

}