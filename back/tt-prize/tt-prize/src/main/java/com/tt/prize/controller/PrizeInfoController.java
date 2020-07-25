package com.tt.prize.controller;


import com.tt.base.cotent.RestApi;
import com.tt.base.entity.PrizeInfo;
import com.tt.base.pojo.ResponseBean;
import com.tt.prize.service.IPrizeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  @className PrizeInfoController
 * @Description TODO(--Controller 调用接口)
 * 
 * @Date 2020-01-03 11:44:22
 * @author j
 */
@RestController
@RequestMapping("/prizeInfo")
public class PrizeInfoController
{
    private String prefix = "prizeInfo/PrizeInfo";
	
	@Autowired
	private IPrizeInfoService PrizeInfoService;
	
	@GetMapping()
	public String PrizeInfo()
	{
	    return prefix + "/PrizeInfo";
	}
	
	/**
	 * 查询-列表
	 */
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public ResponseBean list(@RequestBody PrizeInfo prizeInfo)
	{
		
       
		try {
			 List<PrizeInfo> list = PrizeInfoService.selectPrizeInfoList(prizeInfo);
			return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, list);
		} catch (Exception e) {
			return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
		}
		
	}
	
	
	
	
	/**
	 * 新增-
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存-
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseBean addSave(@RequestBody PrizeInfo prizeInfo)
	{		
		try {
			PrizeInfoService.insertPrizeInfo(prizeInfo);
			return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, prizeInfo);
		} catch (Exception e) {
			return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
		}
		
	}

	/**
	 * 获取 -依据ID
	 */
	@PostMapping("/get/{prizeRecId}")
	public ResponseBean get(@PathVariable("prizeRecId") Integer prizeRecId, ModelMap mmap)
	{
		PrizeInfo prizeInfo = PrizeInfoService.selectPrizeInfoById(prizeRecId);
	    return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, prizeInfo);
	}
	
	/**
	 * 修改保存-
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public ResponseBean editSave(@RequestBody PrizeInfo prizeInfo)
	{		
	
		try {
			PrizeInfoService.updatePrizeInfo(prizeInfo);
			return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, prizeInfo);
		} catch (Exception e) {
			return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
		}
		
	}
	
	/**
	 * 删除-
	 */
	@ResponseBody
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseBean remove(String[] prizeRecId)
	{		
		try {
			PrizeInfoService.deletePrizeInfoByIds(prizeRecId);
			return new ResponseBean(RestApi.Msg.SUCCESS, RestApi.Code.SUCCESS, "");
		} catch (Exception e) {
			return new ResponseBean(RestApi.Msg.FAIL, RestApi.Code.FAIL, "");
		}
		
	}
	
}
