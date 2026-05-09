package com.cricket.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    private static final String DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
    private static final String LOCAL_DB = "jdbc:ucanaccess://C:\\Sports\\Cricket\\Database\\CricketTeams.mdb";
    private static final String ARCHIVE_DB = "jdbc:ucanaccess://C:\\Sports\\CricketArchive\\Database\\CricketTeams.mdb";

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource local = new DriverManagerDataSource();
        local.setDriverClassName(DRIVER);
        local.setUrl(LOCAL_DB);

        DriverManagerDataSource archive = new DriverManagerDataSource();
        archive.setDriverClassName(DRIVER);
        archive.setUrl(ARCHIVE_DB);

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("LOCAL", local);
        targetDataSources.put("ARCHIVE", archive);

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(local);
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.cricket.model");
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    private Properties getHibernateProperties() {

        Properties prop = new Properties();
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.hbm2ddl.auto", "update");
        prop.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        prop.put("hibernate.dialect","org.hibernate.dialect.HSQLDialect");

        return prop;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}