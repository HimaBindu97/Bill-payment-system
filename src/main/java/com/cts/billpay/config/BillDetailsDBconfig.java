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

import com.cts.billpay.billDetails.dao.BillDetailsDao;
import com.cts.billpay.billDetails.entities.BillDetails;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = BillDetailsDao.class, entityManagerFactoryRef = "billDetailsDSEmFactory", transactionManagerRef = "billDetailsDSTransactionManager")
public class billDetailsDBConfig {
	
	@Bean
	@ConfigurationProperties("spring.datasource3")
	public DataSourceProperties billDetailsDSProperties() {
		return new DataSourceProperties();
	}

	
	@Bean
	public DataSource billDetailsDS(@Qualifier("billDetailsDSProperties") DataSourceProperties billDetailsDSProperties) {
		return billDetailsDSProperties.initializeDataSourceBuilder().build();
	}

	
	@Bean
	public LocalContainerEntityManagerFactoryBean billDetailsDSEmFactory(@Qualifier("billDetailsDS") DataSource billDetailsDS,
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(billDetailsDS).packages(BillDetails.class).build();
	}

	
	@Bean
	public PlatformTransactionManager billDetailsDSTransactionManager(EntityManagerFactory billDetailsDSEmFactory) {
		return new JpaTransactionManager(billDetailsDSEmFactory);
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

import com.cts.billpay.billdetails.entity.BillDetails;




@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(entityManagerFactoryRef = "billDetailsEntityManager", 
						transactionManagerRef = "billDetailsTransactionManager", 
						basePackages = "com.cts.billpay.billDetails.dao")

public class BillDetailsDBconfig {
	
	@Autowired
	private Environment env;
	
	
	@Bean
	public DataSource getBillDetailsDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.billDetails.datasource.driver.class"));
		dataSource.setUrl(env.getProperty("spring.billDetails.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.billDetails.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.billDetails.datasource.password"));
		return dataSource;
	}

	
	@Bean(name = "billDetailsEntityManager")
	public LocalContainerEntityManagerFactoryBean billDetailsEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getBillDetailsDataSource()).properties(hibernateProperties()).packages(BillDetails.class)
				.persistenceUnit("billDetailsPU").build();
	}

	
	@Bean(name = "billDetailsTransactionManager")
	public PlatformTransactionManager billDetailsTransactionManager(
			@Qualifier("billDetailsEntityManager") EntityManagerFactory entityManagerFactory) {
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