package com.tt.prize.controller;


import com.alibaba.fastjson.JSONObject;
import com.tt.base.Util.JwtUtil;
import com.tt.base.entity.PrizeInfo;
import com.tt.base.entity.UserEnvelope;
import com.tt.base.pojo.ResponseBean;
import com.tt.prize.mapper.UserEnvelopeMapper;
import com.tt.prize.model.PrizeModel;
import com.tt.prize.service.GetPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("activityInfo")
public class GetPrizeController {

    @Autowired
    private GetPrizeService getPrizeService;
    @Autowired
    private UserEnvelopeMapper userEnvelopeMapper;
    @Autowired
    private HttpServletRequest request;
    @ResponseBody
    @RequestMapping("click")
    public ResponseBean clickRedEnv(@RequestBody JSONObject jsonObject){
        ResponseBean responseBean;
        try {
            String token = request.getHeader("token");
            Integer userId = jsonObject.getInteger("userId");
            UserEnvelope userEnvelope = userEnvelopeMapper.selectUserEnvelopeById(userId);
            String userName = userEnvelope.getUserName().toLowerCase();
            String account = JwtUtil.getAccount(token);
            if (userName.equals(account)) {
                PrizeModel prizeModel = getPrizeService.clickRedEnv(userId);
                responseBean = new ResponseBean("success",0,prizeModel);
            }else {
                responseBean = new ResponseBean("token失效,请重新登陆",1,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean = new ResponseBean("领取失败",1,e);
        }
        return responseBean;
    }


    @ResponseBody
    @RequestMapping("getPrizeInfo")
    public ResponseBean getPrizeInfo(@RequestBody JSONObject jsonObject){
        ResponseBean responseBean;
        try {
            String userName = jsonObject.getString("userName");
            List<PrizeInfo> prizeInfo = getPrizeService.getPrizeInfo(userName.toLowerCase());
            for (PrizeInfo info : prizeInfo) {
                Date getPrizeTime = info.getGetPrizeTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String format = simpleDateFormat.format(getPrizeTime);
                info.setFormatDate(format);
            }
            if(prizeInfo.size()==0){
                responseBean = new ResponseBean("您尚未有奖品哦",0,"");
            }else {
                responseBean = new ResponseBean("success",0,prizeInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean = new ResponseBean("领取失败",1,e);
        }
        return responseBean;
    }


    @ResponseBody
    @RequestMapping("login")
    public ResponseBean login(@RequestBody JSONObject jsonObject, HttpServletRequest request){
        try {
            String userName = jsonObject.getString("userName");
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            UserEnvelope userEnvelope = new UserEnvelope();
            userEnvelope.setUserName(userName.toLowerCase());
            List<UserEnvelope> userEnvelopes = userEnvelopeMapper.selectUserEnvelopeList(userEnvelope);
            if(userEnvelopes.size()==1){
                String status = userEnvelopes.get(0).getStatus();
                if("1".equals(status)){
                    return new ResponseBean("用户已经被禁用",1,"");
                }
                Integer userId = userEnvelopes.get(0).getUserId();
                String token = JwtUtil.createUserToken(userName.toLowerCase(), userId, ip, "password");
                JSONObject object = new JSONObject();
                object.put("userInfo",userEnvelopes.get(0));
                object.put("token",token);
                return new ResponseBean("登陆成功",0,object);
            }else {
                return new ResponseBean("用户不存在！",1,"");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean("登录失败！",1,"");
        }

    }




}
