package com.shop.utils;

import org.springframework.cglib.beans.BeanCopier;

public class BeanUtils {
	
    /**
     * 复制属性：从源对象复制和目标对象相同的属性
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copy(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(),false);
        beanCopier.copy(source, target, null);
    }

}
