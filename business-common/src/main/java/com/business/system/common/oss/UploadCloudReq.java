package com.business.system.common.oss;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UploadCloudReq {

    @NotNull(message = "上传文件不能为空")
    private MultipartFile file;
    @Range(min=1, max=3, message = "类型错误")
    private Integer type;
}
