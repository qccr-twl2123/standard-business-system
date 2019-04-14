package com.business.system.web.controller.admin;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.business.system.util.enums.UserTypeEnum;
import com.business.system.web.bean.qo.FormatTypeDetailQO;
import com.business.system.web.bean.vo.FormatTypeDetailVO;
import com.business.system.web.service.FormatTypeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.system.web.service.FormatTypeService;
import com.business.system.web.bean.qo.FormatTypeQO;
import com.business.system.util.base.BaseController;

import java.util.List;

/**
 * 规格类型表
 *
 * @author mark
 * @since 2019-04-11
 */
@Controller
@RequestMapping(value = "/admin/formatType")
public class FormatTypeController extends BaseController {

	private final static String TARGETID = "admin-formatType";

	@Autowired
	private FormatTypeService service;
	@Autowired
	private FormatTypeDetailService formatTypeDetailService;

	@RequestMapping(value = "/list")
	public void list(@RequestParam(value = "pageCurrent", defaultValue = "1") int pageCurrent, @RequestParam(value = "pageSize", defaultValue = "20") int pageSize, @ModelAttribute FormatTypeQO qo, ModelMap modelMap){
		modelMap.put("page", service.listForPage(pageCurrent, pageSize, qo));
		modelMap.put("pageCurrent", pageCurrent);
		modelMap.put("pageSize", pageSize);
		modelMap.put("bean", qo);
	}


	@RequestMapping(value = "/formatTypeDetail")
	public void formatTypeDetail(@RequestParam(value = "pageCurrent", defaultValue = "1") int pageCurrent, @RequestParam(value = "pageSize", defaultValue = "20") int pageSize, @ModelAttribute FormatTypeDetailQO qo, ModelMap modelMap){
		modelMap.put("page",  formatTypeDetailService.listForPage(pageCurrent, pageSize, qo));
		modelMap.put("pageCurrent", pageCurrent);
		modelMap.put("pageSize", pageSize);
		modelMap.put("bean", qo);
	}


	@RequestMapping(value = "/add")
	public void add(){

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute FormatTypeQO qo){
		if (service.save(qo) > 0) {
			return success(TARGETID);
		}
		return error("添加失败");
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id") Long id){
		if (service.deleteById(id) > 0) {
			return delete(TARGETID);
		}
		return error("删除失败");
	}

	@RequestMapping(value = "/edit")
	public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap){
		modelMap.put("bean", service.getById(id));
	}

	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute FormatTypeQO qo){
		if (service.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@ResponseBody
	@RequestMapping(value = "/saveFormatTypeDetail")
	public String saveFormatTypeDetail(@ModelAttribute FormatTypeDetailQO qo){
		if(formatTypeDetailService.save(qo) > 0){
			return success(TARGETID);
		}
		return error("添加失败");
	}

	@RequestMapping(value = "addFormatTypeDetail")
	public void addFormatTypeDetail(@ModelAttribute FormatTypeDetailQO qo, ModelMap modelMap){
		modelMap.put("bean", qo);

	}

	@RequestMapping(value = "formatDetailAlias")
	public void formatDetailAlias(@ModelAttribute FormatTypeDetailQO qo, ModelMap modelMap){

	}


	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap){
		modelMap.put("bean", service.getById(id));
		//遍历所有规格值
		List<FormatTypeDetailVO> formatTypeDetailList =  formatTypeDetailService.queryForList(FormatTypeDetailQO.builder().formatTypeId(id).build());
		if(CollectionUtil.isNotEmpty(formatTypeDetailList)){
			modelMap.put("formatTypeDetailList", formatTypeDetailList);
		}
	}

	@ResponseBody
	@RequestMapping(value = "getFormatTypeDetail")
	public String getFormatTypeDetail(@RequestParam(value = "id")Long id){
		List<FormatTypeDetailVO> formatTypeDetailVOS = formatTypeDetailService.queryForList(FormatTypeDetailQO.builder().formatTypeId(id).build());
		if(CollectionUtil.isNotEmpty(formatTypeDetailVOS)){
			return JSON.toJSONString(formatTypeDetailVOS);
		}
		return "";
	}


	@ModelAttribute
	public void enums(ModelMap modelMap) {
		modelMap.put("userTypeEnums", UserTypeEnum.values());
	}
}
