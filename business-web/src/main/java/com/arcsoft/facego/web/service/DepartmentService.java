package com.arcsoft.facego.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arcsoft.facego.web.bean.qo.DepartmentQO;
import com.arcsoft.facego.web.bean.vo.DepartmentVO;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.util.bjui.PageUtil;
import com.arcsoft.facego.common.dao.DepartmentDao;
import com.arcsoft.facego.common.entity.Department;
import com.arcsoft.facego.common.entity.DepartmentExample;
import com.arcsoft.facego.common.entity.DepartmentExample.Criteria;

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
	    Criteria c = example.createCriteria();
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
