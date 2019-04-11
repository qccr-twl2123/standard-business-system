package com.business.system.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.system.web.bean.qo.FormatTypeDetailQO;
import com.business.system.web.bean.vo.FormatTypeDetailVO;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import com.business.system.common.dao.FormatTypeDetailDao;
import com.business.system.common.entity.FormatTypeDetail;
import com.business.system.common.entity.FormatTypeDetailExample;
import com.business.system.common.entity.FormatTypeDetailExample.Criteria;

/**
 * 规格明细表 
 *
 * @author mark
 * @since 2019-04-11
 */
@Component
public class FormatTypeDetailService {

	@Autowired
	private FormatTypeDetailDao dao;

	public Page<FormatTypeDetailVO> listForPage(int pageCurrent, int pageSize, FormatTypeDetailQO qo) {
	    FormatTypeDetailExample example = new FormatTypeDetailExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
        Page<FormatTypeDetail> page = dao.listForPage(pageCurrent, pageSize, example);
        return PageUtil.transform(page, FormatTypeDetailVO.class);
	}

	public int save(FormatTypeDetailQO qo) {
	    FormatTypeDetail record = new FormatTypeDetail();
        BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public FormatTypeDetailVO getById(Long id) {
	    FormatTypeDetailVO vo = new FormatTypeDetailVO();
	    FormatTypeDetail record = dao.getById(id);
        BeanUtils.copyProperties(record, vo);
		return vo;
	}

	public int updateById(FormatTypeDetailQO qo) {
	    FormatTypeDetail record = new FormatTypeDetail();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}
	
}
