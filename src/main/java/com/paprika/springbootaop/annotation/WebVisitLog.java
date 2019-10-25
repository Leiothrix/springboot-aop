package com.paprika.springbootaop.annotation;

import java.lang.annotation.*;

/**
 * @author adam
 * @date 2019/10/25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebVisitLog {
    /**
     * 接口描述
     */
    String description();

    /**
     * 访问记录是否存入数据库
     */
    boolean intoDatabase() default false;
}
