package com.tt.prize.service.impl;

import com.tt.base.entity.PrizeInfo;
import com.tt.prize.mapper.PrizeInfoMapper;
import com.tt.prize.service.IPrizeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * - 服务层实现
 * 
 * @author j
 * @date 2020-01-03 11:44:22
 */
@Service
public class PrizeInfoServiceImpl implements IPrizeInfoService
{
	@Autowired
	private PrizeInfoMapper prizeInfoMapper;

	/**
     * 查询-信息
     * 
     * @param prizeRecId Integer
     * @return -信息
     */
    @Override
	public PrizeInfo selectPrizeInfoById(Integer prizeRecId)
	{
	    return prizeInfoMapper.selectPrizeInfoById(prizeRecId);
	}
	
	/**
     * 查询-列表
     * 
     * @param prizeInfo PrizeInfo 信息
     * @return -集合
     */
	@Override
	public List<PrizeInfo> selectPrizeInfoList(PrizeInfo prizeInfo)
	{
	    return prizeInfoMapper.selectPrizeInfoList(prizeInfo);
	}
	
    /**
     * 新增-
     * 
     * @param prizeInfo ${classname} -信息
     * @return 结果
     */
	@Override
	public int insertPrizeInfo(PrizeInfo  prizeInfo)
	{
	    return prizeInfoMapper.insertPrizeInfo(prizeInfo);
	}
	
	/**
     * 修改-
     * 
     * @param prizeInfo PrizeInfo -信息
     * @return 结果
     */
	@Override
	public int updatePrizeInfo(PrizeInfo prizeInfo)
	{
	    return prizeInfoMapper.updatePrizeInfo(prizeInfo);
	}

	/**
     * 删除-对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePrizeInfoByIds(String[] ids)
	{
		return prizeInfoMapper.deletePrizeInfoByIds(ids);
	}
	
}
