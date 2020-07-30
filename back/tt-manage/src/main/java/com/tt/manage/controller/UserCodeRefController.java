package com.tt.manage.controller;


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
        userCodeRefService.addUserCodeRefsByPath(file.getInputStream());
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, "");
    }

    /**
     * 查询-列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseBean list(@RequestBody UserCodeRef ref) {
        try {
            startPage();
            List<UserCodeRef> list = userCodeRefService.selectUserCodeRefList(ref);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

}
