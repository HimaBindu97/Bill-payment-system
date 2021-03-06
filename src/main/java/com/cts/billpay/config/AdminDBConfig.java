package com.cts.billpay.config;
/*
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cts.billpay.admin.dao.AdminDao;
import com.cts.billpay.admin.entities.Admin;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = AdminDao.class, entityManagerFactoryRef = "adminDSEmFactory", transactionManagerRef = "adminDSTransactionManager")
public class adminDBConfig {
	
	@Bean
	@ConfigurationProperties("spring.datasource3")
	public DataSourceProperties adminDSProperties() {
		return new DataSourceProperties();
	}

	
	@Bean
	public DataSource adminDS(@Qualifier("adminDSProperties") DataSourceProperties adminDSProperties) {
		return adminDSProperties.initializeDataSourceBuilder().build();
	}

	
	@Bean
	public LocalContainerEntityManagerFactoryBean adminDSEmFactory(@Qualifier("adminDS") DataSource adminDS,
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(adminDS).packages(Admin.class).build();
	}

	
	@Bean
	public PlatformTransactionManager adminDSTransactionManager(EntityManagerFactory adminDSEmFactory) {
		return new JpaTransactionManager(adminDSEmFactory);
	}

}*/


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cts.billpay.admin.entities.Admin;




@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(entityManagerFactoryRef = "adminEntityManager", 
						transactionManagerRef = "adminTransactionManager", 
						basePackages = "com.cts.billpay.admin.dao")

public class AdminDBConfig {
	
	@Autowired
	private Environment env;
	
	
	@Bean
	public DataSource getAdminDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.admin.datasource.driver.class"));
		dataSource.setUrl(env.getProperty("spring.admin.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.admin.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.admin.datasource.password"));
		return dataSource;
	}

	
	@Bean(name = "adminEntityManager")
	public LocalContainerEntityManagerFactoryBean adminEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getAdminDataSource()).properties(hibernateProperties()).packages(Admin.class)
				.persistenceUnit("adminPU").build();
	}

	
	@Bean(name = "adminTransactionManager")
	public PlatformTransactionManager adminTransactionManager(
			@Qualifier("adminEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Map<String, Object> hibernateProperties() {

		Resource resource = new ClassPathResource("hibernate.properties");
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			return properties.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue()));
		} catch (IOException e) {
			return new HashMap<String, Object>();
		}
	}
}