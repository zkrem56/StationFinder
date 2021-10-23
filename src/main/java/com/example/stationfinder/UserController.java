package com.example.stationfinder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.stationdata.Library;

@Controller
public class UserController {
	private User usr = new User();
	
	@Autowired
	private JdbcTemplate template1;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LibService libService;
	
	//Home Page
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("user", usr);
		return "index";
	}
	
	@RequestMapping("/update")
	public String update() {
		return "update";
	}
	
	@GetMapping("/update")
	public String showUpdatePage(Model model) {
		Library libdata = new Library();
		model.addAttribute("libdata", libdata);
		model.addAttribute("user", usr);
		return "update";
	}
	
	@RequestMapping("/")
	public String test(Model model) {
		User user = new User();
		model.addAttribute("user", user);		
		return "index";
	}
	
	@RequestMapping("/thanks")
	public String thankyou() {
		
		return "thanks";
	}
	
	@RequestMapping("/thankyou")
	public String thankyou2(Model model) {
		model.addAttribute("user", usr);
		return "thankyou";
	}
	
	@RequestMapping(value = "/updated", method = RequestMethod.POST)
	public String updated(@ModelAttribute ("libdata") Library libdata, Model model) {
		libService.updateLibrary(libdata);
		
		model.addAttribute("user", usr);
		return "thanks";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute ("libdata") Library libdata, Model model) {
		libService.deleteLibrary(libdata);
		
		model.addAttribute("user", usr);
		
		return "thanks";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute ("libdata") Library libdata, Model model) {
		libService.saveLibrary(libdata);
		
		model.addAttribute("user", usr);
		return "thanks";
	}
	
	//Library Results
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String submitSearchForm(@ModelAttribute ("libdata") Library libdata, Model model) {
		//List<Library> list = libService.getAllTheLibraries();
		List<Library> temp = new ArrayList<>();
		int sid = 0;
		temp.clear();
		
		if(libdata.getLibName().length() > 2 && libdata.getBranchName().length() > 2 && libdata.getAddr().length() > 2 && libdata.getZip() > 0 && libdata.getCity().length() > 2 && libdata.getCounty().length() > 2) {
			try {
				System.out.println("HI");
				sid = template1.queryForObject("select ID from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", Integer.class);
				System.out.println(sid);
				temp.add(new Library(
						sid,
						template1.queryForObject("select Library_Name from Missouri where ID = "+sid, String.class),
						template1.queryForObject("select Branch_Name from Missouri where ID = "+sid, String.class),
						template1.queryForObject("select Mailing_Address from Missouri where ID = "+sid, String.class),
						template1.queryForObject("select City from Missouri where ID = "+sid, String.class),
						template1.queryForObject("select Library_Email_Address from Missouri where ID = "+sid, String.class),
						template1.queryForObject("select ZIP_Code from Missouri where ID = "+sid, Integer.class),
						template1.queryForObject("select County from Missouri where ID = "+sid, String.class)));
			}catch(Exception e){
				System.out.println("Did not work");
			}
		}
		else {
			
			if(libdata.getAddr().length() > 2)
				temp = libService.getAllTheLibraries(libdata, 3);
			else if(libdata.getZip() > 0)
				temp = libService.getAllTheLibraries(libdata, 4);
			else if(libdata.getBranchName().length() > 2)
				temp = libService.getAllTheLibraries(libdata, 5);
			else if(libdata.getCity().length() > 2)
				temp = libService.getAllTheLibraries(libdata, 1);
			else if(libdata.getLibName().length() > 2)
				temp = libService.getAllTheLibraries(libdata, 0);
			else if(libdata.getCounty().length() > 2)
				temp = libService.getAllTheLibraries(libdata, 2);
			else
				temp.add(new Library("Search Not Found"));
		}
		
		
		model.addAttribute("temp", temp);
		model.addAttribute("user", usr);
		
		return "index";
	}
	
	@GetMapping("/login")
	public String showLogForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "login_form";
	}
	
	//Login Authentication
	@PostMapping("/login")
	public String submitLogForm(@ModelAttribute ("user") User user, Model model) {		
		List<User> users = userService.getAllTheUsers();
		
		for(int i = 0; i < users.size(); i++) {
			
			if(user.getUsername().equals(users.get(i).getUsername()) && user.getPassword().equals(users.get(i).getPassword())) {
				usr.setUsername(user.getUsername());
				model.addAttribute("user", usr);
				return "index";
			}
		}
		
		return "login_form";
	}
	
	@RequestMapping("/aboutUs")
	public String aboutUs(Model model) {
		model.addAttribute("user", usr);
		return "aboutUs";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		usr.setUsername("");
		model.addAttribute("user", usr);
		return "index";
	}
	
	@RequestMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("user", usr);
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
	public String submitRegForm(@ModelAttribute ("user") User user, Model model) {
		userService.updateUser(user);
		usr.setUsername(user.getUsername());
		model.addAttribute("user", usr);
		return "index";
	}
	
}
