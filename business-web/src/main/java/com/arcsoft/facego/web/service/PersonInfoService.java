package com.arcsoft.facego.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arcsoft.facego.web.bean.qo.PersonInfoQO;
import com.arcsoft.facego.web.bean.vo.PersonInfoVO;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.util.bjui.PageUtil;
import com.arcsoft.facego.common.dao.PersonInfoDao;
import com.arcsoft.facego.common.entity.PersonInfo;
import com.arcsoft.facego.common.entity.PersonInfoExample;
import com.arcsoft.facego.common.entity.PersonInfoExample.Criteria;

/**
 * 人员表 
 *
 * @author mark
 * @since 2018-07-04
 */
@Component
public class PersonInfoService {

	@Autowired
	private PersonInfoDao dao;

	public Page<PersonInfoVO> listForPage(int pageCurrent, int pageSize, PersonInfoQO qo) {
	    PersonInfoExample example = new PersonInfoExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
        Page<PersonInfo> page = dao.listForPage(pageCurrent, pageSize, example);
        return PageUtil.transform(page, PersonInfoVO.class);
	}

	public Long save(PersonInfoQO qo) {
	    PersonInfo record = new PersonInfo();
        BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public PersonInfoVO getById(Long id) {
	    PersonInfoVO vo = new PersonInfoVO();
	    PersonInfo record = dao.getById(id);
        BeanUtils.copyProperties(record, vo);



		return vo;
	}

	public int updateById(PersonInfoQO qo) {
	    PersonInfo record = new PersonInfo();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}
	
}
