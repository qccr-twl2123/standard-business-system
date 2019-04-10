package com.business.system.web.service;

import com.business.system.common.dao.PersonInfoDao;
import com.business.system.common.entity.PersonInfo;
import com.business.system.common.entity.PersonInfoExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.system.web.bean.qo.PersonInfoQO;
import com.business.system.web.bean.vo.PersonInfoVO;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;

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
	    PersonInfoExample.Criteria c = example.createCriteria();
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
