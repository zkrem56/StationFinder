package com.example.stationfinder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	public String uname, pass;
	public int i;
	
	@Autowired
	private JdbcTemplate template;
	
	//Home Page
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//Logining Authentication
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute User user) {
		ModelAndView modelAndView = new ModelAndView();
		if(template.queryForObject("select Username from Users where Username = " + user.getUsername(), String.class) != null && template.queryForObject("select Password from Users where Password = " + user.getPassword(), String.class) != null) {
			modelAndView.setViewName("index");
		}
		else {
			modelAndView.addObject("user", user);
		}
		
		return modelAndView;
		
	}
	
}
