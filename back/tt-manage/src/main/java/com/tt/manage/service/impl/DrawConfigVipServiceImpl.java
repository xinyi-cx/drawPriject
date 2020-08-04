package com.tt.manage.service.impl;

import com.tt.manage.entity.DrawConfigVip;
import com.tt.manage.mapper.DrawConfigVipMapper;
import com.tt.manage.service.DrawConfigVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * - 服务层实现
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
@Service
public class DrawConfigVipServiceImpl implements DrawConfigVipService {

    @Autowired
    private DrawConfigVipMapper drawConfigVipMapper;

    @Override
    public List<DrawConfigVip> selectAll() {
        return drawConfigVipMapper.selectAll();
    }

    @Override
    public void updateDrawConfig(List<DrawConfigVip> DrawConfigVips) {
        for (DrawConfigVip DrawConfigVip : DrawConfigVips) {
            drawConfigVipMapper.updateByDigit(DrawConfigVip);
        }
    }

}
