package com.tt.manage.mapper;

import com.tt.manage.entity.UserReward;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserRewardMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserReward record);

    int insertSelective(UserReward record);

    int updateByPrimaryKeySelective(UserReward record);

    int updateByPrimaryKey(UserReward record);

    /**
     * 获取列表
     *
     * @param record
     * @return
     */
    List<UserReward> selectUserRewardList(UserReward record);

}