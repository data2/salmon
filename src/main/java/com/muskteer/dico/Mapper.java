package com.muskteer.dico;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Mapper {

    String name() default "";

    String file() ;

    String database() ;

}
