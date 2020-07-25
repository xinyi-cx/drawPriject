package com.tt.prize.service.impl;

import com.tt.base.entity.UserEnvelope;
import com.tt.prize.mapper.UserEnvelopeMapper;
import com.tt.prize.service.IUserEnvelopeService;
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
public class UserEnvelopeServiceImpl implements IUserEnvelopeService
{
	@Autowired
	private UserEnvelopeMapper userEnvelopeMapper;

	/**
     * 查询-信息
     * 
     * @param userId Integer
     * @return -信息
     */
    @Override
	public UserEnvelope selectUserEnvelopeById(Integer userId)
	{
	    return userEnvelopeMapper.selectUserEnvelopeById(userId);
	}
	
	/**
     * 查询-列表
     * 
     * @param userEnvelope UserEnvelope 信息
     * @return -集合
     */
	@Override
	public List<UserEnvelope> selectUserEnvelopeList(UserEnvelope userEnvelope)
	{
	    return userEnvelopeMapper.selectUserEnvelopeList(userEnvelope);
	}
	
    /**
     * 新增-
     * 
     * @param userEnvelope ${classname} -信息
     * @return 结果
     */
	@Override
	public int insertUserEnvelope(UserEnvelope  userEnvelope)
	{
	    return userEnvelopeMapper.insertUserEnvelope(userEnvelope);
	}
	
	/**
     * 修改-
     * 
     * @param userEnvelope UserEnvelope -信息
     * @return 结果
     */
	@Override
	public int updateUserEnvelope(UserEnvelope userEnvelope)
	{
	    return userEnvelopeMapper.updateUserEnvelope(userEnvelope);
	}

	/**
     * 删除-对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserEnvelopeByIds(String[] ids)
	{
		return userEnvelopeMapper.deleteUserEnvelopeByIds(ids);
	}
	
}
