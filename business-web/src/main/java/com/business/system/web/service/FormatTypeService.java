package com.business.system.web.service;

import cn.hutool.core.collection.CollectionUtil;
import com.business.system.common.dao.FormatTypeDetailDao;
import com.business.system.common.entity.FormatTypeDetail;
import com.business.system.web.bean.qo.FormatTypeDetailQO;
import com.business.system.web.bean.vo.FormatTypeDetailVO;
import org.apache.commons.lang.StringUtils;
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

import java.util.List;
import java.util.stream.Collectors;

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
	@Autowired
	private FormatTypeDetailService formatTypeDetailService;

	public Page<FormatTypeVO> listForPage(int pageCurrent, int pageSize, FormatTypeQO qo) {
	    FormatTypeExample example = new FormatTypeExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
	    if(StringUtils.isNotBlank(qo.getName())){
	    	c.andNameLike(PageUtil.like(qo.getName()));
		}
        Page<FormatType> page = dao.listForPage(pageCurrent, pageSize, example);
		Page<FormatTypeVO> formatTypeVOPage = PageUtil.transform(page, FormatTypeVO.class);
	    if(formatTypeVOPage != null && CollectionUtil.isNotEmpty(formatTypeVOPage.getList())){
			formatTypeVOPage.getList().forEach(formatType -> {
				List<FormatTypeDetailVO> formatTypeDetailVOList = formatTypeDetailService.queryForList(FormatTypeDetailQO.builder().formatTypeId(formatType.getId()).build());
				if(CollectionUtil.isNotEmpty(formatTypeDetailVOList)){
					formatType.setFormatTypeValue(formatTypeDetailVOList.stream().map(s->s.getDetailName()).collect(Collectors.joining(",")));
				}
			});
		}

        return formatTypeVOPage;
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
        vo.setShowImgText(vo.getShowImg() == 0?"文本":"图片");
		return vo;
	}

	public int updateById(FormatTypeQO qo) {
	    FormatType record = new FormatType();
        BeanUtils.copyProperties(qo, record);
		return dao.updateById(record);
	}

}
