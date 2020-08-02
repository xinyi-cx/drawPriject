package com.tt.manage.controller;


import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.DrawConfig;
import com.tt.manage.service.DrawConfigService;
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
@RequestMapping("/drawConfig")
public class DrawConfigController {

    @Autowired
    private DrawConfigService drawConfigService;

    /**
     * 更新
     */
    @ApiOperation(value = "更新抽奖配置表", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseBean update(@RequestBody List<DrawConfig> drawConfigs) {
        drawConfigService.updateDrawConfig(drawConfigs);
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, drawConfigs);
    }

    @ApiOperation(value = "更新一个抽奖配置表", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/updateOne", method = RequestMethod.POST)
    public ResponseBean updateOne(@RequestBody DrawConfig drawConfig) {
        List<DrawConfig> drawConfigs = new ArrayList<>();
        drawConfigs.add(drawConfig);
        drawConfigService.updateDrawConfig(drawConfigs);
        List<DrawConfig> drawConfigAll = drawConfigService.selectAll();
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, drawConfigAll);
    }

    /**
     * 查询-列表
     */
    @ApiOperation(value = "查询所有抽奖配置", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseBean list() {
        try {
            List<DrawConfig> drawConfigs = drawConfigService.selectAll();
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, drawConfigs);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

}
