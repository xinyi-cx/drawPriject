package com.tt.manage.entity;

import java.util.Date;

public class UserReward extends BaseEntity {
    private String userId;

    private String userName;

    private Long reward;

    private Date creatTime;

    private Date starttTime;

    private Date enddTime;

    public Date getStarttTime() {
        return starttTime;
    }

    public void setStarttTime(Date starttTime) {
        this.starttTime = starttTime;
    }

    public Date getEnddTime() {
        return enddTime;
    }

    public void setEnddTime(Date enddTime) {
        this.enddTime = enddTime;
    }

    private String rewardStatus;

    private String creatTimeStr;

    public String getCreatTimeStr() {
        return creatTimeStr;
    }

    public void setCreatTimeStr(String creatTimeStr) {
        this.creatTimeStr = creatTimeStr;
    }

    public int getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(int drawNum) {
        this.drawNum = drawNum;
    }

    /**
     * 可抽奖次数
     */
    private int drawNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(String rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}