package com.guofei.wu.springannotation.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-12 15:44
 * @since v3.0
 */
@PropertySource("classpath:dbprofile.properties")
@Configuration
public class MainConfigProfile implements EmbeddedValueResolverAware {


    @Value("${db.user}")
    private String user;

    private StringValueResolver valueResolver;

    private String driverClass;

    @Profile("test")
    @Bean("dataSourceTest")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource db = new ComboPooledDataSource();
        db.setUser(user);
        db.setPassword(pwd);
        db.setDriverClass(driverClass);
        db.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        return db;
    }

    @Profile("dev")
    @Bean("dataSourceDev")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource db = new ComboPooledDataSource();
        db.setUser(user);
        db.setPassword(pwd);
        db.setDriverClass(driverClass);
        db.setJdbcUrl("jdbc:mysql://localhost:3306/spring_cache");
        return db;
    }


    @Profile("prod")
    @Bean("dataSourceProd")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource db = new ComboPooledDataSource();
        db.setUser(user);
        db.setPassword(pwd);
        db.setDriverClass(driverClass);
        db.setJdbcUrl("jdbc:mysql://localhost:3306/sell");
        return db;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver = resolver;
        driverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }
}
