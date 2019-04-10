package com.arcsoft.facego.web.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class DepartmentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 父Id
     */
    private Long parentId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 代码
     */
    private String code;
    /**
     * 层级: 1一级 2 二级
     */
    private Integer depth;
    /**
     * 状态: 0无效 1有效
     */
    private Integer status;
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
