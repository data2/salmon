package com.data2.salmon.core;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

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
        // TODO
        if ("jdbc".equals(type)) {
            return new String[]{QuickService.class.getName()};
        } else {
            return new String[]{LinkService.class.getName()};
        }
    }
}
