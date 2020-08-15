package com.tt.manage.controller;


import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.SystemConfig;
import com.tt.manage.service.SystemConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mohanwen
 * @className UserManageController
 * @Description TODO(- - Controller 调用接口)
 * @Date 2020-01-02 18:24:39
 */
@RestController
@RequestMapping("/systemConfig")
public class SystemConfigController extends BaseController {

    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * 查询一个
     */
    @ResponseBody
    @ApiOperation(value = "查询一个", httpMethod = "POST")
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
    @PostMapping("/selectActive")
    public ResponseBean selectByPrimaryKey() {
        try {
            SystemConfig systemConfig = systemConfigService.selectByKey("if_active");
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, systemConfig);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 更新
     */
    @ResponseBody
    @ApiOperation(value = "更新", httpMethod = "POST")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseBean updateByPrimaryKey(@RequestBody SystemConfig systemConfig) {
        try {
            systemConfigService.updateByPrimaryKey(systemConfig);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, systemConfig);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 查询列表
     */
    @ApiOperation(value = "查询列表", httpMethod = "GET")
    @GetMapping("/list")
    public ResponseBean selectSystemUserList() {
        try {
            List<SystemConfig> systemConfigs = systemConfigService.selectUrlList();
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, systemConfigs);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

}
