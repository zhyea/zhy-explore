package org.chobit.spring.ext;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

    private Map<String, DruidDataSource> datasource;

    public Map<String, DruidDataSource> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, DruidDataSource> datasource) {
        this.datasource = datasource;
    }
}
