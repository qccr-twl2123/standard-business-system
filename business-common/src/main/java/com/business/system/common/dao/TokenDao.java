package com.business.system.common.dao;

import com.business.system.common.entity.Token;
import com.business.system.common.entity.TokenExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface TokenDao {
    int save(Token record);

    int deleteById(Long id);

    int updateById(Token record);

    Token getById(Long id);

    List<Token> listByExample(TokenExample example);

    Page<Token> listForPage(int pageCurrent, int pageSize, TokenExample example);
}
