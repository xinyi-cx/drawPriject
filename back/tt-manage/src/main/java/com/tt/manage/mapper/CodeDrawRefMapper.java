package com.tt.manage.mapper;

import com.tt.manage.entity.CodeDrawRef;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CodeDrawRefMapper {
    int deleteByPrimaryKey(String refId);

    int insert(CodeDrawRef record);

    int insertSelective(CodeDrawRef record);

    CodeDrawRef selectByPrimaryKey(String refId);

    CodeDrawRef selectByParam(CodeDrawRef record);

    int updateByPrimaryKeySelective(CodeDrawRef record);

    int updateByPrimaryKey(CodeDrawRef record);

    /**
     * 根据DrawDigit更新
     *
     * @param record
     * @return
     */
    int updateByDrawDigit(CodeDrawRef record);

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
    List<CodeDrawRef> selectCodeDrawRefList();

}