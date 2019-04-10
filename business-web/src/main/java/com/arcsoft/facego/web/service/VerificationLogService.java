package com.arcsoft.facego.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arcsoft.facego.web.bean.qo.VerificationLogQO;
import com.arcsoft.facego.web.bean.vo.VerificationLogVO;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.util.bjui.PageUtil;
import com.arcsoft.facego.common.dao.VerificationLogDao;
import com.arcsoft.facego.common.entity.VerificationLog;
import com.arcsoft.facego.common.entity.VerificationLogExample;
import com.arcsoft.facego.common.entity.VerificationLogExample.Criteria;

/**
 * 刷脸记录表 
 *
 * @author mark
 * @since 2018-07-04
 */
@Component
public class VerificationLogService {

	@Autowired
	private VerificationLogDao dao;

	public Page<VerificationLogVO> listForPage(int pageCurrent, int pageSize, VerificationLogQO qo) {
	    VerificationLogExample example = new VerificationLogExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
        Page<VerificationLog> page = dao.listForPage(pageCurrent, pageSize, example);
        return PageUtil.transform(page, VerificationLogVO.class);
	}

	public int save(VerificationLogQO qo) {
	    VerificationLog record = new VerificationLog();
        BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public VerificationLogVO getById(Long id) {
	    VerificationLogVO vo = new VerificationLogVO();
	    VerificationLog record = dao.getById(id);
        BeanUtils.copyProperties(record, vo);
		return vo;
	}

	public int updateById(VerificationLogQO qo) {
	    VerificationLog record = new VerificationLog();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}
	
}
