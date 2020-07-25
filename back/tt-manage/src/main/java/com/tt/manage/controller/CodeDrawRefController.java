package com.tt.manage.controller;


import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.CodeDrawRef;
import com.tt.manage.service.CodeDrawRefService;
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
@RequestMapping("/codeDrawRef")
public class CodeDrawRefController {

    @Autowired
    private CodeDrawRefService codeDrawRefService;

    /**
     * 查询-列表
     */
    @ApiOperation(value = "查询打码抽奖位数关联表", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseBean list() {
        try {
            List<CodeDrawRef> list = codeDrawRefService.selectCodeDrawRefList();
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
    public ResponseBean update(@RequestBody CodeDrawRef codeDrawRef) {
        try {
            codeDrawRefService.updateByDrawDigit(codeDrawRef);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, codeDrawRef);
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
    public ResponseBean updateByList(@RequestBody List<CodeDrawRef> codeDrawRefs) {
        try {
            for (CodeDrawRef codeDrawRef : codeDrawRefs) {
                codeDrawRefService.updateByDrawDigit(codeDrawRef);
            }
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, codeDrawRefs);
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
    @RequestMapping(value = "/getCodeDrawRef", method = RequestMethod.POST)
    public ResponseBean getCodeDrawRefByParam(@RequestBody CodeDrawRef codeDrawRef) {
        CodeDrawRef result = codeDrawRefService.selectCodeDrawRef(codeDrawRef);
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, result);
    }


}
