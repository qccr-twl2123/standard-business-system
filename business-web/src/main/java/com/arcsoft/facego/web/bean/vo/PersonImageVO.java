package com.arcsoft.facego.web.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户图片
 * </p>
 *
 * @author mark
 * @since 2018-07-04
 */
@Data
@Accessors(chain = true)
public class PersonImageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 状态: 0无效  1有效
     */
    private Integer status;
    /**
     * 是否反向: 0否 1是
     */
    private Integer isMirror;
    /**
     * 所属用户Id
     */
    private Long personId;
    private String imageUrl;
    /**
     * 人脸在图片中位置
     */
    private String faceRect;
    /**
     * 特征
     */
    private String feature;
    /**
     * 创建人
     */
    private String createPerson;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updatePerson;
    /**
     * 修改时间
     */
    private Date updateTime;



}