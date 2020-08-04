package com.tt.manage.service;


import com.tt.manage.entity.DrawConfigVip;

import java.util.List;

/**
 * - 服务层
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
public interface DrawConfigVipService {

    List<DrawConfigVip> selectAll();

    void updateDrawConfig(List<DrawConfigVip> drawConfigs);

}
