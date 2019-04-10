package com.business.system.util;

/**
 * Created by xierongli on 17/6/18.
 */

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xierongli
 * @version $$Id: livtripmanager-parent, v 0.1 2017/2/18 17:39 user Exp $$
 * @name 工具装换类
 */

public abstract class ObjectConvert {


    /**
     * 把source对象转换成另一个clazz类型的对象, 两种对象相同属性的类型，必须相同，否则会抛出BeansException异常。
     * 内部采用BeanUtils.copyProperties(source, object)来实现属性复制。
     *
     * @param source	源对象
     * @param clazz		目标对象的类型
     * @return
     */
    public static <T> T convertObject(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }

        T object = BeanUtils.instantiate(clazz);
        BeanUtils.copyProperties(source, object);
        return object;
    }

    /**
     * 拷贝list,只支持List<Object>结构；不支持list里面的元素为集合类型
     *
     * @param source	源list数据
     * @param clazz		需要转换成的list元素类型
     * @return	转换后的列表对象
     */
    public static <T> List<T> convertList(List<?> source, Class<T> clazz) {
        if (source == null) {
            return null;
        }

        List<T> lists = new ArrayList<T>();
        for (Object element : source) {
            lists.add(convertObject(element, clazz));
        }
        return lists;
    }
}
