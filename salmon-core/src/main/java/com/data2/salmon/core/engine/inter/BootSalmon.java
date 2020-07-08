package com.data2.salmon.core.engine.inter;

import java.lang.annotation.*;

/**
 * @author leewow
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
