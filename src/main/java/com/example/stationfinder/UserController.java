package com.example.stationfinder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.stationdata.Library;

@Controller
public class UserController {
	
	private String uname, pass, n;
	private String libName, libAddr, libCity, libCounty, libWeb;
	private int libId;
	private int maxRows = 1;
	private int maxlibs = 17;
	
	//private ArrayList<Library> lib = new ArrayList<Library>();
	
	@Autowired
	private JdbcTemplate template1;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LibService libService;
	
	//Home Page
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/result") 
	public String showHome(Model model) { 
		Library libdata =  new Library(); 
		model.addAttribute("libdata", libdata);
	  
		return "search_form"; 
	 }
	
	//Library Results
	@PostMapping("/result")
	public String submitSearchForm(@ModelAttribute ("libdata") Library libdata, Model model) {
		List<Library> list = libService.getAllTheLibraries();
		List<Library> temp = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			if(libdata.getLibName() != null && libdata.getLibName().equals(list.get(i).getLibName())) {
				temp.add(libService.getLibrary(i+1));
				break;
			}
			else if(libdata.getAddr() != null && libdata.getAddr().equals(list.get(i).getAddr())) {
				temp.add(libService.getLibrary(i+1));
				break;
			}
			else if(libdata.getCity() != null && libdata.getCity().equals(list.get(i).getCity())) {
				temp.add(libService.getLibrary(i+1));
			}
			else if(libdata.getCounty() != null && libdata.getCounty().equals(list.get(i).getCounty()))
				temp.add(libService.getLibrary(i+1));
		}
		
		for(int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i).getLibName());
		}
		
		model.addAttribute("temp", temp);
		
		return "search_results";
	}
	
	@GetMapping("/login")
	public String showLogForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "login_form";
	}
	
	//Login Authentication
	@PostMapping("/login")
	public String submitLogForm(@ModelAttribute ("user") User user) {		
		List<User> users = userService.getAllTheUsers();
		
		for(int i = 0; i < users.size(); i++) {
			
			if(user.getUsername().equals(users.get(i).getUsername()) && user.getPassword().equals(users.get(i).getPassword()))
				return "loginUser";
		}
		
		return "login_form";
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
	
	
	//Adds a new User
	@PostMapping("/register")
	public String submitRegForm(@ModelAttribute ("user") User user) {
		userService.updateUser(user);
		return "registeredUser";
	}
	
}
