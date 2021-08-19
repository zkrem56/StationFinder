package com.example.stationfinder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.example.stationdata.LibraryService;
import com.example.stationdata.LibService;

@Controller
public class UserController {
	
	private String uname, pass, n;
	private String libName, libAddr, libCity, libCounty, libWeb;
	private int libId;
	private int maxRows = 1;
	private int maxlibs = 17;
	
	//private ArrayList<Library> lib = new ArrayList<Library>();
	/*@Autowired
	private UserService userService;*/
	
	@Autowired
	private LibService libService;
	
	@Autowired
	private JdbcTemplate template1;
	
	@Autowired
	private JdbcTemplate template2;
	
	private ModelAndView modelAndView;
	
	//Home Page
	
	@GetMapping("/result") 
	public String showHome(Model model) { 
		Library libdata =  new Library(); 
		model.addAttribute("libdata", libdata);
	  
		return "search_form"; 
	 }
	
	@PostMapping("/result")
	public String submitSearchForm(@ModelAttribute ("libdata") Library libdata, Model model) {
		List<Library> list = libService.getAllTheLibraries();
		List<Library> temp = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			try {
				if(libdata.getLibName().equals(list.get(i).getLibName()))
					temp.add(libService.getLibrary(i));
			}
			catch (Exception e) {
				
			}
			
			try {
				if(libdata.getAddr().equals(list.get(i).getAddr()))
					temp.add(libService.getLibrary(i));
			}
			catch (Exception e) {
				
			}
			
			try {
				if(libdata.getCounty().equals(list.get(i).getAddr()))
					temp.add(libService.getLibrary(i));
			}
			catch (Exception e) {
				
			}
		}
		
		model.addAttribute("temp", temp);
		
		return "search_reult";
	}
	
	@GetMapping("/login")
	public String showLogForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "login_form";
	}
	
	@PostMapping("/login")
	public String submitLogForm(@ModelAttribute ("user") User user) {
		for(int i = 1; true; i++) {
			try {
				uname = template1.queryForObject("select username from Users where ID = " + i, String.class);
				pass = template1.queryForObject("select password from Users where ID = " + i, String.class);
			}
			catch (Exception e) {
				return "login_form";
			}
			
			if(uname.equals(user.getUsername()) && pass.equals(user.getPassword()))
				return "loginUser";
			else
				return "login_form";
		}
	}
	
	@RequestMapping("/aboutUs")
	public String aboutUs() {
		return "aboutUs";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/register")
	public String showForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "register_form";
	}
	
	@PostMapping("/register")
	public String submitRegForm(@ModelAttribute ("user") User user) {
		//userService.updateUser(user);
		return "registeredUser";
	}
	
	//Logining Authentication
	/*@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String save(@ModelAttribute ("user") User user) {
		
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
		if(pagec == true)
			return "loginUser";
		else
			return "login";
		
	}*/
	
	/*@RequestMapping(value = "/registeredUser", method = RequestMethod.POST)
	public String add(@ModelAttribute User user) {
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		
		template1.update("insert into Users(person_name, username, password) values(?, ?, ?)", user.getName(), user.getUsername(), user.getPassword());
		
		maxRows++;
		
		
		
		return "registeredUser";
	}*/
	
	
	//Library Search
	/*@RequestMapping(value = "/searchResult", method = RequestMethod.POST)
	public String search(@ModelAttribute("libdata") Library libdata) {
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
		
		System.out.println(libdata.getLibName());
		
		model.addObject("libdata", libdata);
		
		return "searchResult";
	}*/
	
}
