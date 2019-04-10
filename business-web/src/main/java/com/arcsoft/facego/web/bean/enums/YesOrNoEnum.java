package com.arcsoft.facego.web.bean.enums;
public enum YesOrNoEnum {
    YES(1,"yes"),
     NO(0,"no");

    private Integer code;
    private String description;


    YesOrNoEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }


        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
}
