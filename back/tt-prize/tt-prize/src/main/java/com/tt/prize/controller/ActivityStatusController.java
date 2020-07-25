package com.tt.prize.controller;


import com.alibaba.fastjson.JSONObject;
import com.tt.base.pojo.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("activityTime")
public class ActivityStatusController {

    @Autowired
    private ActivityInfoMapper activityInfoMapper;

    @RequestMapping("activityLeftTime")
    @ResponseBody
    public ResponseBean leftTime() {
        ActivityInfo activityInfo = activityInfoMapper.selectLastestInfo();
        long l = 0l;
        if (activityInfo.getEndTime().getTime() < System.currentTimeMillis()) {
            activityInfo.setStatus("2");
            activityInfoMapper.updateActivityInfo(activityInfo);
        }
        String status = activityInfo.getStatus();
        if (status.equals("0")) {
            Date endTime = activityInfo.getEndTime();
            l = endTime.getTime() - System.currentTimeMillis();
        } else if (("1").equals(status)) {
            Date startTime = activityInfo.getStartTime();
            l = startTime.getTime() - System.currentTimeMillis();
        } else {
            l = 0;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", l);
        jsonObject.put("status", status);
        return new ResponseBean("success", 0, jsonObject);
    }


}
