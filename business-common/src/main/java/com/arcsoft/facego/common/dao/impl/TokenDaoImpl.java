package com.arcsoft.facego.common.dao.impl;

import com.arcsoft.facego.common.dao.TokenDao;
import com.arcsoft.facego.common.dao.impl.mapper.TokenMapper;
import com.arcsoft.facego.common.entity.Token;
import com.arcsoft.facego.common.entity.TokenExample;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.util.bjui.PageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDaoImpl implements TokenDao {
    @Autowired
    private TokenMapper tokenMapper;

    public int save(Token record) {
        return this.tokenMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.tokenMapper.deleteByPrimaryKey(id);
    }

    public int updateById(Token record) {
        return this.tokenMapper.updateByPrimaryKeySelective(record);
    }

    public List<Token> listByExample(TokenExample example) {
        return this.tokenMapper.selectByExample(example);
    }

    public Token getById(Long id) {
        return this.tokenMapper.selectByPrimaryKey(id);
    }

    public Page<Token> listForPage(int pageCurrent, int pageSize, TokenExample example) {
        int count = this.tokenMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Token>(count, totalPage, pageCurrent, pageSize, this.tokenMapper.selectByExample(example));
    }
}