package com.example.web.annotation;

import java.lang.annotation.*;

/**
 * @author JIA
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {

}
