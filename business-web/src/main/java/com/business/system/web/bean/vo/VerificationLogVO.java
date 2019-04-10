package com.business.system.web.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 刷脸记录表
 * </p>
 *
 * @author mark
 * @since 2018-07-04
 */
@Data
@Accessors(chain = true)
public class VerificationLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 所属用户Id
     */
    private Long personId;
    /**
     * 姓名
     */
    private String name;
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
