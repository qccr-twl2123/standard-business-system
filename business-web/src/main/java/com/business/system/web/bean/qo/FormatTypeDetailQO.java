package com.business.system.web.bean.qo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 规格明细表
 * </p>
 *
 * @author mark
 * @since 2019-04-11
 */
@Data
@Accessors(chain = true)
public class FormatTypeDetailQO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 规格明细名称
     */
    private String detailName;
    /**
     * 外键:规格明细项Id
     */
    private Long formatTypeId;
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
