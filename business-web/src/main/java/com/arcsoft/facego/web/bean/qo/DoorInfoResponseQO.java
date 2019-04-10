package com.arcsoft.facego.web.bean.qo;

import com.arcsoft.facego.common.entity.DoorInfo;

public class DoorInfoResponseQO extends DoorInfo {
    private int statusCode;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
