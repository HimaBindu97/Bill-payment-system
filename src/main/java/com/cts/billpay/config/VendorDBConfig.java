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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cts.billpay.entities.vendor.Vendor;
import com.cts.billpay.vendor.dao.VendorDao;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = VendorDao.class, entityManagerFactoryRef = "VendorDSEmFactory", transactionManagerRef = "VendorDSTransactionManager")
public class VendorDBConfig {

	
	@Bean
	@ConfigurationProperties("spring.datasource1")
	public DataSourceProperties VendorDSProperties() {
		return new DataSourceProperties();
	}

	
	@Bean
	public DataSource VendorDS(@Qualifier("VendorDSProperties") DataSourceProperties VendorDSProperties) {
		return VendorDSProperties.initializeDataSourceBuilder().build();
	}

	
	@Bean
	public LocalContainerEntityManagerFactoryBean VendorDSEmFactory(@Qualifier("VendorDS") DataSource VendorDS,
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(VendorDS).packages(Vendor.class).build();
	}

	
	@Bean
	public PlatformTransactionManager VendorDSTransactionManager(EntityManagerFactory VendorDSEmFactory) {
		return new JpaTransactionManager(VendorDSEmFactory);
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

import com.cts.billpay.vendor.entities.Vendor;




@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(entityManagerFactoryRef = "vendorEntityManager", 
						transactionManagerRef = "vendorTransactionManager", 
						basePackages = "com.cts.billpay.vendor.dao")

public class VendorDBConfig {
	
	@Autowired
	private Environment env;
	
	
	@Bean
	public DataSource getVendorDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.vendor.datasource.driver.class"));
		dataSource.setUrl(env.getProperty("spring.vendor.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.vendor.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.vendor.datasource.password"));
		return dataSource;
	}

	
	@Bean(name = "vendorEntityManager")
	public LocalContainerEntityManagerFactoryBean vendorEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getVendorDataSource()).properties(hibernateProperties()).packages(Vendor.class)
				.persistenceUnit("vendorPU").build();
	}

	
	@Bean(name = "vendorTransactionManager")
	public PlatformTransactionManager vendorTransactionManager(
			@Qualifier("vendorEntityManager") EntityManagerFactory entityManagerFactory) {
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