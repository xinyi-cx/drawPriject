package com.tt.manage.service;


import com.tt.manage.entity.UserCodeRef;
import com.tt.manage.entity.UserReward;

import java.util.List;

/**
 * - 服务层
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
public interface UserRewardService {

    /**
     * @param userReward
     * @param flag       是否脱敏
     * @return
     */
    List<UserReward> selectUserRewardList(UserReward userReward, boolean flag);

    UserReward createRewardByUserId(String userId);

    List<UserReward> selectUserRewardByUserId(String userId);

    /**
     * 根据userCodeRef生成reward
     *
     * @param userCodeRef
     * @return
     */
    int createRewardByUserCodeRef(UserCodeRef userCodeRef);

}
