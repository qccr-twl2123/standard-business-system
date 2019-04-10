package com.arcsoft.facego.api.service;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.arcsoft.facego.api.cache.LocalCache;
import com.arcsoft.facego.api.model.dto.PersonImageDTO;
import com.arcsoft.facego.api.model.req.CompareFeatureReq;
import com.arcsoft.facego.common.dao.PersonImageDao;
import com.arcsoft.facego.common.entity.PersonImage;
import com.arcsoft.facego.common.entity.PersonImageExample;
import com.arcsoft.facego.util.ObjectConvert;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FaceService {
    private final static  String PERSON_IMAGE_KEY = "person_image_key";

    @Autowired
    private PersonImageDao personImageDao;

    public void compareFeature(CompareFeatureReq compareFeatureReq){
        List<PersonImageDTO> personImageDTOList = getFromCache();
        if(CollectionUtil.isNotEmpty(personImageDTOList)){

        }
    }

    public List<PersonImageDTO> getFromCache(){
        List<PersonImageDTO> personImageDTOList = Lists.newArrayList();
        String personImageJson = LocalCache.get(PERSON_IMAGE_KEY);
        if(StringUtils.isBlank(personImageJson)){
            PersonImageExample personImageExample = new PersonImageExample();
            personImageExample.createCriteria();
            List<PersonImage> personImagesList = personImageDao.listByExample(personImageExample);
            if(CollectionUtil.isNotEmpty(personImagesList)){
                personImageDTOList = ObjectConvert.convertList(personImagesList,PersonImageDTO.class);
                LocalCache.put(PERSON_IMAGE_KEY, personImageDTOList);
            }
        }else{
            personImageDTOList = JSON.parseArray(personImageJson, PersonImageDTO.class);
        }
        return  personImageDTOList;
    }



}
