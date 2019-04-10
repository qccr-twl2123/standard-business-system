package com.arcsoft.facego.web.bean.qo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * <p>
 * 部门信息
 * </p>
 *
 * @author mark
 * @since 2018-07-04
 */
@Data
@Accessors(chain = true)
public class DepartmentQO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 部门名称
     */
    @NotEmpty(message="描述不能为空")
    @Length(max = 20,message="描述不能超过20个字符")
    private String name;
    /**
     * 父Id
     */
    private Long parentId;
    /**
     * 代码
     */
    private String code;
    /**
     * 层级: 1一级 2 二级
     */
    private Byte depth;
    /**
     * 状态: 0无效 1有效
     */
    private Byte status;
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
