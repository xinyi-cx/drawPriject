package com.tt.manage.controller;


import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.CodeDrawRefVip;
import com.tt.manage.service.CodeDrawRefVipService;
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
@RequestMapping("/codeDrawRefVip")
public class CodeDrawRefVipController {

    @Autowired
    private CodeDrawRefVipService codeDrawRefVipService;

    /**
     * 查询-列表
     */
    @ApiOperation(value = "查询打码抽奖位数关联表", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseBean list() {
        try {
            List<CodeDrawRefVip> list = codeDrawRefVipService.selectCodeDrawRefList();
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }

    }

    /**
     * 修改保存-
     */
    @ApiOperation(value = "根据抽奖位数更新打码抽奖位数关联表", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseBean update(@RequestBody CodeDrawRefVip codeDrawRefVip) {
        try {
            codeDrawRefVipService.updateByDrawDigit(codeDrawRefVip);
            List<CodeDrawRefVip> list = codeDrawRefVipService.selectCodeDrawRefList();
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 修改保存-
     */
    @ApiOperation(value = "根据抽奖位数更新打码抽奖位数关联表", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/updateByList", method = RequestMethod.POST)
    public ResponseBean updateByList(@RequestBody List<CodeDrawRefVip> codeDrawRefVips) {
        try {
            for (CodeDrawRefVip codeDrawRefVip : codeDrawRefVips) {
                codeDrawRefVipService.updateByDrawDigit(codeDrawRefVip);
            }
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, codeDrawRefVips);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 获取
     */
    @ApiOperation(value = "获取一个打码抽奖位数关联信息", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/getCodeDrawRefVip", method = RequestMethod.POST)
    public ResponseBean getCodeDrawRefVipByParam(@RequestBody CodeDrawRefVip codeDrawRefVip) {
        CodeDrawRefVip result = codeDrawRefVipService.selectCodeDrawRef(codeDrawRefVip);
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, result);
    }


}
