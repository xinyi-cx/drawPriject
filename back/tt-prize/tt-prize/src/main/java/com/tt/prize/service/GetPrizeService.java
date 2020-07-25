package com.tt.prize.service;


import com.tt.base.entity.PrizeInfo;
import com.tt.prize.model.PrizeModel;

import java.util.List;

public interface GetPrizeService {

    List<PrizeInfo> getPrizeInfo(String userName);

    PrizeModel clickRedEnv(Integer userId);

    String testClick();

}
