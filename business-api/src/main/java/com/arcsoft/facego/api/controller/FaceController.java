package com.arcsoft.facego.api.controller;

import com.arcsoft.facego.api.model.req.CompareFeatureReq;
import com.arcsoft.facego.api.service.FaceService;
import com.arcsoft.facego.api.service.FileService;
import com.arcsoft.facego.api.validator.Assert;
import com.arcsoft.facego.api.validator.ValidatorUtils;
import com.arcsoft.facego.util.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "1.2", description = "人脸检测", value = "人脸检测")
public class FaceController extends BaseController {

    @Autowired
    private FaceService faceService;

    @PostMapping("compareFeature")
    @ApiOperation(value = "比对人脸特征",notes = "比对人脸特征")
    @ApiImplicitParams({

    })
    public void compareFeature(CompareFeatureReq compareFeatureReq){
        logger.info("compareFeatureReq:{}",compareFeatureReq);
        ValidatorUtils.validateEntity(compareFeatureReq);
        faceService.compareFeature(compareFeatureReq);
    }




}
