package com.business.system.web.service;

import cn.hutool.core.collection.CollectionUtil;
import com.business.system.common.dao.DoorInfoDao;
import com.business.system.common.entity.DoorInfo;
import com.business.system.common.entity.DoorInfoExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.system.web.bean.qo.DoorInfoQO;
import com.business.system.web.bean.vo.DoorInfoVO;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;

import java.util.List;

/**
 *
 *
 * @author mark
 * @since 2018-07-03
 */
@Component
public class DoorInfoService {

	@Autowired
	private DoorInfoDao dao;

	public Page<DoorInfoVO> listForPage(int pageCurrent, int pageSize, DoorInfoQO qo) {
	    DoorInfoExample example = new DoorInfoExample();
	    DoorInfoExample.Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
        Page<DoorInfo> page = dao.listForPage(pageCurrent, pageSize, example);
        return PageUtil.transform(page, DoorInfoVO.class);
	}

	public int save(DoorInfoQO qo) {
	    DoorInfo record = new DoorInfo();
        BeanUtils.copyProperties(qo, record);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public DoorInfo getById(Long id) {
	    DoorInfo record = dao.getById(id);
		return record;
	}

	public DoorInfo selectByDoorCode(String doorCode) {
		DoorInfoExample doorInfoExample = new DoorInfoExample();
		doorInfoExample.createCriteria().andCodeEqualTo(doorCode);
		List<DoorInfo> doorInfoList = dao.listByExample(doorInfoExample);
		if(CollectionUtil.isNotEmpty(doorInfoList)){
			return  doorInfoList.get(0);
		}
		return  null;
	}

	public int updateById(DoorInfoQO qo) {
	    DoorInfo record = new DoorInfo();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}
}
