package com.example.stationfinder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.web.servlet.ModelAndView;

import com.example.stationdata.Library;

@Controller
public class UserController {
	
	private String uname, pass, n;
	private String libName, libAddr, libCity, libCounty, libWeb;
	private int libId;
	private int maxRows = 1;
	private int maxlibs = 17;
	
	private ArrayList<Library> lib = new ArrayList<Library>();
	
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
	
	@RequestMapping("/aboutUs")
	public String aboutUs() {
		return "aboutUs";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	//Logining Authentication
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public String save(@ModelAttribute User user) {
		
		for(int i = 1; true; i++)
		{
			try {
				uname = template1.queryForObject("select username from Users where ID = " + i, String.class);
				pass = template1.queryForObject("select password from Users where ID = " + i, String.class);
			}
			catch (Exception e) {
				return "login";
			}
			
			if(uname.equals(user.getUsername()) && pass.equals(user.getPassword())) {
				return "loginUser";
			}
		}
		
		
		//return modelAndView;
		/*if(pagec == true)
			return "loginUser";
		else
			return "login";*/
		
	}
	
	@RequestMapping(value = "/registeredUser", method = RequestMethod.POST)
	public String add(@ModelAttribute User user) {
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		
		template1.update("insert into Users(person_name, username, password) values(?, ?, ?)", user.getName(), user.getUsername(), user.getPassword());
		
		maxRows++;
		
		
		
		return "registeredUser";
	}
	
	
	//Library Search
	@RequestMapping(value = "/searchResult", method = RequestMethod.POST)
	public String search(@ModelAttribute Library libdata) {
		ModelAndView model = new ModelAndView();
		
		if(lib.isEmpty()) {
			for(int i = 0; true; i++) {
				if(lib.isEmpty()) {
					try {
						libName = template2.queryForObject("select lib_name from Libraries where ID = " + i, String.class);
						libAddr = template2.queryForObject("select lib_address from Libraries where ID = " + i, String.class);
						libCity = template2.queryForObject("select lib_city from Libraries where ID = " + i, String.class);
						libWeb = template2.queryForObject("select lib_web from Libraries where ID = " + i, String.class);
						libCounty = template2.queryForObject("select lib_county from Libraries where ID = " + i,  String.class);
						libId = template2.queryForObject("select ID from Libraries where ID = " + i, Integer.class);
					}
					catch(Exception e) {
						break;
					}
					
					lib.add(new Library(libId, libName, libAddr, libCity, libWeb, libCounty));
				}
				else {
					try {
						libName = template2.queryForObject("select lib_name from Libraries where ID = " + i, String.class);
						libAddr = template2.queryForObject("select lib_address from Libraries where ID = " + i, String.class);
						libCity = template2.queryForObject("select lib_city from Libraries where ID = " + i, String.class);
						libWeb = template2.queryForObject("select lib_web from Libraries where ID = " + i, String.class);
						libCounty = template2.queryForObject("select lib_county from Libraries where ID = " + i,  String.class);
						libId = template2.queryForObject("select ID from Libraries where ID = " + i, Integer.class);
					}
					catch(Exception e) {
						break;
					}
					
					lib.add(new Library(libId, libName, libAddr, libCity, libWeb, libCounty));
				}
			}
		}
		
		System.out.println(libdata.getName());
		
		model.addObject("libdata", libdata);
		
		return "searchResult";
	}
	
}
