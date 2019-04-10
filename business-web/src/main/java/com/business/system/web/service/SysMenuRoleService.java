/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.web.service;

import com.business.system.common.dao.SysMenuRoleDao;
import com.business.system.common.entity.SysMenuRole;
import com.business.system.util.ArrayListUtil;
import com.business.system.web.bean.qo.SysMenuRoleQO;
import com.business.system.web.bean.vo.SysMenuRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 菜单角色关联表
 *
 * @author wujing
 * @since 2017-12-26
 */
@Component
public class SysMenuRoleService {

	@Autowired
	private SysMenuRoleDao dao;

	public List<SysMenuRoleVO> listByRoleId(Long roleId) {
		List<SysMenuRole> list = dao.listByRoleId(roleId);
		return ArrayListUtil.copy(list, SysMenuRoleVO.class);
	}

	public int save(SysMenuRoleQO qo, String ids) {
		if (StringUtils.hasText(ids)) {
			dao.deleteByRoleId(qo.getRoleId());
			String[] menuIds = ids.split(",");
			for (String id : menuIds) {
				SysMenuRole entity = new SysMenuRole();
				entity.setMenuId(Long.valueOf(id));
				entity.setRoleId(qo.getRoleId());
				entity.setPlatformId(qo.getPlatformId());
				dao.save(entity);
			}
		}
		return 1;
	}

}
