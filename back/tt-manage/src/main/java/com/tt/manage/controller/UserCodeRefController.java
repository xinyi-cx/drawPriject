package com.tt.manage.controller;


import com.github.pagehelper.PageInfo;
import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.UserCodeRef;
import com.tt.manage.service.UserCodeRefService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @ApiOperation(value = "查询-列表", httpMethod = "GET")
//    @RequestMapping(value = "/list", method = RequestMethod.POST)
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

}
