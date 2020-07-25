package com.tt.manage.service;


import com.tt.manage.entity.DrawConfig;

import java.util.List;

/**
 * - 服务层
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
public interface DrawConfigService {

    List<DrawConfig> selectAll();

    void updateDrawConfig(List<DrawConfig> drawConfigs);

}
