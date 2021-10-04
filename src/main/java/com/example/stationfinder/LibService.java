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

	public int max_count = 380;
	public LibService(JdbcTemplate template) {
		System.out.println("Service Layer is created");
	}
	
	//Return all the libraries
	public List<Library> getAllTheLibraries(){
		
		List<Library> list = new ArrayList<>();
		
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
		return list;
	}
	
	//Save the Player
	public void saveLibrary(Library library) {
		template.update("insert into Libraries(ID, Library_Name, Branch_Name, Mailing_Address, City, State, ZIP_Code, County, Library_Email_Address) values(?, ?, ?, ?, ?, ?, ?, ?, ?)", max_count, library.getLibName(), library.getBranchName(), library.getAddr(), library.getCity(), null, library.getZip(), library.getCounty(), library.getEmail());
	}
	
	//Update the Library
	public void updateLibrary(Library library) {
		
				try {
					if(library.getLibName() != null) {
						template.update("update Libraries set Library_Name='"+ library.getLibName() +"' where Mailing_Address = '"+ library.getAddr() +"'");
					}
					
					if(library.getBranchName() != null) {
						template.update("update Libraries set Branch_Name='"+library.getBranchName()+"' where Mailling_Address = '"+library.getAddr()+"'");
					}
					
					if(library.getCity() != null && library.getCity().isEmpty() == false) {
						template.update("update Libraries set City = '"+ library.getCity() +"' where Mailing_Address = '"+ library.getAddr() +"'");
					}
					
					if(library.getZip() != 0 && library.getZip() > 0) {
						template.update("update Libraries set ZIP_Code = '"+library.getZip() +"' where Mailing_Address = '"+ library.getAddr() +"'");
					}
					
					if(library.getCounty() != null && library.getCounty().isBlank() == false) {
						template.update("update Libraries set County = '"+library.getCounty()+"' where Mailing_Address = '"+ library.getAddr() +"'");
					}
					
					/*if(library.getEmail() != null) {
						p.setEmail(library.getEmail());
						template.update("update Libraries set Library_Email_Address = '"+ library.getEmail() +"' where ID = '"+p.getId()+"'");
					}*/
				} catch(Exception e) {
					System.out.println(e.toString());
				}
				
	}
	
	//Remove Library
	public void deleteLibrary(Library library) {
		template.update("delete * from Libraries where Mailing_Address = '"+library.getAddr()+"'");
		
	}
	
	public int findId(List<Library> lib, Library library) {
		int temp = 0;
		for(int i = 0; i < lib.size(); i++) {
			if(lib.get(i).getCity().equalsIgnoreCase(library.getCity())) {
				temp =  lib.get(i).getId();
				break;
			}
		}
		
		return temp;
	}
}
