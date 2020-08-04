package com.tt.manage.controller;


import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.DrawConfigVip;
import com.tt.manage.service.DrawConfigVipService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mohanwen
 * @className UserRewardController
 * @Description TODO(- - Controller 调用接口)
 * @Date 2020-07-18 18:24:39
 */
@RestController
@RequestMapping("/drawConfigVip")
public class DrawConfigVipController {

    @Autowired
    private DrawConfigVipService drawConfigVipService;

    /**
     * 更新
     */
    @ApiOperation(value = "更新抽奖配置表", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseBean update(@RequestBody List<DrawConfigVip> drawConfigVips) {
        drawConfigVipService.updateDrawConfig(drawConfigVips);
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, drawConfigVips);
    }

    @ApiOperation(value = "更新一个抽奖配置表", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/updateOne", method = RequestMethod.POST)
    public ResponseBean updateOne(@RequestBody DrawConfigVip drawConfigVip) {
        List<DrawConfigVip> drawConfigVips = new ArrayList<>();
        drawConfigVips.add(drawConfigVip);
        drawConfigVipService.updateDrawConfig(drawConfigVips);
        List<DrawConfigVip> drawConfigVipAll = drawConfigVipService.selectAll();
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, drawConfigVipAll);
    }

    /**
     * 查询-列表
     */
    @ApiOperation(value = "查询所有抽奖配置", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseBean list() {
        try {
            List<DrawConfigVip> drawConfigVips = drawConfigVipService.selectAll();
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, drawConfigVips);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

}
