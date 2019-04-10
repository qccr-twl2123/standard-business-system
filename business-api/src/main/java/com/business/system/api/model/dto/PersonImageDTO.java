package com.business.system.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonImageDTO {
    private Long  personId;
    private String imageUrl;
    private byte[] feature;
}
