package com.huangw1.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by huangw1 on 2018/5/6.
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {

    public String value();
}
