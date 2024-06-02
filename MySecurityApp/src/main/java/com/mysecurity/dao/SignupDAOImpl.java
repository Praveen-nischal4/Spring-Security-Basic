package com.mysecurity.dao;

import com.mysecurity.model.SignupDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SignupDAOImpl implements SingupDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void saveUser(SignupDTO signupDTO) {
		
		String sql = "insert into users values(?,?,?)";
		String sql1 ="insert into authorities values(?,?)";
		
		jdbcTemplate.update(sql, signupDTO.getUsername(),signupDTO.getPassword(),1);
		jdbcTemplate.update(sql1, signupDTO.getUsername(),"user");
		
	}

}
