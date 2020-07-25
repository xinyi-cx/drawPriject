package com.tt.prize.service;


import com.tt.base.entity.UserEnvelope;

import java.util.List;

/**
 * - 服务层
 * 
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
public interface IUserEnvelopeService 
{
	/**
     * 查询-信息
     * 
     * @param userId Integer
     * @return -信息
     */
	public UserEnvelope selectUserEnvelopeById(Integer userId);
	
	/**
     * 查询-列表
     * 
     * @param userEnvelope -信息
     * @return -集合
     */
	public List<UserEnvelope> selectUserEnvelopeList(UserEnvelope userEnvelope);
	
	/**
     * 新增-
     * 
     * @param userEnvelope -信息
     * @return 结果
     */
	public int insertUserEnvelope(UserEnvelope userEnvelope);
	
	/**
     * 修改-
     * 
     * @param userEnvelope -信息
     * @return 结果
     */
	public int updateUserEnvelope(UserEnvelope userEnvelope);
		
	/**
     * 删除-信息
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserEnvelopeByIds(String[] userIds);
	
}
