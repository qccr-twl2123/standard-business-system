package com.arcsoft.facego.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum YesOrNoEnum {

    YES(1,"yes","有效","外包"),
    NO(0,"no","无效","内部");
    private Integer code;
    private String description;
    private String statusText;
    private String companyType;

    public void setCode(Integer code) {
        this.code = code;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
}
