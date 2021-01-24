package org.chobit.spring.ext;


import javax.sql.DataSource;
import java.util.Map;

//@Component
//@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

    private Map<String, DataSource> datasource;

    public Map<String, DataSource> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, DataSource> datasource) {
        this.datasource = datasource;
    }
}
