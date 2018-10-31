package com.muskteer.dico;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BootDico {
    String database() default "";

    String name() default  "";

    String file() default  "";
}
