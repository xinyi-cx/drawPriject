package com.tt.manage.mapper;

import com.tt.manage.entity.DrawConfigVip;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DrawConfigVipMapper {
    int deleteByPrimaryKey(String drawId);

    int insert(DrawConfigVip record);

    int insertSelective(DrawConfigVip record);

    DrawConfigVip selectByPrimaryKey(String drawId);

    int updateByPrimaryKeySelective(DrawConfigVip record);

    int updateByPrimaryKey(DrawConfigVip record);

    int updateByDigit(DrawConfigVip record);

    List<DrawConfigVip> selectAll();

}