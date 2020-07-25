package com.tt.manage.service.impl;

import com.tt.manage.common.CommonConstance;
import com.tt.manage.entity.DrawConfig;
import com.tt.manage.entity.UserCodeRef;
import com.tt.manage.entity.UserReward;
import com.tt.manage.mapper.CodeDrawRefMapper;
import com.tt.manage.mapper.DrawConfigMapper;
import com.tt.manage.mapper.UserCodeRefMapper;
import com.tt.manage.mapper.UserRewardMapper;
import com.tt.manage.service.UserRewardService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * - 服务层实现
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
@Service
public class UserRewardServiceImpl implements UserRewardService {

    @Autowired
    private UserCodeRefMapper userCodeRefMapper;

    @Autowired
    private CodeDrawRefMapper codeDrawRefMapper;

    @Autowired
    private DrawConfigMapper drawConfigMapper;

    @Autowired
    private UserRewardMapper userRewardMapper;

    /**
     * 获取用户奖励列表
     *
     * @param userReward
     * @return
     */
    @Override
    public List<UserReward> selectUserRewardList(UserReward userReward, boolean flag) {
        List<UserReward> userRewards = userRewardMapper.selectUserRewardList(userReward);

        if (flag) {
            for (UserReward userReward1 : userRewards) {
                String uId = userReward1.getUserId();
                userReward1.setUserId(StringUtils.overlay(uId, "*", CommonConstance.TM_STAR, CommonConstance.TM_END));
            }
        }

        return userRewards;
    }

    /**
     * 根据打码量生成奖励
     *
     * @param userId
     * @return
     */
    @Override
    public UserReward createRewardByUserId(String userId) {
        UserCodeRef userCodeRef = userCodeRefMapper.selectByPrimaryKey(userId);
        Long code = userCodeRef.getCode();
        String drawDigit = codeDrawRefMapper.getDrawDigitByCode(code);
        List<DrawConfig> drawConfigs = drawConfigMapper.selectAll();
        int reward = 0;
        boolean flag = false;
        if (CommonConstance.BWW.equals(drawDigit)) {
            DrawConfig drawConfig = getDrawConfigByDigit(drawConfigs, CommonConstance.BWW);
            reward += getNum(drawConfig.getDigitStart().intValue(), drawConfig.getDigitEnd().intValue()) * 1000000;
            flag = true;
        }

        if (flag || CommonConstance.SWW.equals(drawDigit)) {
            DrawConfig drawConfig = getDrawConfigByDigit(drawConfigs, CommonConstance.SWW);
            reward += getNum(drawConfig.getDigitStart().intValue(), drawConfig.getDigitEnd().intValue()) * 100000;
            flag = true;
        }

        if (flag || CommonConstance.WW.equals(drawDigit)) {
            DrawConfig drawConfig = getDrawConfigByDigit(drawConfigs, CommonConstance.WW);
            reward += getNum(drawConfig.getDigitStart().intValue(), drawConfig.getDigitEnd().intValue()) * 10000;
            flag = true;
        }

        if (flag || CommonConstance.QW.equals(drawDigit)) {
            DrawConfig drawConfig = getDrawConfigByDigit(drawConfigs, CommonConstance.QW);
            reward += getNum(drawConfig.getDigitStart().intValue(), drawConfig.getDigitEnd().intValue()) * 1000;
            flag = true;
        }

        if (flag || CommonConstance.BW.equals(drawDigit)) {
            DrawConfig drawConfig = getDrawConfigByDigit(drawConfigs, CommonConstance.BW);
            reward += getNum(drawConfig.getDigitStart().intValue(), drawConfig.getDigitEnd().intValue()) * 100;
            flag = true;
        }

        if (flag || CommonConstance.SW.equals(drawDigit)) {
            DrawConfig drawConfig = getDrawConfigByDigit(drawConfigs, CommonConstance.SW);
            reward += getNum(drawConfig.getDigitStart().intValue(), drawConfig.getDigitEnd().intValue()) * 10;
            flag = true;
        }

        if (flag || CommonConstance.GW.equals(drawDigit)) {
            DrawConfig drawConfig = getDrawConfigByDigit(drawConfigs, CommonConstance.GW);
            reward += getNum(drawConfig.getDigitStart().intValue(), drawConfig.getDigitEnd().intValue()) * 1;
        }
        UserReward userReward = new UserReward();
        userReward.setReward((long) reward);
        userReward.setUserId(userId);
        userReward.setCreatTime(new Date());
        userReward.setRewardStatus(CommonConstance.REWARD_STATUS_YCJ);
        userRewardMapper.insert(userReward);
        //打码清零
        userCodeRef.setCode(0L);
        userCodeRef.setDrawNum(0);
        userCodeRefMapper.updateByPrimaryKey(userCodeRef);
        return userReward;
    }

    @Override
    public List<UserReward> selectUserRewardByUserId(String userId) {
        UserReward userReward = new UserReward();
        userReward.setUserId(userId);
        return userRewardMapper.selectUserRewardList(userReward);
    }


    /**
     * 生成两个数字之间的一个随机数
     *
     * @param start
     * @param end
     * @return
     */
    private int getNum(int start, int end) {
        return new Random().nextInt((end - start + 1)) + start;
    }

    private DrawConfig getDrawConfigByDigit(List<DrawConfig> drawConfigs, String digit) {
        for (DrawConfig drawConfig : drawConfigs) {
            if (digit.equals(drawConfig.getDigit())) {
                return drawConfig;
            }
        }
        return null;
    }


}
