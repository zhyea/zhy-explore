package org.chobit.spring.config;

import org.chobit.spring.bean.Worker;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class WorkerBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder workerBuilder = BeanDefinitionBuilder.rootBeanDefinition(Worker.class);
        registry.registerBeanDefinition("jerry", workerBuilder.getBeanDefinition());
    }

}
