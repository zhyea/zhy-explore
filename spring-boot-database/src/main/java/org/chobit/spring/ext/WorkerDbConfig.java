package org.chobit.spring.ext;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.chobit.spring.service.mapper",
        sqlSessionTemplateRef = "workerSqlSessionTemplate",
        sqlSessionFactoryRef = "workerSqlSessionFactory")
public class WorkerDbConfig {


    @Bean(name = "workerDataSource")
    @ConfigurationProperties(prefix = "custom.datasource.worker")
    public DataSource setDataSource() {
        return new DruidDataSource();
    }


    @Bean(name = "workerTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("workerDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = "workerSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("workerDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();
        conf.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(conf);

        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "workerSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("workerSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
