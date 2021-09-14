package com.data2.salmon.core.engine.inter;

import com.data2.salmon.core.engine.enums.DataBase;

import java.lang.annotation.*;

/**
 * @author data2
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
