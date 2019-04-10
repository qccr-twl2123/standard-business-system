package com.arcsoft.facego.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arcsoft.facego.web.bean.qo.PersonImageQO;
import com.arcsoft.facego.web.bean.vo.PersonImageVO;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.util.bjui.PageUtil;
import com.arcsoft.facego.common.dao.PersonImageDao;
import com.arcsoft.facego.common.entity.PersonImage;
import com.arcsoft.facego.common.entity.PersonImageExample;
import com.arcsoft.facego.common.entity.PersonImageExample.Criteria;

import java.text.SimpleDateFormat;

/**
 * 用户图片 
 *
 * @author mark
 * @since 2018-07-04
 */
@Component
public class PersonImageService {

	@Autowired
	private PersonImageDao dao;

	public Page<PersonImageVO> listForPage(int pageCurrent, int pageSize, PersonImageQO qo) {
	    PersonImageExample example = new PersonImageExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
	    if(qo.getPersonId() > 0){
			example.createCriteria().andPersonIdEqualTo(qo.getPersonId());
		}
		if(qo.getStatus() == 0 || qo.getStatus() == 1){
			example.createCriteria().andStatusEqualTo(qo.getStatus());
		}
        Page<PersonImage> page = dao.listForPage(pageCurrent, pageSize, example);
        return PageUtil.transform(page, PersonImageVO.class);
	}

	public int save(PersonImageQO qo) {
	    PersonImage record = new PersonImage();
        BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public PersonImageVO getById(Long id) {
	    PersonImageVO vo = new PersonImageVO();
	    PersonImage record = dao.getById(id);
        BeanUtils.copyProperties(record, vo);
		return vo;
	}

	public int updateById(PersonImageQO qo) {
	    PersonImage record = new PersonImage();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}


	//根据人员ID修改图片状态
	public int updateImage(Long personId,Byte status) {

		return 1;
	}


	//根据人员ID删除图片
	public int deleteImage(Long personId) {
		return 1;
	}
}
