package org.chobit.spring.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {




    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{MySelectConfig.class.getName()};
    }


}
