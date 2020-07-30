package com.tt.manage.controller;


import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.SystemUser;
import com.tt.manage.service.SystemUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mohanwen
 * @className UserManageController
 * @Description TODO(- - Controller 调用接口)
 * @Date 2020-01-02 18:24:39
 */
@RestController
@RequestMapping("/systemUser")
public class SystemUserController extends BaseController {

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 新增
     */
    @ResponseBody
    @ApiOperation(value = "新增", httpMethod = "POST")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseBean insert(@RequestBody SystemUser systemUser) {
        try {
            systemUserService.insert(systemUser);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, systemUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 删除
     */
    @ResponseBody
    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping("/deleteByPrimaryKey/{userId}")
    public ResponseBean deleteByPrimaryKey(@PathVariable("userId") Integer userId) {
        try {
            systemUserService.deleteByPrimaryKey(userId);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 查询一个
     */
    @ResponseBody
    @ApiOperation(value = "查询一个", httpMethod = "POST")
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
    @PostMapping("/selectByPrimaryKey/{userId}")
    public ResponseBean selectByPrimaryKey(@PathVariable("userId") Integer userId, ModelMap map) {
        try {
            SystemUser systemUsers = systemUserService.selectByPrimaryKey(userId);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, systemUsers);
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
    public ResponseBean updateByPrimaryKey(@RequestBody SystemUser systemUser) {
        try {
            systemUserService.updateByPrimaryKey(systemUser);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, systemUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 查询列表
     */
    @ResponseBody
    @ApiOperation(value = "登陆", httpMethod = "POST")
    @RequestMapping(value = "/selectSystemUserList", method = RequestMethod.POST)
    public ResponseBean selectSystemUserList(@RequestBody SystemUser systemUser) {
        try {
            startPage();
            List<SystemUser> systemUsers = systemUserService.selectSystemUserList(systemUser);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, systemUsers);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }


}
