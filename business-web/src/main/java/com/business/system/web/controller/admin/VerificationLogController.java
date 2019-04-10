package com.business.system.web.controller.admin;

import com.business.system.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.business.system.web.service.VerificationLogService;
import com.business.system.web.bean.qo.VerificationLogQO;

/**
 * 刷脸记录表
 *
 * @author mark
 * @since 2018-07-04
 */
@Controller
@RequestMapping(value = "/admin/verificationLog")
public class VerificationLogController extends BaseController {

	private final static String TARGETID = "admin-verificationLog";

	@Autowired
	private VerificationLogService service;

	@RequestMapping(value = "/list")
	public void list(@RequestParam(value = "page", defaultValue = "1") int pageCurrent, @RequestParam(value = "pageSize", defaultValue = "20") int pageSize, @ModelAttribute VerificationLogQO qo, ModelMap modelMap){
		modelMap.put("page", service.listForPage(pageCurrent, pageSize, qo));
		modelMap.put("pageCurrent", pageCurrent);
		modelMap.put("pageSize", pageSize);
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id){
	}

}
