package com.tt.prize.service.impl;

import com.tt.base.entity.PrizeInfo;
import com.tt.base.entity.UserEnvelope;
import com.tt.prize.mapper.PrizeInfoMapper;
import com.tt.prize.mapper.UserEnvelopeMapper;
import com.tt.prize.model.PrizeModel;
import com.tt.prize.service.GetPrizeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GetPrizeServiceImpl implements GetPrizeService {

    @Autowired
    private UserEnvelopeMapper userEnvelopeMapper;
    @Autowired
    private PrizeInfoMapper prizeInfoMapper;

    @Override
    public List<PrizeInfo> getPrizeInfo(String userName) {
        PrizeInfo prizeInfo = new PrizeInfo();
        prizeInfo.setUserName(userName);
        List<PrizeInfo> prizeInfos = prizeInfoMapper.selectPrizeInfoList(prizeInfo);
        return prizeInfos;
    }

    @Override
    public PrizeModel clickRedEnv(Integer userId) {
        UserEnvelope userEnvelope = userEnvelopeMapper.selectUserEnvelopeById(userId);
        String userName = userEnvelope.getUserName();
        PrizeInfo prizeInfo = new PrizeInfo();
        prizeInfo.setUserName(userName);
        prizeInfo.setGetPrizeTime(new Date(System.currentTimeMillis()));
        PrizeModel prizeModel = new PrizeModel();
        if(userEnvelope.getEnvelopeNum()>0) {
            int sum = userEnvelope.getEnvelopeNum() - 1;
            userEnvelope.setEnvelopeNum(sum);
            userEnvelopeMapper.updateUserEnvelope(userEnvelope);
            prizeModel.setLeftNum(sum);
            if(!StringUtils.isEmpty(userEnvelope.getPrize().trim())&&!userEnvelope.getPrize().trim().equals("无")){
                String prize = userEnvelope.getPrize();
                userEnvelope.setPrize("无");
                userEnvelopeMapper.updateUserEnvelope(userEnvelope);
                prizeInfo.setPrizeInfo(prize);
                prizeInfoMapper.insertPrizeInfo(prizeInfo);
                prizeModel.setPrizeInfo("恭喜您获得"+prize);
                return prizeModel;
            }
            String moneyRange = userEnvelope.getMoneyRange();
            String[] split = moneyRange.split("~");
            Integer max = Integer.parseInt(split[1]);
            Integer min = Integer.parseInt(split[0]);
            Random random = new Random();
            int awards = random.nextInt(max) % (max - min + 1) + min;
            prizeInfo.setPrizeInfo(awards + "");
            prizeInfoMapper.insertPrizeInfo(prizeInfo);
            prizeModel.setPrizeInfo("恭喜您获得"+awards+"元彩金");
            return prizeModel;
        }else {
            prizeModel.setPrizeInfo("您的机会已经用完啦");
            prizeModel.setLeftNum(0);
            return prizeModel;
        }
    }

    @Override
    public String testClick() {
        Integer max = 100;
        Integer min = 30;
        Random random = new Random();
        int awards = random.nextInt(max) % (max - min + 1) + min;
        return "恭喜您获得"+awards+"元彩金";

    }


}
