package com.tt.manage.controller;


import com.alibaba.fastjson.JSONObject;
import com.tt.base.Util.ExcelUtils;
import com.tt.base.cotent.RestApi;
import com.tt.base.pojo.ResponseBean;
import com.tt.manage.entity.UserReward;
import com.tt.manage.service.UserRewardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author mohanwen
 * @className UserRewardController
 * @Description TODO(- - Controller 调用接口)
 * @Date 2020-07-18 18:24:39
 */
@RestController
@RequestMapping("/userReward")
public class UserRewardController {

    @Autowired
    private UserRewardService userRewardService;

    /**
     * 新增抽奖 -依据ID
     */
    @ApiOperation(value = "根据用户id，用户进行抽奖", httpMethod = "POST")
    @PostMapping("/createReward/{userId}")
    public ResponseBean createReward(@PathVariable("userId") String userId, ModelMap map) {
        UserReward userReward = userRewardService.createRewardByUserId(userId);
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, userReward);
    }

    /**
     * 获取 -依据ID
     */
    @ApiOperation(value = "根据用户id获得用户的所有抽奖信息", httpMethod = "POST")
    @PostMapping("/getReward/{userId}")
    public ResponseBean getReward(@PathVariable("userId") String userId, ModelMap map) {
        List<UserReward> userRewards = userRewardService.selectUserRewardByUserId(userId);
        return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, userRewards);
    }

    /**
     * 查询-列表
     */
    @ApiOperation(value = "获取所有抽奖信息（不脱敏），用于后台管理显示", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseBean list(@RequestBody UserReward userReward) {
        try {
            List<UserReward> list = userRewardService.selectUserRewardList(userReward, false);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 查询-列表 有脱敏处理
     */
    @ApiOperation(value = "获取所有抽奖信息（脱敏），用于抽奖页面显示", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/listForDraw", method = RequestMethod.POST)
    public ResponseBean listForDraw(@RequestBody UserReward userReward) {
        try {
            List<UserReward> list = userRewardService.selectUserRewardList(userReward, true);
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
        }
    }

    /**
     * 导出中奖信息为excel
     *
     * @param jsonObject
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "导出", httpMethod = "POST")
    @RequestMapping(value = "/excelOut")
    public ResponseBean excelStandardTemplateOut(@RequestBody JSONObject jsonObject, HttpServletRequest request,
                                                 HttpServletResponse response) throws IOException {

        /// TODO: 2020/7/19 导出后状态变成已派发。逻辑还未确认

        ResponseBean responseBean;
        URL save = Thread.currentThread().getContextClassLoader().getResource("");
        String str = save.toString();
        str = str.substring(5, str.length());
        str = str.replaceAll("%20", " ");
        int num = str.lastIndexOf("tt-manage");//wgbs 为项目名，应用到不同的项目中，这个需要修改！
        str = str.substring(0, num + "tt-manage".length());
        str = str + "/data/中奖记录.xls";//Excel模板所在的路径。
        UserReward userReward = JSONObject.toJavaObject(jsonObject, UserReward.class);
        List<UserReward> userRewards = userRewardService.selectUserRewardList(userReward, false);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("userId", "会员账号");
        map.put("reward", "奖金");
        map.put("creatTime", "抽奖时间");
        map.put("rewardStatus", "抽奖状态");

        if (userRewards.size() == 0) {
            new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, "");
        }
        try {
            ExcelUtils.write(str, map, userRewards);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File f = new File(str);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String fileName = "中奖记录.xls";
        try {
//            response.setHeader("Content-Disposition", "attachment;filename="+ new String(("prizeInfo" + ".xlsx").getBytes(), "iso-8859-1"));//下载文件的名称
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(f));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, fileName);
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

//    public static void main(String[] args) throws Exception {
//        List<UserCodeRef> userCodeRefs = ExcelUtils.parseFromExcel("D:\\test.xlsx", 1,
//                UserCodeRef.class);
//        for (int i = 0; i < userCodeRefs.size(); i++) {
//            System.err.println(userCodeRefs.get(i).toString());
//        }
//    }

}
