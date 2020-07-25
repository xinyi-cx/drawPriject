package com.tt.prize.service;


import java.util.List;

/**
 * - 服务层
 * 
 * @author j
 * @date 2020-01-03 10:55:30
 */
public interface IActivityInfoService 
{
	/**
     * 查询-信息
     * 
     * @param activityRecId Integer
     * @return -信息
     */
	public ActivityInfo selectActivityInfoById(Integer activityRecId);
	
	/**
     * 查询-列表
     * 
     * @param activityInfo -信息
     * @return -集合
     */
	public List<ActivityInfo> selectActivityInfoList(ActivityInfo activityInfo);
	
	/**
     * 新增-
     * 
     * @param activityInfo -信息
     * @return 结果
     */
	public int insertActivityInfo(ActivityInfo activityInfo);
	
	/**
     * 修改-
     * 
     * @param activityInfo -信息
     * @return 结果
     */
	public int updateActivityInfo(ActivityInfo activityInfo);
		
	/**
     * 删除-信息
     * 
     * @param activityRecIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteActivityInfoByIds(String[] activityRecIds);
	
}
