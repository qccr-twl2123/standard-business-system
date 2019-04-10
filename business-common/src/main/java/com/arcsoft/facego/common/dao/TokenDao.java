package com.arcsoft.facego.common.dao;

import com.arcsoft.facego.common.entity.Token;
import com.arcsoft.facego.common.entity.TokenExample;
import com.arcsoft.facego.util.bjui.Page;
import java.util.List;

public interface TokenDao {
    int save(Token record);

    int deleteById(Long id);

    int updateById(Token record);

    Token getById(Long id);

    List<Token> listByExample(TokenExample example);

    Page<Token> listForPage(int pageCurrent, int pageSize, TokenExample example);
}