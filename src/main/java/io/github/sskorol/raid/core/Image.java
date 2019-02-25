package io.github.sskorol.raid.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static io.github.sskorol.raid.config.BaseConfig.CONFIG;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Image {

    float affinity() default 0.7f;

    boolean highlight() default false;

    String[] name() default {};

    int screenHeight() default 845;

    int screenWidth() default 1439;

    int x() default 0;

    int y() default 0;
}
