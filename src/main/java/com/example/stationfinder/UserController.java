package com.example.stationfinder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	public String uname, pass, n;
	public int i;
	
	@Autowired
	private JdbcTemplate template;
	
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
	public ModelAndView save(@ModelAttribute User user) {
		if(template.queryForObject("select Username from Users where Username = " + user.getUsername(), String.class) != null && template.queryForObject("select Password from Users where Password = " + user.getPassword(), String.class) != null) {
			modelAndView.setViewName("loginUser");
		}
		else {
			modelAndView.setViewName("loginUser");
			modelAndView.addObject("user", user);
		}
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String add(@ModelAttribute User user) {
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		
		template.update("insert into Users(Name, Username, Password) values(?, ?, ?)", user.getName(), user.getUsername(), user.getPassword());
		
		return "index";
	}
	
}
