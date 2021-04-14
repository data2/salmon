package com.data2.salmon.core.engine.spring;

import com.data2.salmon.core.BootSalmon;
import com.data2.salmon.core.engine.inter.LinkService;
import com.data2.salmon.core.engine.inter.QuickService;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import static com.data2.salmon.core.engine.enums.DataBase.jdbc;

/**
 * @author leewow
 */
public class Selector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{jdbc.name().equals(AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(
                BootSalmon.class.getName(), false)).getString("type")) ? QuickService.class.getName() : LinkService.class.getName()};
    }
}
