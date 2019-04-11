package com.business.system.web.bean.qo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 规格类型表
 * </p>
 *
 * @author mark
 * @since 2019-04-11
 */
@Data
@Accessors(chain = true)
public class FormatTypeQO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 规格项名称
     */
    private String name;
    /**
     * 是否展示为图片模式
     */
    private Integer showImg;
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
