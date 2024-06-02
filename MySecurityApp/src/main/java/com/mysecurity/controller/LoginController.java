package com.mysecurity.controller;

import com.mysecurity.dao.SingupDAO;
import com.mysecurity.model.SignupDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private SingupDAO signupDAO;
	
	@GetMapping("/myLogin")
	public String getLogin()
	{
		return "Login";
		
	}
	
	@GetMapping("/signup")
	public String signUp(@ModelAttribute("signupdto") SignupDTO signupDTO)
	{
		return "sign_up";
		
	}
	
	@PostMapping("/process-signup")
	public String getSignup(SignupDTO signupDTO)
	{
		//before encoding 
		System.out.println(signupDTO);
		
	String encodedPassword = bcryptPasswordEncoder.encode(signupDTO.getPassword());         //encode password
	
	signupDTO.setPassword(encodedPassword);
	
	//After encoding 
			System.out.println(signupDTO);
			
			
	//finally save in database
			
			signupDAO.saveUser(signupDTO);
		
		return "redirect:/myLogin";
		
	}
	
	
}
