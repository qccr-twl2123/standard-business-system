/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.web.service;

import com.business.system.common.dao.SysRoleDao;
import com.business.system.common.entity.SysRole;
import com.business.system.common.entity.SysRoleExample;
import com.business.system.common.entity.SysRoleExample.Criteria;
import com.business.system.util.ArrayListUtil;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import com.business.system.web.bean.qo.SysRoleQO;
import com.business.system.web.bean.qo.SysRoleUserQO;
import com.business.system.web.bean.vo.SysRoleUserVO;
import com.business.system.web.bean.vo.SysRoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 角色信息
 *
 * @author wujing
 * @since 2017-12-26
 */
@Component
public class SysRoleService {

	@Autowired
	private SysRoleDao dao;

	public Page<SysRoleVO> listForPage(int pageCurrent, int pageSize, SysRoleQO qo) {
		SysRoleExample example = new SysRoleExample();
		Criteria c = example.createCriteria();
		example.setOrderByClause(" id desc ");
		Page<SysRole> page = dao.listForPage(pageCurrent, pageSize, example);
		return PageUtil.transform(page, SysRoleVO.class);
	}

	public int save(SysRoleQO qo) {
		SysRole record = new SysRole();
		BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysRoleVO getById(Long id) {
		SysRoleVO vo = new SysRoleVO();
		SysRole record = dao.getById(id);
		BeanUtils.copyProperties(record, vo);
		return vo;
	}

	public int updateById(SysRoleQO qo) {
		SysRole record = new SysRole();
		BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}

	public List<SysRoleVO> listRoleForUserInfo(SysRoleUserQO qo, List<SysRoleUserVO> list, Long userInfoId) {
		SysRoleExample example = new SysRoleExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(qo.getRoleName())) {
			c.andRoleNameLike(PageUtil.likeRight(qo.getRoleName()));
		}
		List<SysRole> sysRolelist = dao.listByExample(example);
		List<SysRoleVO> sysRoleVOlist = ArrayListUtil.copy(sysRolelist, SysRoleVO.class);
		for (SysRoleVO roleVO : sysRoleVOlist) {
			Integer isShow = 0;
			for (SysRoleUserVO roleUserVo : list) {
				if (roleVO.getId().equals(roleUserVo.getRoleId())) {
					isShow = 1;
					break;
				}
			}
			roleVO.setIsShow(isShow);
		}
		return sysRoleVOlist;
	}

}
