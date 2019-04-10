package com.arcsoft.facego.web.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.arcsoft.facego.util.bjui.Page;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class PersonInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 代码
     */
    private String code;
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

    //人脸信息
    private Page<PersonImageVO> facedata;

}
