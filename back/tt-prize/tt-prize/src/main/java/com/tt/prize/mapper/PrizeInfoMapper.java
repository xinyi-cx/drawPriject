package com.tt.prize.mapper;

import com.tt.base.entity.PrizeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * - 数据层
 * 
 * @author j
 * @date 2020-01-03 11:44:22
 */
@Mapper
@Component
public interface PrizeInfoMapper 
{
	/**
     * 查询-信息
     * 
     * @param prizeRecId PrizeInfo ID
     * @return -信息
     */
	public PrizeInfo selectPrizeInfoById(Integer prizeRecId);
	
	/**
     * 查询-列表
     * 
     * @param prizeInfo PrizeInfo信息
     * @return -集合
     */
	public List<PrizeInfo> selectPrizeInfoList(PrizeInfo prizeInfo);
	
	/**
     * 新增-
     * 
     * @param prizeInfo PrizeInfo 信息
     * @return 结果
     */
	public int insertPrizeInfo(PrizeInfo prizeInfo);
	
	/**
     * 修改-
     * 
     * @param  prizeInfo  -信息
     * @return 结果
     */
	public int updatePrizeInfo(PrizeInfo prizeInfo);
	
	/**
     * 删除-
     * 
     * @param prizeRecId -ID
     * @return 结果
     */
	public int deletePrizeInfoById(Integer prizeRecId);
	
	/**
     * 批量删除-
     * 
     * @param prizeRecIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePrizeInfoByIds(String[] prizeRecIds);

    List<PrizeInfo> selectPrizeInfoByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}