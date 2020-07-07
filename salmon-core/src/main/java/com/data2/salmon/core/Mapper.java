package com.data2.salmon.core;

import java.lang.annotation.*;

/**
 * @author leewow
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Mapper {

    String name() default "";

    String file();

    DataBase database();

}
