package com.example.stationfinder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	private String uname, pass, n;
	private int maxRows = 1;
	
	@Autowired
	private JdbcTemplate template1;
	
	@Autowired
	private JdbcTemplate template2;
	
	private ModelAndView modelAndView;
	
	//Home Page
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	//Logining Authentication
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public String save(@ModelAttribute User user) {
		
		boolean pagec = false;
		
		for(int i = 1; i < maxRows+1; i++ )
		{
			try {
				uname = template1.queryForObject("select username from Users where ID = 1", String.class);
				pass = template1.queryForObject("select password from Users where ID = 1", String.class);
			}
			catch (Exception e) {
				//modelAndView.addObject("user", user);
				System.out.println(e.toString());
				break;
			}
			
			if(uname.equals(user.getUsername()) && pass.equals(user.getPassword())) {
				pagec = true;
				break;
			}
		}
		
		
		//return modelAndView;
		if(pagec == true)
			return "loginUser";
		else
			return "login";
		
	}
	
	@RequestMapping(value = "/registeredUser", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute User user) {
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		
		template1.update("insert into Users(Name, Username, Password) values(?, ?, ?)", user.getName(), user.getUsername(), user.getPassword());
		
		maxRows++;
		
		modelAndView.setViewName("registeredUser");
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}
	
}
