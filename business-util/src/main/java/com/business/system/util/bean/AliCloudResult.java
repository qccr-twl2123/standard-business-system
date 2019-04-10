/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.util.bean;

import lombok.Data;

/**
 * @author xierongli
 * @version $$Id: uber-system, v 0.1 2018/4/10 上午10:13 mark1xie Exp $$
 */
@Data
public class AliCloudResult<T> {

    private T result;
    private String msg;
    private String status;
}
