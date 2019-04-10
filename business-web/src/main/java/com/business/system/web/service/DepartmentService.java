package com.business.system.web.service;

import com.business.system.common.dao.DepartmentDao;
import com.business.system.common.entity.Department;
import com.business.system.common.entity.DepartmentExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.system.web.bean.qo.DepartmentQO;
import com.business.system.web.bean.vo.DepartmentVO;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;

/**
 * 部门信息
 *
 * @author mark
 * @since 2018-07-04
 */
@Component
public class DepartmentService {

	@Autowired
	private DepartmentDao dao;

	public Page<DepartmentVO> listForPage(int pageCurrent, int pageSize, DepartmentQO qo) {
	    DepartmentExample example = new DepartmentExample();
	    DepartmentExample.Criteria c = example.createCriteria();
	    if(qo.getParentId() != null){
	    	c.andParentIdEqualTo(qo.getParentId());
		}
		if(qo.getStatus() != null){
			c.andStatusEqualTo(qo.getStatus());
		}
	    example.setOrderByClause(" id desc ");
        Page<Department> page = dao.listForPage(pageCurrent, pageSize, example);
        return PageUtil.transform(page, DepartmentVO.class);
	}

	public int save(DepartmentQO qo) {
	    Department record = new Department();
        BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public DepartmentVO getById(Long id) {
	    DepartmentVO vo = new DepartmentVO();
	    Department record = dao.getById(id);
        BeanUtils.copyProperties(record, vo);
		return vo;
	}

	public int updateById(DepartmentQO qo) {
	    Department record = new Department();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}

}
