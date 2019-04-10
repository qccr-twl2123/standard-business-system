package com.business.system.web.bean.qo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author mark
 * @since 2019-04-10
 */
@Data
@Accessors(chain = true)
public class BrandQO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 品牌首字母
     */
    private String letter;
    /**
     * 品牌中文名称
     */
    private String chineseText;
    /**
     * 品牌英文名称
     */
    private String englishText;
    /**
     * 入驻时间
     */
    private Date enterTime;
    /**
     * 品牌类型: 1综合 2家电
     */
    private Integer brandType;
    /**
     * 品牌起源地
     */
    private String origin;
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
    /**
     * 图片数据
     */
    private MultipartFile imageFile;

    /**
     * 品牌logo
     */
    private String logo;
}
