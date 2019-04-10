package com.business.system.facetech.model.response;

import com.business.system.facetech.model.Rect;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DetectRes implements Serializable {
    /**人脸个数*/
    public int nface;
    /**人脸的坐标list*/
    public Rect[] rcface;

    public int errorcode;
    public String errormsg;

}
