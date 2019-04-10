package com.business.system.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.system.web.bean.qo.BrandQO;
import com.business.system.web.bean.vo.BrandVO;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import com.business.system.common.dao.BrandDao;
import com.business.system.common.entity.Brand;
import com.business.system.common.entity.BrandExample;
import com.business.system.common.entity.BrandExample.Criteria;

/**
 * 品牌表 
 *
 * @author mark
 * @since 2019-04-10
 */
@Component
public class BrandService {

	@Autowired
	private BrandDao dao;

	public Page<BrandVO> listForPage(int pageCurrent, int pageSize, BrandQO qo) {
	    BrandExample example = new BrandExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
        Page<Brand> page = dao.listForPage(pageCurrent, pageSize, example);
        return PageUtil.transform(page, BrandVO.class);
	}

	public int save(BrandQO qo) {
	    Brand record = new Brand();
        BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public BrandVO getById(Long id) {
	    BrandVO vo = new BrandVO();
	    Brand record = dao.getById(id);
        BeanUtils.copyProperties(record, vo);
		return vo;
	}

	public int updateById(BrandQO qo) {
	    Brand record = new Brand();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}
	
}
