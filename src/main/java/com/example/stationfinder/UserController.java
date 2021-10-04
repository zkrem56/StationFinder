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
	
	@RequestMapping("/update")
	public String update() {
		return "update";
	}
	
	@GetMapping("/update")
	public String showUpdatePage(Model model) {
		Library libdata = new Library();
		model.addAttribute("libdata", libdata);
		
		return "update";
	}
	
	/*@GetMapping("/result") 
	public String showHome(Model model) { 
		Library libdata =  new Library(); 
		model.addAttribute("libdata", libdata);
	  
		return "search_form"; 
	 }*/
	
	@RequestMapping(value = "/updated", method = RequestMethod.POST)
	public String updated(@ModelAttribute ("libdata") Library libdata, Model model) {
		libService.updateLibrary(libdata);
		return "thanks";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute ("libdata") Library libdata, Model model) {
		libService.deleteLibrary(libdata);
		return "thanks";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute ("libdata") Library libdata, Model model) {
		libService.saveLibrary(libdata);
		return "thanks";
	}
	
	//Library Results
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String submitSearchForm(@ModelAttribute ("libdata") Library libdata, Model model) {
		List<Library> list = libService.getAllTheLibraries();
		List<Library> temp = new ArrayList<>();
		temp.clear();
		
		System.out.println(libdata.getLibName());
		System.out.println(template1.queryForObject("select max(ID) from Libraries", Integer.class));;
		
		for(int i = 1;  i <= template1.queryForObject("select max(ID) from Libraries", Integer.class); i++) {
			try {
				if(libdata.getLibName() != null && libdata.getLibName().equalsIgnoreCase(template1.queryForObject("select Library_Name from Libraries where Library_Name = '"+libdata.getLibName()+"'", String.class))) {
					temp.add(new Library(template1.queryForObject("select ID from Libraries where Library_Name = '"+libdata.getLibName()+"'", Integer.class),
							template1.queryForObject("select Library_Name from Libraries where Library_Name = '"+libdata.getLibName()+"'", String.class),
							template1.queryForObject("select Branch_Name from Libraries where Library_Name = '"+libdata.getLibName()+"'", String.class),
							template1.queryForObject("select Mailing_Address from Libraries where Library_Name = '"+libdata.getLibName()+"'", String.class),
							template1.queryForObject("select City from Libraries where Library_Name = '"+libdata.getLibName()+"'", String.class),
							template1.queryForObject("select Library_Email_Address from Libraries where Library_Name = '"+libdata.getLibName()+"'", String.class),
							template1.queryForObject("select ZIP_Code from Libraries where Library_Name = '"+libdata.getLibName()+"'", Integer.class),
							template1.queryForObject("select County from Libraries where Library_Name = '"+libdata.getLibName()+"'", String.class)));
					break;
				}
				else if(libdata.getAddr() != null && libdata.getAddr().length() > 2 && libdata.getAddr().equalsIgnoreCase(template1.queryForObject("select Mailing_Address from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", String.class))) {
					System.out.println(template1.getFetchSize());
					temp.add(new Library(template1.queryForObject("select ID from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", Integer.class),
							template1.queryForObject("select Library_Name from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", String.class),
							template1.queryForObject("select Branch_Name from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", String.class),
							template1.queryForObject("select Mailing_Address from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", String.class),
							template1.queryForObject("select City from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", String.class),
							template1.queryForObject("select Library_Email_Address from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", String.class),
							template1.queryForObject("select ZIP_Code from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", Integer.class),
							template1.queryForObject("select County from Libraries where Mailing_Address = '"+libdata.getAddr()+"'", String.class)));
					break;
				}
				else if(libdata.getCity() != null && libdata.getCity().length() > 2 && libdata.getCity().equalsIgnoreCase(template1.queryForObject("select City from Libraries where City = '"+libdata.getCity()+"';", String.class )) && template1.queryForObject("select ID from Libraries where City = '"+libdata.getCity()+"'", Integer.class) == i) {
					temp.add(new Library(template1.queryForObject("select ID from Libraries where ID = " + i, Integer.class),
							template1.queryForObject("select Library_Name from Libraries where ID = " + i, String.class),
							template1.queryForObject("select Branch_Name from Libraries where ID = " + i, String.class),
							template1.queryForObject("select Mailing_Address from Libraries where ID = " + i, String.class),
							template1.queryForObject("select City from Libraries where ID = " + i, String.class),
							template1.queryForObject("select Library_Email_Address from Libraries where ID = " + i, String.class),
							template1.queryForObject("select ZIP_Code from Libraries where ID = " + i, Integer.class),
							template1.queryForObject("select County from Libraries where ID = " + i, String.class)));
				}
				else if(libdata.getZip() > 0 && libdata.getZip() == template1.queryForObject("select ZIP_Code from Libraries where ZIP_Code = '"+libdata.getZip()+"'", Integer.class) && template1.queryForObject("select ID from Libraries where ZIP_Code = '"+libdata.getZip()+"'", Integer.class) == i) {
					temp.add(new Library(template1.queryForObject("select ID from Libraries where ID = " + i, Integer.class),
							template1.queryForObject("select Library_Name from Libraries where ID = " + i, String.class),
							template1.queryForObject("select Branch_Name from Libraries where ID = " + 1, String.class),
							template1.queryForObject("select Mailing_Address from Libraries where ID = " + i, String.class),
							template1.queryForObject("select City from Libraries where ID = " + i, String.class),
							template1.queryForObject("select Library_Email_Address from Libraries where ID = " + i, String.class),
							template1.queryForObject("select ZIP_Code from Libraries where ID = " + i, Integer.class),
							template1.queryForObject("select County from Libraries where ID = " + i, String.class)));
				}
				else if(libdata.getCounty() != null && libdata.getCounty().length() > 2 && libdata.getCounty().equalsIgnoreCase(template1.queryForObject("select County from Libraries where County = '"+libdata.getCounty()+"'", String.class)))
					temp.add(new Library(template1.queryForObject("select ID from Libraries where ID = " + i, Integer.class),
							template1.queryForObject("select Library_Name from Libraries where ID = " + i, String.class),
							template1.queryForObject("select Branch_Name from Libraries where ID = " + 1, String.class),
							template1.queryForObject("select Mailing_Address from Libraries where ID = " + i, String.class),
							template1.queryForObject("select City from Libraries where ID = " + i, String.class),
							template1.queryForObject("select Library_Email_Address from Libraries where ID = " + i, String.class),
							template1.queryForObject("select ZIP_Code from Libraries where ID = " + i, Integer.class),
							template1.queryForObject("select County from Libraries where ID = " + i, String.class)));
			}catch(Exception e) {
				System.out.println(e.toString());
				break;
			}
		}
		
		
		model.addAttribute("temp", temp);
		
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
		return "loginUser";
	}
	
	/*@GetMapping("/update")
	public String showUpdatePage(Model model) {
		Library libdata = new Library();
		model.addAttribute("libdata", libdata);
		
		return "update";
	}*/
	
	
}
