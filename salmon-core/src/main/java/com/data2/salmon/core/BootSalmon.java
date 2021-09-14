package com.data2.salmon.core;

import java.lang.annotation.*;

/**
 * @author data2
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BootSalmon {
    String database() default "";

    String name() default "";

    String file() default "";
}
