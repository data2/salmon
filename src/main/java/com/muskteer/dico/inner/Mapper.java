package com.muskteer.dico.inner;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Mapper {
    String file() default "mapper";
    String database() default "database";
}
