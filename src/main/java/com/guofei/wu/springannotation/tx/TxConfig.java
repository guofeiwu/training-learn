package com.guofei.wu.springannotation.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @Author Mason
 * @Description
 * @Date 2020/1/13 9:15
 **/
@Configuration
@ComponentScan(value = {"com.guofei.wu.springannotation.tx"})
@EnableTransactionManagement
public class TxConfig {

    /**
     * 1、配置数据源
     * 2、在方法上加上@Transactional
     * 3、开启@EnableTransactionManagement
     * 4、配置事务管理器
     *
     * 事务原理
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setUser("root");
        ds.setPassword("123456");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        ds.setDriverClass("com.mysql.jdbc.Driver");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }
}
