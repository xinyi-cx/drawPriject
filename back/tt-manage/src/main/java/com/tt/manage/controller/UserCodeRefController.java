package com.tt.manage.controller;


import com.github.pagehelper.PageInfo;
import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.UserCodeRef;
import com.tt.manage.service.UserCodeRefService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author mohanwen
 * @className UserRewardController
 * @Description TODO(- - Controller 调用接口)
 * @Date 2020-07-08 18:24:39
 */
@RestController
@RequestMapping("/userCodeRef")
public class UserCodeRefController extends BaseController {

    @Autowired
    private UserCodeRefService userCodeRefService;

    /**
     * 获取 -依据ID
     */
    @ApiOperation(value = "导入", httpMethod = "POST")
    @PostMapping("/excelIn")
    public ResponseBean excelIn(MultipartFile file, boolean updateSupport) throws Exception {
        try {
            List<UserCodeRef> userCodeRefs = userCodeRefService.addUserCodeRefsByPath(file.getInputStream());
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, userCodeRefs);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 查询-列表
     */
    @ApiOperation(value = "查询-列表", httpMethod = "GET")
    @GetMapping("/list")
    public ResponseBean list(UserCodeRef ref) {
        try {
            startPage();
            List<UserCodeRef> list = userCodeRefService.selectUserCodeRefList(ref);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, list, new PageInfo(list).getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 新增
     */
    @ResponseBody
    @ApiOperation(value = "新增", httpMethod = "POST")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseBean insert(@RequestBody UserCodeRef ref) {
        try {
            userCodeRefService.insert(ref);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, ref);
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
    public ResponseBean deleteByPrimaryKey(@PathVariable("userId") String userId) {
        try {
            userCodeRefService.deleteByPrimaryKey(userId);
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
    public ResponseBean selectByPrimaryKey(@PathVariable("userId") String userId) {
        try {
            UserCodeRef ref = userCodeRefService.selectByPrimaryKey(userId);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, ref);
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
    public ResponseBean updateByPrimaryKey(@RequestBody UserCodeRef ref) {
        try {
            userCodeRefService.updateByPrimaryKey(ref);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, ref);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }


}
