package com.tt.manage.controller;


import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.SystemConfig;
import com.tt.manage.entity.UserInfo;
import com.tt.manage.service.SystemConfigService;
import com.tt.manage.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mohanwen
 * @className UserManageController
 * @Description TODO(- - Controller 调用接口)
 * @Date 2020-01-02 18:24:39
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * 登陆
     */
    @ResponseBody
    @ApiOperation(value = "登陆", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBean login(@RequestBody UserInfo userInfo) {
        try {
            UserInfo loginUser = userInfoService.judgeUser(userInfo);
            //抽奖次数何时返回
            if (loginUser != null) {
                SystemConfig systemConfig = systemConfigService.selectByKey("if_active");
                //url
                SystemConfig onlineService = systemConfigService.selectByKey("onlineService");
                SystemConfig indexUrl = systemConfigService.selectByKey("indexUrl");
                SystemConfig register = systemConfigService.selectByKey("register");
                Map<String, String> configs = new HashMap<>();
                configs.put("onlineService", onlineService != null ? onlineService.getConfigValue() : "");
                configs.put("indexUrl", indexUrl != null ? indexUrl.getConfigValue() : "");
                configs.put("register", register != null ? register.getConfigValue() : "");
                loginUser.setUrls(configs);
                loginUser.setConfigValue(systemConfig.getConfigValue());
                return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, loginUser);
            } else {
                return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }

    }


}
