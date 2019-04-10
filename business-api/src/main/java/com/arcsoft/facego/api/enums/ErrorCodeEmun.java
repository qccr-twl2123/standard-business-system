package com.arcsoft.facego.api.enums;

/**
 * 常见错误枚举
 *
 * @author xierongli
 * @version $$Id: project-system, v 0.1 2018/6/11 上午10:54 mark1xie Exp $$
 */
public enum ErrorCodeEmun {

    PARAM_ERROR(-100,"参数错误"),
    TOKEN_NOT_EMPTY(-101,"token不能为空"),
    TOKEN_NOT_LEGAL(-102,"token不合法");

    private Integer code;
    private String desc;

    ErrorCodeEmun(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
