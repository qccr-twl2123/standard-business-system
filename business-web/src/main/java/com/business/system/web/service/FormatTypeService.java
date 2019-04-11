package com.business.system.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.system.web.bean.qo.FormatTypeQO;
import com.business.system.web.bean.vo.FormatTypeVO;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import com.business.system.common.dao.FormatTypeDao;
import com.business.system.common.entity.FormatType;
import com.business.system.common.entity.FormatTypeExample;
import com.business.system.common.entity.FormatTypeExample.Criteria;

/**
 * 规格类型表 
 *
 * @author mark
 * @since 2019-04-11
 */
@Component
public class FormatTypeService {

	@Autowired
	private FormatTypeDao dao;

	public Page<FormatTypeVO> listForPage(int pageCurrent, int pageSize, FormatTypeQO qo) {
	    FormatTypeExample example = new FormatTypeExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
        Page<FormatType> page = dao.listForPage(pageCurrent, pageSize, example);
        return PageUtil.transform(page, FormatTypeVO.class);
	}

	public int save(FormatTypeQO qo) {
	    FormatType record = new FormatType();
        BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public FormatTypeVO getById(Long id) {
	    FormatTypeVO vo = new FormatTypeVO();
	    FormatType record = dao.getById(id);
        BeanUtils.copyProperties(record, vo);
		return vo;
	}

	public int updateById(FormatTypeQO qo) {
	    FormatType record = new FormatType();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}
	
}
