package com.tt.prize.service;


import com.tt.base.entity.PrizeInfo;

import java.util.List;

/**
 * - 服务层
 * 
 * @author j
 * @date 2020-01-03 11:44:22
 */
public interface IPrizeInfoService 
{
	/**
     * 查询-信息
     * 
     * @param prizeRecId Integer
     * @return -信息
     */
	public PrizeInfo selectPrizeInfoById(Integer prizeRecId);
	
	/**
     * 查询-列表
     * 
     * @param prizeInfo -信息
     * @return -集合
     */
	public List<PrizeInfo> selectPrizeInfoList(PrizeInfo prizeInfo);
	
	/**
     * 新增-
     * 
     * @param prizeInfo -信息
     * @return 结果
     */
	public int insertPrizeInfo(PrizeInfo prizeInfo);
	
	/**
     * 修改-
     * 
     * @param prizeInfo -信息
     * @return 结果
     */
	public int updatePrizeInfo(PrizeInfo prizeInfo);
		
	/**
     * 删除-信息
     * 
     * @param prizeRecIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePrizeInfoByIds(String[] prizeRecIds);
	
}
