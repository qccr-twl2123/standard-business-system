package com.business.system.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.system.web.bean.qo.FormatDetailAliasQO;
import com.business.system.web.bean.vo.FormatDetailAliasVO;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import com.business.system.common.dao.FormatDetailAliasDao;
import com.business.system.common.entity.FormatDetailAlias;
import com.business.system.common.entity.FormatDetailAliasExample;
import com.business.system.common.entity.FormatDetailAliasExample.Criteria;

/**
 * 规格明细别名表 
 *
 * @author mark
 * @since 2019-04-11
 */
@Component
public class FormatDetailAliasService {

	@Autowired
	private FormatDetailAliasDao dao;

	public Page<FormatDetailAliasVO> listForPage(int pageCurrent, int pageSize, FormatDetailAliasQO qo) {
	    FormatDetailAliasExample example = new FormatDetailAliasExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
        Page<FormatDetailAlias> page = dao.listForPage(pageCurrent, pageSize, example);
        return PageUtil.transform(page, FormatDetailAliasVO.class);
	}

	public int save(FormatDetailAliasQO qo) {
	    FormatDetailAlias record = new FormatDetailAlias();
        BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public FormatDetailAliasVO getById(Long id) {
	    FormatDetailAliasVO vo = new FormatDetailAliasVO();
	    FormatDetailAlias record = dao.getById(id);
        BeanUtils.copyProperties(record, vo);
		return vo;
	}

	public int updateById(FormatDetailAliasQO qo) {
	    FormatDetailAlias record = new FormatDetailAlias();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}
	
}
