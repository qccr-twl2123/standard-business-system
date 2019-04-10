package com.arcsoft.facego.common.dao;

import com.arcsoft.facego.common.entity.VerificationLog;
import com.arcsoft.facego.common.entity.VerificationLogExample;
import com.arcsoft.facego.util.bjui.Page;
import java.util.List;

public interface VerificationLogDao {
    int save(VerificationLog record);

    int deleteById(Long id);

    int updateById(VerificationLog record);

    VerificationLog getById(Long id);

    List<VerificationLog> listByExample(VerificationLogExample example);

    Page<VerificationLog> listForPage(int pageCurrent, int pageSize, VerificationLogExample example);
}