package com.business.system.web.controller.admin;

import com.business.system.common.entity.CommonMessage;
import com.business.system.util.bjui.Bjui;
import com.business.system.util.bjui.Page;
import com.business.system.web.bean.vo.DepartmentVO;
import com.business.system.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.system.web.service.DepartmentService;
import com.business.system.web.bean.qo.DepartmentQO;

import javax.validation.Valid;
import java.util.List;

/**
 * 部门信息
 *
 * @author mark
 * @since 2018-07-04
 */
@Controller
@RequestMapping(value = "/admin/department")
public class DepartmentController extends BaseController {

	private final static String TARGETID = "admin-department";

	@Autowired
	private DepartmentService service;

	@RequestMapping("/index")
	public String index(){
		return "/admin/department/index";
	}


	@ResponseBody
	@RequestMapping(value = "/list")
	public Page<DepartmentVO> list( DepartmentQO qo){
		Page<DepartmentVO> dataList = service.listForPage(1,9999,qo);
		dataList.setStatusCode(Bjui.OK);
		dataList.setMessage(CommonMessage.QUERY_SUCCESS);
		return  dataList;
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public Bjui save(@Valid DepartmentQO qo, BindingResult result){
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
		if (service.save(qo) > 0) {
			return successObject(TARGETID);
		}
		return errorObject(CommonMessage.ADD_SUCCESS);
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public Bjui delete(@RequestParam(value = "id") Long id){
		if (service.deleteById(id) > 0) {
			return deleteObject(TARGETID);
		}
		return errorObject(CommonMessage.DELETE_FAIL);
	}


	@ResponseBody
	@RequestMapping(value = "/update")
	public Bjui update(@Valid DepartmentQO qo, BindingResult result){
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
		if (service.updateById(qo) > 0) {
			return successObject(TARGETID);
		}
		return errorObject(CommonMessage.UPDATE_FAIL);
	}

}
