package org.chobit.spring.autoconfig;


import org.chobit.spring.HelloStarter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HelloStarter.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    private HelloProperties helloProperties;

    public HelloAutoConfiguration(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    //@ConditionalOnProperty(prefix = "hello", name = "name")
    @Bean
    public HelloStarter helloStarter() {
        return new HelloStarter(helloProperties.getName());
    }
}
