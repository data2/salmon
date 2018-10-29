package com.muskteer.dico.inner;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Mapper {
    @AliasFor(
            annotation = Component.class
    )
    String file() default "mapper";

    String database() default "database";
}
