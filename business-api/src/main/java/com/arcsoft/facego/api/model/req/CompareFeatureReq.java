package com.arcsoft.facego.api.model.req;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class CompareFeatureReq {

    @NotNull(message = "人脸特征数据不能为空")
    private MultipartFile faceFile;
    private Integer topN;
    private Double similar;
    private String doorCode;
}
