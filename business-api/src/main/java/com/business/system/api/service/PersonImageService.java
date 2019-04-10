package com.business.system.api.service;

import com.business.system.common.dao.PersonImageDao;
import com.business.system.common.entity.PersonImage;
import com.business.system.common.entity.PersonImageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonImageService {
    @Autowired
    private PersonImageDao personImageDao;

    public List<PersonImage> queryForList(){
        PersonImageExample personImageExample = new PersonImageExample();
        personImageExample.createCriteria();
        return  personImageDao.listByExample(personImageExample);
    }
}
