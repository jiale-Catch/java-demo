package com.jiale.aopjdkproxy.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(value = {ElementType.METHOD ,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreAccess {
}
