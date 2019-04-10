package com.arcsoft.facego.api.service;

import com.arcsoft.facego.common.dao.PersonImageDao;
import com.arcsoft.facego.common.entity.PersonImage;
import com.arcsoft.facego.common.entity.PersonImageExample;
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
