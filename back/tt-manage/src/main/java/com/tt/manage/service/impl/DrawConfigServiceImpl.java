package com.tt.manage.service.impl;

import com.tt.manage.entity.DrawConfig;
import com.tt.manage.mapper.DrawConfigMapper;
import com.tt.manage.service.DrawConfigService;
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
public class DrawConfigServiceImpl implements DrawConfigService {

    @Autowired
    private DrawConfigMapper drawConfigMapper;

    @Override
    public List<DrawConfig> selectAll() {
        return drawConfigMapper.selectAll();
    }

    @Override
    public void updateDrawConfig(List<DrawConfig> drawConfigs) {
        for (DrawConfig drawConfig : drawConfigs) {
            drawConfigMapper.updateByDigit(drawConfig);
        }
    }

}
