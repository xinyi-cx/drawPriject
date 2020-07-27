package com.tt.manage.controller;


import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.service.UserCodeRefService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mohanwen
 * @className UserRewardController
 * @Description TODO(- - Controller 调用接口)
 * @Date 2020-07-08 18:24:39
 */
@RestController
@RequestMapping("/userCodeRef")
public class UserCodeRefController {

    @Autowired
    private UserCodeRefService userCodeRefService;

    /**
     * 获取 -依据ID
     */
    @ApiOperation(value = "导入", httpMethod = "POST")
    @PostMapping("/excelIn")
    public ResponseBean createReward(MultipartFile file, boolean updateSupport) throws Exception {
        String path = "";
        userCodeRefService.addUserCodeRefsByPath(file.getInputStream());
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, "");
    }

//    public ResponseBean createReward(MultipartFile file, boolean updateSupport) {
//        userCodeRefService.addUserCodeRefsByPath(path);
//        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, "");
//    }

//    /**
//     * 查询-列表
//     */
//    @ResponseBody
//    @RequestMapping(value = "/list", method = RequestMethod.POST)
//    public ResponseBean list(@RequestBody UserReward userReward) {
//        try {
//            List<UserReward> list = userRewardService.selectUserRewardList(userReward);
//            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, list);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
//        }
//    }

}
