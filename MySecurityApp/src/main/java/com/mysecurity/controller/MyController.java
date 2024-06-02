package com.mysecurity.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	/*
	//Principle means user name
	
	@RequestMapping("/")
	public String myHome(Principal principle)
	{
		
		
		String username = principle.getName();
		System.out.println(" Hello "+username);
		
		return "myhome";
	}
	*/
	
	@RequestMapping("/")
	public String myHome(Principal principle,Authentication auth,Model model)
	{
		 
	    String username = "";                     // Initialize user name as empty string
	    // Check if principal is not null
	    if (principle != null) {
	       
	        username = principle.getName();                // Fetch user name
	        System.out.println(username);
	    }

	    // Fetch authorities
	    Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
	    
	    if(auth!=null)
	    {
	    authorities = auth.getAuthorities();
	    System.out.println(authorities);
	    
	    }
	    model.addAttribute("username", username);
	    model.addAttribute("roles", authorities);

	    return "myhome";
		
		
		
		
		/*
		
		//fetching the username
		 String username = "";
		
		 username =principle.getName();
		System.out.println(username);
				
		//fetching authorities
		
	 Collection<? extends GrantedAuthority>  authorities	= auth.getAuthorities();
	 System.out.println(authorities);
	 
	 model.addAttribute("username",username);
	 model.addAttribute("roles", authorities);
	 
		return "myhome";
		*/
	}
	
	
	
	@ResponseBody
	@GetMapping("/bye")
	public String sayBye()
	{
		return "Bye bye its Over";
	}
	
	
	@GetMapping("/trainer")
	public String showTrainerDashboard()
	{
		return "trainer-dashboard";
	}
	
	
	@GetMapping("/coder")
	public String showCoderDashboard()
	{
		return "coder-dashboard";
	}
	
	@GetMapping("/accessDenied")
	public String errorPage()
	{
		return "access-denied";
	}
}
