/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.web.service;

import com.business.system.common.dao.SysRoleDao;
import com.business.system.common.dao.SysRoleUserDao;
import com.business.system.common.entity.SysRole;
import com.business.system.common.entity.SysRoleUser;
import com.business.system.util.ArrayListUtil;
import com.business.system.web.bean.qo.SysRoleUserQO;
import com.business.system.web.bean.vo.SysRoleUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 角色用户关联表
 *
 * @author wujing
 * @since 2017-12-26
 */
@Component
public class SysRoleUserService {

	@Autowired
	private SysRoleUserDao dao;

	@Autowired
	private SysRoleDao sysRoleDao;

	public int save(SysRoleUserQO qo, String ids) {
		if (StringUtils.hasText(ids)) {
			// 先删除旧的，再添加新的
			dao.deleteByUserInfoId(qo.getUserInfoId());
			// 拆分角色和平台拼接ID
			String[] roleIds = ids.split(",");
			for (String roleId : roleIds) {
				SysRoleUser sysRoleUser = new SysRoleUser();
				sysRoleUser.setRoleId(Long.parseLong(roleId));
				sysRoleUser.setPlatformId(getPlatformIdByRoleId(Long.parseLong(roleId)));
				sysRoleUser.setUserInfoId(qo.getUserInfoId());
				dao.save(sysRoleUser);
			}
		}
		return 1;
	}

	private Long getPlatformIdByRoleId(Long id) {
		SysRole bean = sysRoleDao.getById(id);
		return bean.getPlatformId();
	}

	public List<SysRoleUserVO> listByUserInfoId(Long userInfoId) {
		List<SysRoleUser> list = dao.listByUserInfoId(userInfoId);
		return ArrayListUtil.copy(list, SysRoleUserVO.class);
	}

}
