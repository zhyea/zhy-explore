package org.chobit.spring.ext;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.chobit.spring.ext.DsType.READ;
import static org.chobit.spring.ext.DsType.WRITE;

/**
 * @author robin
 */
@Component
public class ReadWriteDsRouter extends AbstractRoutingDataSource {

    @Autowired
    @Qualifier("readCfg")
    private DataSourceProperties dsReadCfg;

    @Autowired
    @Qualifier("writeCfg")
    private DataSourceProperties dsWriteCfg;


    @Override
    protected Object determineCurrentLookupKey() {
        DsType type = DsContextHolder.getDbType();
        DsContextHolder.clear();
        return type;
    }


    @Override
    public void afterPropertiesSet() {
        DataSource dsWrite = this.dsWriteCfg.initializeDataSourceBuilder().type(BasicDataSource.class).build();
        DataSource dsRead = this.dsReadCfg.initializeDataSourceBuilder().type(BasicDataSource.class).build();

        Map<Object, Object> dataSources = new HashMap<>(2);
        dataSources.put(READ, dsRead);
        dataSources.put(WRITE, dsWrite);

        this.setTargetDataSources(dataSources);
        this.setDefaultTargetDataSource(dsWrite);

        super.afterPropertiesSet();
    }

}
