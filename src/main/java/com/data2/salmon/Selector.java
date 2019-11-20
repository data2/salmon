package com.data2.salmon;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class Selector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Class<?> annotationType = BootSalmon.class;
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(
                annotationType.getName(), false));
        String type = attributes.getString("type");
        if ("jdbc".equals(type)) {
            return new String[] { QuickDicoService.class.getName() };
        }  else {
            return new String[] { DicoService.class.getName() };
        }
    }
}
