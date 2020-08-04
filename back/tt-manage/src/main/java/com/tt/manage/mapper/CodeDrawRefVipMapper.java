package com.tt.manage.mapper;

import com.tt.manage.entity.CodeDrawRefVip;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CodeDrawRefVipMapper {
    int deleteByPrimaryKey(String refId);

    int insert(CodeDrawRefVip record);

    int insertSelective(CodeDrawRefVip record);

    CodeDrawRefVip selectByPrimaryKey(String refId);

    CodeDrawRefVip selectByParam(CodeDrawRefVip record);

    int updateByPrimaryKeySelective(CodeDrawRefVip record);

    int updateByPrimaryKey(CodeDrawRefVip record);

    /**
     * 根据DrawDigit更新
     *
     * @param record
     * @return
     */
    int updateByDrawDigit(CodeDrawRefVip record);

    /**
     * 根据code 获得用户奖励位数
     *
     * @param code
     * @return DrawDigit
     */
    String getDrawDigitByCode(Long code);

    /**
     * 获取列表
     *
     * @return
     */
    List<CodeDrawRefVip> selectCodeDrawRefList();

}