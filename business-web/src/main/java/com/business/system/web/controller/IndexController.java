/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.web.controller;

import com.business.system.util.Constants;
import com.business.system.util.JSONUtil;
import com.business.system.util.exception.BaseException;
import com.business.system.web.bean.vo.SysMenuVO;
import com.business.system.web.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping("/index")
	public void index(ModelMap modelMap) {
		Long userInfoId = Long.valueOf(SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.USER_ID).toString());
		List<SysMenuVO> menuVOList = sysMenuService.listMenuByUserInfoIdAndPlatformAppId(userInfoId, "RC9560472864454143847a270bd4436532");
		if (menuVOList == null || menuVOList.isEmpty()) {
			throw new BaseException("没权限");
		}
		modelMap.put("menuList", menuVOList);
		SecurityUtils.getSubject().getSession(false).setAttribute(Constants.Session.MENU, JSONUtil.toJSONString(menuVOList));
	}

	@RequestMapping("/context")
	public void context(ModelMap modelMap) {
		modelMap.put("javaVersion", System.getProperty("java.version"));
		modelMap.put("osName", System.getProperty("os.name"));
	}




}
