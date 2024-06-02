package com.mysecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity(debug = true)
public class SpringSecurity extends WebSecurityConfigurerAdapter {

	//i want to create some details for user
	// username,password,roles
	// Default password is 955455Pra
	
	
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;

	@Autowired 
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	/*	auth.inMemoryAuthentication()
		.withUser("Praveen").password("$2a$10$6unANv6C2jmVXUnJvL.VSuxGwAQpA6Kj.13uKKZwubUfxEcUVglWK").roles("admin")
		.and()
		.withUser("Raju").password("$2a$10$.EACjK492stlEEBRyObCPOPscYloeWyRIQNtr4h/AiSxKEC3dePSq").roles("user"); 
		
		System.out.println("Encoded password ="+bcryptPasswordEncoder.encode("Raju"));
		
		*/
		
		// code to connect user from DB i.e Database
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(bcryptPasswordEncoder);
		
	
		
		
		
	}   
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

     http
      .authorizeRequests()
      .antMatchers("/coder").hasAuthority("Coder")    
      .antMatchers("/trainer").hasAuthority("Trainer")
      .and()
      .formLogin().loginPage("/myLogin").loginProcessingUrl("/process-login").permitAll()
      .and()
      .httpBasic()
      .and()
      .logout()
      .and()
      .exceptionHandling().accessDeniedPage("/accessDenied");
	}
}
