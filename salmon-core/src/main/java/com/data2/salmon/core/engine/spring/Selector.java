package com.data2.salmon.core.engine.spring;

import com.data2.salmon.core.BootSalmon;
import com.data2.salmon.core.engine.inter.LinkService;
import com.data2.salmon.core.engine.inter.QuickService;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import static com.data2.salmon.core.engine.enums.DataBase.JDBC;

/**
 * @author leewow
 */
public class Selector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Class<?> annotationType = BootSalmon.class;
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(
                annotationType.getName(), false));
        String type = attributes.getString("type");
        if (JDBC.getCode().equals(type)) {
            return new String[]{QuickService.class.getName()};
        } else {
            return new String[]{LinkService.class.getName()};
        }
    }
}
