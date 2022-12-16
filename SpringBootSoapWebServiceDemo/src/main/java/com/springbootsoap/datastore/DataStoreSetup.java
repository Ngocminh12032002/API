package com.springbootsoap.datastore;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.springbootsoap.repository")
@ComponentScan(value = "com.springbootsoap.*")
@EntityScan(basePackages = { "com.springbootsoap.model" })
@PropertySource("classpath:application.properties")
public class DataStoreSetup {
	@Value("${spring.datasource.url")
	String databaseUrl;

	@Value("${spring.datasource.username")
	String databaseUser;

	@Value("${spring.datasource.password")
	String databasePassword;

	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DataSourceBuilder dataSource = DataSourceBuilder.create();

		// Xem: datasouce-cfg.properties
		dataSource.driverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.url(env.getProperty("spring.datasource.url"));
		dataSource.username(env.getProperty("spring.datasource.username"));
		dataSource.password(env.getProperty("spring.datasource.password"));
		return dataSource.build();
	}
}
