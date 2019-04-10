package com.arcsoft.facego.web.controller.admin;

import com.arcsoft.facego.common.entity.CommonMessage;
import com.arcsoft.facego.common.entity.DoorInfo;
import com.arcsoft.facego.util.bjui.Bjui;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.web.bean.qo.DoorInfoResponseQO;
import com.arcsoft.facego.web.bean.vo.DoorInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.arcsoft.facego.web.service.DoorInfoService;
import com.arcsoft.facego.web.bean.qo.DoorInfoQO;
import com.arcsoft.facego.util.base.BaseController;

import javax.validation.Valid;
import java.util.List;

/**
 *  
 *
 * @author mark
 * @since 2018-07-03
 */
@Controller
@RequestMapping(value = "/admin/doorInfo")
public class DoorInfoController extends BaseController {

	private final static String TARGETID = "admin-doorInfo";

	@Autowired
	private DoorInfoService service;

	@RequestMapping("/index")
	public String index(){
		return "/admin/doorInfo/index";
	}


	@ResponseBody
	@RequestMapping(value = "/list")
	public Page<DoorInfoVO> list(){
		Page<DoorInfoVO> dataList = service.listForPage(1,9999,null);
		dataList.setStatusCode(Bjui.OK);
		dataList.setMessage(CommonMessage.QUERY_SUCCESS);
		return  dataList;
	}

	
	@ResponseBody
	@RequestMapping(value = "/save")
	public Bjui save(@Valid DoorInfoQO qo, BindingResult result){
		if(result.hasErrors()) {

			List<ObjectError> list = result.getAllErrors();
			StringBuilder errorMessageList = new StringBuilder();
			for (ObjectError error : list) {
				errorMessageList.append(error.getDefaultMessage()+",");
			}
			if(errorMessageList.length() > 0) {
				return errorObject("验证错误:" + errorMessageList.subSequence(0,errorMessageList.length()-1).toString());
			}
		}

		DoorInfo record = new DoorInfo();
		BeanUtils.copyProperties(qo, record);

		DoorInfo doorTemp =service.selectByDoorCode(record.getCode());
		if(doorTemp != null && doorTemp.getId() > 0){
			return errorObject(CommonMessage.ADD_FAIL_REPEAT);
		}
		int successCode = service.save(qo);
		if (successCode > 0) {
			return successObject(TARGETID);
		}
		return errorObject(CommonMessage.ADD_FAIL);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Bjui delete(@RequestParam(value = "id") Long id){
		if (service.deleteById(id) > 0) {
			return deleteObject(TARGETID);
		}
		return errorObject(CommonMessage.DELETE_FAIL);
	}
	
	@RequestMapping(value = "/edit")
	public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap){
		modelMap.put("bean", service.getById(id));
	}
	
	@ResponseBody
	@RequestMapping(value = "/update")
	public DoorInfoResponseQO update(@Valid DoorInfoQO qo, BindingResult result){
		DoorInfoResponseQO responseDoor =new DoorInfoResponseQO();
		if(result.hasErrors()) {

			List<ObjectError> list = result.getAllErrors();
			StringBuilder errorMessageList = new StringBuilder();
			for (ObjectError error : list) {
				errorMessageList.append(error.getDefaultMessage()+",");
			}
			if(errorMessageList.length() > 0) {
				responseDoor.setMessage("验证错误:" + errorMessageList.subSequence(0,errorMessageList.length()-1).toString());
				responseDoor.setStatusCode(Bjui.ER);
				return  responseDoor;
			}
		}

		DoorInfo record = new DoorInfo();
		BeanUtils.copyProperties(qo, record);

		DoorInfo doorTemp =service.getById(record.getId());
		if(doorTemp == null || doorTemp.getId() < 1){
			responseDoor.setMessage(CommonMessage.UPDATE_FAIL_EMPTY);
			responseDoor.setStatusCode(Bjui.ER);
			return  responseDoor;
		}

		if (service.updateById(qo) > 0) {
			responseDoor.setMessage(CommonMessage.UPDATE_SUCCESS);
			responseDoor.setStatusCode(Bjui.OK);
			responseDoor.setCode(doorTemp.getCode());
			responseDoor.setName(record.getName());
			responseDoor.setId(record.getId());
			return responseDoor;
		}
		responseDoor.setMessage(CommonMessage.UPDATE_FAIL);
		responseDoor.setStatusCode(Bjui.ER);
		return  responseDoor;
	}
}
