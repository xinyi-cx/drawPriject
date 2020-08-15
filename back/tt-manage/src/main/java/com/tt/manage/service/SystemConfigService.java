package com.tt.manage.service;


import com.tt.manage.entity.SystemConfig;

import java.util.List;

/**
 * - 服务层
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
public interface SystemConfigService {

    int deleteByPrimaryKey(Long configId);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByPrimaryKey(Long configId);

    SystemConfig selectByKey(String configKey);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);

    List<SystemConfig> selectUrlList();
}
