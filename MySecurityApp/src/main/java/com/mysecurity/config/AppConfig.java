package com.mysecurity.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mysecurity")
public class AppConfig {

	@Bean
	public ViewResolver getViewResolver()
	{
		InternalResourceViewResolver views = new InternalResourceViewResolver();
		views.setSuffix(".jsp");
		views.setPrefix("/WEB-INF/views/");
		return views;
	}
	
	
	@Bean
	public PasswordEncoder bcryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DataSource datasource()
	{
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/practisespring");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("955455Praveen");
		
	return driverManagerDataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource());
	
		return jdbcTemplate;
	}
	
}
