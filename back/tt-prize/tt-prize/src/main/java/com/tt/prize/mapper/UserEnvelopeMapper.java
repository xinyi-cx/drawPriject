package com.tt.prize.mapper;

import com.tt.base.entity.UserEnvelope;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * - 数据层
 * 
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
@Mapper
@Component
public interface UserEnvelopeMapper 
{
	/**
     * 查询-信息
     * 
     * @param userId UserEnvelope ID
     * @return -信息
     */
	public UserEnvelope selectUserEnvelopeById(Integer userId);
	
	/**
     * 查询-列表
     * 
     * @param userEnvelope UserEnvelope信息
     * @return -集合
     */
	public List<UserEnvelope> selectUserEnvelopeList(UserEnvelope userEnvelope);
	
	/**
     * 新增-
     * 
     * @param userEnvelope UserEnvelope 信息
     * @return 结果
     */
	public int insertUserEnvelope(UserEnvelope userEnvelope);
	
	/**
     * 修改-
     * 
     * @param  userEnvelope  -信息
     * @return 结果
     */
	public int updateUserEnvelope(UserEnvelope userEnvelope);
	
	/**
     * 删除-
     * 
     * @param userId -ID
     * @return 结果
     */
	public int deleteUserEnvelopeById(Integer userId);
	
	/**
     * 批量删除-
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserEnvelopeByIds(String[] userIds);
	
}