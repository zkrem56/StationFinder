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
	public LibService(JdbcTemplate template) {
		System.out.println("Service Layer is created");
		
		this.template = template;
		
		for(int i = 1; true; i++) {
			try {
				list.add(new Library(template.queryForObject("select ID from Libraries where ID = " + i, Integer.class),
						template.queryForObject("select lib_name from Libraries where ID = " + i, String.class),
						template.queryForObject("select lib_address from Libraries where ID = " + i, String.class),
						template.queryForObject("select lib_city from Libraries where ID = " + i, String.class),
						template.queryForObject("select lib_web from Libraries where ID = " + i, String.class),
						template.queryForObject("select lib_county from Libraries where ID = " + i, String.class)));
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
		list.remove(id);
	}
}
