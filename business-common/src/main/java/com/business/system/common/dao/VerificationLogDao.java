package com.business.system.common.dao;

import com.business.system.common.entity.VerificationLog;
import com.business.system.common.entity.VerificationLogExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface VerificationLogDao {
    int save(VerificationLog record);

    int deleteById(Long id);

    int updateById(VerificationLog record);

    VerificationLog getById(Long id);

    List<VerificationLog> listByExample(VerificationLogExample example);

    Page<VerificationLog> listForPage(int pageCurrent, int pageSize, VerificationLogExample example);
}
