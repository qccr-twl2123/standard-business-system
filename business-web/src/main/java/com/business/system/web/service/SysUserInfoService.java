/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.web.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.business.system.common.dao.SysUserInfoDao;
import com.business.system.common.entity.SysUserInfo;
import com.business.system.common.entity.SysUserInfoExample;
import com.business.system.common.entity.SysUserInfoExample.Criteria;
import com.business.system.util.Constants;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import com.business.system.util.enums.StatusIdEnum;
import com.business.system.util.enums.UserTypeEnum;
import com.business.system.web.bean.qo.SysUserInfoQO;
import com.business.system.web.bean.vo.SysUserInfoVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台用户信息
 *
 * @author wujing
 * @since 2017-12-26
 */
@Component
public class SysUserInfoService {

	@Autowired
	private SysUserInfoDao dao;


	public Page<SysUserInfoVO> listForPage(int pageCurrent, int pageSize, SysUserInfoQO qo) {
		SysUserInfoExample example = new SysUserInfoExample();
		Criteria c = example.createCriteria();
		if (StringUtils.isNotBlank(qo.getLoginName())) {
			c.andLoginNameLike(PageUtil.likeRight(qo.getLoginName()));
		}
		if (StringUtils.isNotBlank(qo.getMobile())) {
			c.andMobileLike(PageUtil.likeRight(qo.getMobile()));
		}
		if (ObjectUtil.isNotNull(qo.getUserType())) {
			c.andUserTypeEqualTo(qo.getUserType());
		}
		example.setOrderByClause(" id desc ");
		Page<SysUserInfo> page = dao.listForPage(pageCurrent, pageSize, example);
		return PageUtil.transform(page, SysUserInfoVO.class);
	}

	public int save(SysUserInfoQO qo) {
		SysUserInfo record = new SysUserInfo();
		BeanUtils.copyProperties(qo, record);
		record.setStatusId(StatusIdEnum.NORMAL.getCode()); // 默认正常状态
		record.setSalt(SecureUtil.simpleUUID());
		record.setPwd(DigestUtil.md5Hex(record.getSalt() + "roncoo"));
		Long userInfoId = dao.save(record);



		return 1;
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysUserInfoVO getById(Long id) {
		SysUserInfoVO vo = new SysUserInfoVO();
		SysUserInfo record = dao.getById(id);
		BeanUtils.copyProperties(record, vo);
		return vo;
	}

	public int updateById(SysUserInfoQO qo) {
		SysUserInfo record = new SysUserInfo();
		BeanUtils.copyProperties(qo, record);
		record.setLoginName(null); // 登录名不能修改
		return dao.updateById(record);
	}

	public Page<SysUserInfoVO> lookup(int pageCurrent, int pageSize, SysUserInfoQO qo) {
		SysUserInfoExample example = new SysUserInfoExample();
		Criteria c = example.createCriteria();
		c.andUserTypeEqualTo(UserTypeEnum.MERCHANTS.getCode());
		List<Long> listIds = getIds();
		if (!listIds.isEmpty()) {
			c.andIdNotIn(listIds); // 已经绑定的用户不再列出，防止重复
		}
		example.setOrderByClause(" id desc ");
		Page<SysUserInfo> page = dao.listForPage(pageCurrent, pageSize, example);
		return PageUtil.transform(page, SysUserInfoVO.class);
	}

	private List<Long> getIds() {
		List<Long> ids = new ArrayList<>();
		return ids;
	}

	public SysUserInfoVO getUserInfoForSeesion() {
		Long userInfoId = Long.valueOf(SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.USER_ID).toString());
		return getById(userInfoId);
	}

	public int updatePassword(String oldPwd, String newPwd, String rePwd) {
		Long userInfoId = Long.valueOf(SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.USER_ID).toString());
		SysUserInfo sysUserInfo = dao.getById(userInfoId);
		if (!newPwd.equals(rePwd)) {
			return -1;
		}

		if (sysUserInfo.getPwd().equals(DigestUtil.md5Hex(sysUserInfo.getSalt() + oldPwd))) {
			SysUserInfo user = new SysUserInfo();
			user.setSalt(SecureUtil.simpleUUID());
			user.setPwd(DigestUtil.md5Hex(sysUserInfo.getSalt() + newPwd));
			user.setId(userInfoId);
			return dao.updateById(user);
		}
		return -1;
	}

}
