package com.example.stationfinder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.stationdata.Library;

@Service
public class LibService {

	@Autowired
	private JdbcTemplate template;
	
	private List<Library> list = new ArrayList<>();
	public int max_count = 380;
	public LibService(JdbcTemplate template) {
		System.out.println("Service Layer is created");
		
		this.template = template;
		
		for(int i = 1; true; i++) {
			try {
				list.add(new Library(template.queryForObject("select ID from Libraries where ID = " + i, Integer.class),
						template.queryForObject("select Library_Name from Libraries where ID = " + i, String.class),
						template.queryForObject("select Branch_Name from Libraries where ID = " + 1, String.class),
						template.queryForObject("select Mailing_Address from Libraries where ID = " + i, String.class),
						template.queryForObject("select City from Libraries where ID = " + i, String.class),
						template.queryForObject("select Library_Email_Address from Libraries where ID = " + i, String.class),
						template.queryForObject("select ZIP_Code from Libraries where ID = " + i, Integer.class),
						template.queryForObject("select County from Libraries where ID = " + i, String.class)));
			}
			catch (Exception e) {
				System.out.println("Did not work");
				System.out.println(e.toString());
				break;
			}
		}
	}
	
	//Return all the libraries
	public List<Library> getAllTheLibraries(){
		
		return list;
	}
	
	
	//Return Single Library
	public Library getLibrary(int id) {
		for(Library p: list) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	//Save the Player
	public void saveLibrary(Library library) {
		max_count++;
		template.update("insert into Libraries(ID, Library_Name, Branch_Name, Mailing_Address, City, State, ZIP_Code, County, Library_Email_Address) values(?, ?, ?, ?, ?, ?, ?, ?, ?)", max_count, library.getLibName(), library.getBranchName(), library.getAddr(), library.getCity(), null, library.getZip(), library.getCounty(), library.getEmail());
		this.list.add(library);
	}
	
	//Update the Library
	public void updateLibrary(Library library) {
		for(Library p: list) {
			if(p.getId() == library.getId()) {
				p.setLibName(library.getLibName());
			}
		}
	}
	
	//Remove Library
	public void deleteLibrary(int id) {
		template.update("delete * from  Libraries where ID = " + id, Integer.class);
		list.remove(id);
	}
}
