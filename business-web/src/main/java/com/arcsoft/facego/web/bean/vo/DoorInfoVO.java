package com.arcsoft.facego.web.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * <p>
 * 
 * </p>
 *
 * @author mark
 * @since 2018-07-03
 */
@Data
@Accessors(chain = true)
public class DoorInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自动增长ID
     */
    private Long id;
    /**
     * 门禁编号
     */
    @NotEmpty(message="门禁ID不能为空")
    @Length(max = 20,message="门禁ID不能长20位")
    private String code;
    /**
     * 门禁名称
     */
    @NotEmpty(message="描述不能为空")
    @Length(max = 20,message="描述不能长20位")
    private String name;
    /**
     * 备注信息
     */
    private String remark;
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
