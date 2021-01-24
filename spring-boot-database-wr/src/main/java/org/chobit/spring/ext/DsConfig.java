package org.chobit.spring.ext;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author robin
 */
@Configuration
public class DsConfig {

    @Bean(name = "readCfg")
    @ConfigurationProperties("datasource.read")
    public DataSourceProperties readConfig() {
        return new DataSourceProperties();
    }


    @Primary
    @Bean(name = "writeCfg")
    @ConfigurationProperties("datasource.write")
    public DataSourceProperties writeConfig() {
        return new DataSourceProperties();
    }

}
