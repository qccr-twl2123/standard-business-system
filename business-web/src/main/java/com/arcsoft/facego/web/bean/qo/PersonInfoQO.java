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
 * 人员表
 * </p>
 *
 * @author mark
 * @since 2018-07-04
 */
@Data
@Accessors(chain = true)
public class PersonInfoQO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 姓名
     */
    @NotEmpty(message="姓名不能为空")
    @Length(max = 20,message="姓名不能超过20个字符")
    private String name;
    /**
     * 代码
     */
    @NotEmpty(message="编码不能为空")
    @Length(max = 20,message="编码不能超过20个字符")
    private String code;


    @NotEmpty(message="图片不能为空")
    @Length(min = 23,message="图片太小，请重新上传")
    private String image64;
    /**
     * 类型: 1员工，2拜访者，3面试者
     */
    private Byte type;
    /**
     * 状态： 0无效 1有效
     */
    private Byte status;
    /**
     * 所属部门Id
     */
    private Long departmentParentId;
    /**
     * 所属部门二级Id
     */
    private Long departmentId;
    /**
     * 门禁权限
     */
    private String doorRights;
    /**
     * 主图地址链接
     */
    private String masterImageUrl;
    /**
     * 人脸在图片中位置
     */
    private String materRect;
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

    private Long departmentFirst;

    private Long departmentSecond;
}
