package com.tt.manage.service.impl;

import com.tt.manage.entity.SystemConfig;
import com.tt.manage.mapper.SystemConfigMapper;
import com.tt.manage.service.SystemConfigService;
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
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public int deleteByPrimaryKey(Long configId) {
        return systemConfigMapper.deleteByPrimaryKey(configId);
    }

    @Override
    public int insert(SystemConfig record) {
        return systemConfigMapper.insert(record);
    }

    @Override
    public int insertSelective(SystemConfig record) {
        return systemConfigMapper.insertSelective(record);
    }

    @Override
    public SystemConfig selectByPrimaryKey(Long configId) {
        return systemConfigMapper.selectByPrimaryKey(configId);
    }

    @Override
    public SystemConfig selectByKey(String configKey) {
        return systemConfigMapper.selectByKey(configKey);
    }

    @Override
    public int updateByPrimaryKeySelective(SystemConfig record) {
        return systemConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SystemConfig record) {
        return systemConfigMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SystemConfig> selectUrlList() {
        return systemConfigMapper.selectUrlList();
    }

}
