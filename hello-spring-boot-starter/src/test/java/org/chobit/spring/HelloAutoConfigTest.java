package org.chobit.spring;

import org.chobit.spring.autoconfig.HelloAutoConfiguration;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloAutoConfigTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(HelloAutoConfiguration.class));


    @Test
    public void defaultStarterBacksOff() {
        this.contextRunner.withUserConfiguration(HelloConfiguration.class).run((context) -> {
            assertThat(context).hasSingleBean(HelloStarter.class);
            assertThat(context).getBean("helloStarter").isSameAs(context.getBean(HelloStarter.class));
            assertThat(context.getBean(HelloStarter.class).getName()).isEqualTo(null);
        });
    }


    @Test
    public void serviceNameCanBeConfigured() {
        this.contextRunner.withPropertyValues("hello.name=chobit").run((context) -> {
            assertThat(context.getBean(HelloStarter.class).getName()).isEqualTo("chobit");
        });
    }


    @Test
    public void autoConfigTest() {
        ConditionEvaluationReportLoggingListener initializer = new ConditionEvaluationReportLoggingListener(
                LogLevel.INFO);
        ApplicationContextRunner contextRunner =
                new ApplicationContextRunner()
                        .withConfiguration(AutoConfigurations.of(HelloAutoConfiguration.class))
                        .withInitializer(initializer).run((context) -> System.out.println(context.getBean(HelloStarter.class).getName()));

    }


    @Test
    public void serviceIsIgnoredIfLibraryIsNotPresent() {
        this.contextRunner.withClassLoader(new FilteredClassLoader(HelloStarter.class))
                .run((context) -> assertThat(context).doesNotHaveBean("helloStarter"));
    }


    @Configuration
    static class HelloConfiguration {

        @Bean
        HelloStarter helloStarter() {
            return new HelloStarter("chobit");
        }

    }

}
