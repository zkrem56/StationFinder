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

	public int min = 0, max = 0;
	public LibService(JdbcTemplate template) {
		System.out.println("Service Layer is created");
	}
	
	//Return all the libraries
	public List<Library> getAllTheLibraries(Library library, int s){
		
		List<Library> list = new ArrayList<>();
		
		switch(s){
			case 0:
				try {
					min = template.queryForObject("select min(ID) from Libraries where Library_Name = '"+library.getLibName()+"'", Integer.class);
					max = template.queryForObject("select mix(ID) from Libraries where Library_Name = '"+library.getLibName()+"'", Integer.class);
				}catch(Exception e) {
					list.add(new Library("Search Not Found"));
					return list;
				}
				
				
				if(min == max) {
					try {
						list.add(new Library(
								template.queryForObject("select ID from Libraries where Library_Name = '"+library.getLibName()+"'", Integer.class),
								template.queryForObject("select Library_Name from Libraries where Library_Name = '"+library.getLibName()+"'", String.class),
								template.queryForObject("select Branch_Name from Libraries where Library_Name = '"+library.getLibName()+"'", String.class),
								template.queryForObject("select Mailing_Address from Libraries where Library_Name = '"+library.getLibName()+"'", String.class),
								template.queryForObject("select City from Libraries where Library_Name = '"+library.getLibName()+"'", String.class),
								template.queryForObject("select Library_Email_Address from Libraries where Library_Name = '"+library.getLibName()+"'", String.class),
								template.queryForObject("select ZIP_Code from Libraries where Library_Name = '"+library.getLibName()+"'", Integer.class),
								template.queryForObject("select County from Libraries where Library_Name = '"+library.getLibName()+"'", String.class)));
					}catch(Exception e) {
						
					}
				}
				else {
					for(int i = min; i <= max; i++) {
						try {
							
							if(library.getCounty().equalsIgnoreCase(template.queryForObject("select Library_Name from Libraries where ID = "+i, String.class))) {
								list.add(new Library(
										template.queryForObject("select ID from Libraries where ID = "+i, Integer.class),
										template.queryForObject("select Library_Name from Libraries where ID = "+i, String.class),
										template.queryForObject("select Branch_Name from Libraries where ID = "+i, String.class),
										template.queryForObject("select Mailing_Address from Libraries where ID = "+i, String.class),
										template.queryForObject("select City from Libraries where ID = "+i, String.class),
										template.queryForObject("select Library_Email_Address from Libraries where ID = "+i, String.class),
										template.queryForObject("select ZIP_Code from Libraries where ID = "+i, Integer.class),
										template.queryForObject("select County from Libraries where ID = "+i, String.class)));
							}
						}catch(Exception e) {
							
						}
					}
				}
				
				break;
			case 1:
				try {
					min = template.queryForObject("select min(ID) from Libraries where City = '"+library.getCity()+"'", Integer.class);
					max = template.queryForObject("select max(ID) from Libraries where City = '"+library.getCity()+"'", Integer.class);
				}catch(Exception e) {
					list.add(new Library("Search Not Found"));
					return list;
				}
				if(min == max) {
					try {
						list.add(new Library(
								template.queryForObject("select ID from Libraries where City = '"+library.getCity()+"'", Integer.class),
								template.queryForObject("select Library_Name from Libraries where City = '"+library.getCity()+"'", String.class),
								template.queryForObject("select Branch_Name from Libraries where City = '"+library.getCity()+"'", String.class),
								template.queryForObject("select Mailing_Address from Libraries where City = '"+library.getCity()+"'", String.class),
								template.queryForObject("select City from Libraries where City = '"+library.getCity()+"'", String.class),
								template.queryForObject("select Library_Email_Address from Libraries where City = '"+library.getCity()+"'", String.class),
								template.queryForObject("select ZIP_Code from Libraries where City = '"+library.getCity()+"'", Integer.class),
								template.queryForObject("select County from Libraries where City = '"+library.getCity()+"'", String.class)));
					}catch(Exception e) {
						
					}
				}
				else {
					for(int i = min; i <= max; i++) {
						try {
							
							if(library.getCity().equalsIgnoreCase(template.queryForObject("select City from Libraries where ID = "+i, String.class))) {
								list.add(new Library(
										template.queryForObject("select ID from Libraries where ID = "+i, Integer.class),
										template.queryForObject("select Library_Name from Libraries where ID = "+i, String.class),
										template.queryForObject("select Branch_Name from Libraries where ID = "+i, String.class),
										template.queryForObject("select Mailing_Address from Libraries where ID = "+i, String.class),
										template.queryForObject("select City from Libraries where ID = "+i, String.class),
										template.queryForObject("select Library_Email_Address from Libraries where ID = "+i, String.class),
										template.queryForObject("select ZIP_Code from Libraries where ID = "+i, Integer.class),
										template.queryForObject("select County from Libraries where ID = "+i, String.class)));
							}
						}catch(Exception e) {
							
						}
					}
				}
				break;
			case 2:
				try {
					min = template.queryForObject("select min(ID) from Libraries where County = '"+library.getCounty()+"'", Integer.class);
					max = template.queryForObject("select max(ID) from Libraries where County = '"+library.getCounty()+"'", Integer.class);
				}catch(Exception e) {
					list.add(new Library("Search Not Found"));
					return list;
				}
				
				if(max == min) {
					try {
						list.add(new Library(
								template.queryForObject("select ID from Libraries where County = '"+library.getCounty()+"'", Integer.class),
								template.queryForObject("select Library_Name from Libraries where County = '"+library.getCounty()+"'", String.class),
								template.queryForObject("select Branch_Name from Libraries where County = '"+library.getCounty()+"'", String.class),
								template.queryForObject("select Mailing_Address from Libraries where County = '"+library.getCounty()+"'", String.class),
								template.queryForObject("select City from Libraries where County = '"+library.getCounty()+"'", String.class),
								template.queryForObject("select Library_Email_Address from Libraries where County = '"+library.getCounty()+"'", String.class),
								template.queryForObject("select ZIP_Code from Libraries where County = '"+library.getCounty()+"'", Integer.class),
								template.queryForObject("select County from Libraries where County = '"+library.getCounty()+"'", String.class)));
					}catch(Exception e) {
						
					}
				}
				else {
					for(int i = min; i <= max; i++) {
						try {
							
							if(library.getCounty().equalsIgnoreCase(template.queryForObject("select County from Libraries where ID = "+i, String.class))) {
								list.add(new Library(
										template.queryForObject("select ID from Libraries where ID = "+i, Integer.class),
										template.queryForObject("select Library_Name from Libraries where ID = "+i, String.class),
										template.queryForObject("select Branch_Name from Libraries where ID = "+i, String.class),
										template.queryForObject("select Mailing_Address from Libraries where ID = "+i, String.class),
										template.queryForObject("select City from Libraries where ID = "+i, String.class),
										template.queryForObject("select Library_Email_Address from Libraries where ID = "+i, String.class),
										template.queryForObject("select ZIP_Code from Libraries where ID = "+i, Integer.class),
										template.queryForObject("select County from Libraries where ID = "+i, String.class)));
							}
						}catch(Exception e) {
							
						}
					}
				}
				break;
			case 3:
				try {
					list.add(new Library(
							template.queryForObject("select ID from Libraries where Mailing_Address = '"+library.getAddr()+"'", Integer.class),
							template.queryForObject("select Library_Name from Libraries where Mailing_Address = '"+library.getAddr()+"'", String.class),
							template.queryForObject("select Branch_Name from Libraries where Mailing_Address = '"+library.getAddr()+"'", String.class),
							template.queryForObject("select Mailing_Address from Libraries where Mailing_Address = '"+library.getAddr()+"'", String.class),
							template.queryForObject("select City from Libraries where Mailing_Address = '"+library.getAddr()+"'", String.class),
							template.queryForObject("select Library_Email_Address from Libraries where Mailing_Address = '"+library.getAddr()+"'", String.class),
							template.queryForObject("select ZIP_Code from Libraries where Mailing_Address = '"+library.getAddr()+"'", Integer.class),
							template.queryForObject("select County from Libraries where Mailing_Address = '"+library.getAddr()+"'", String.class)));
				}catch(Exception e) {
					
				}
				break;
			case 4:
				try {
					list.add(new Library(
							template.queryForObject("select ID from Libraries where ZIP_Code = "+library.getZip(), Integer.class),
							template.queryForObject("select Library_Name from Libraries where ZIP_Code = "+library.getZip(), String.class),
							template.queryForObject("select Branch_Name from Libraries where ZIP_Code = "+library.getZip(), String.class),
							template.queryForObject("select Mailing_Address from Libraries where ZIP_Code = "+library.getZip(), String.class),
							template.queryForObject("select City from Libraries where ZIP_Code = "+library.getZip(), String.class),
							template.queryForObject("select Library_Email_Address from Libraries where ZIP_Code = "+library.getZip(), String.class),
							template.queryForObject("select ZIP_Code from Libraries where ZIP_Code = "+library.getZip(), Integer.class),
							template.queryForObject("select County from Libraries where ZIP_Code = "+library.getZip(), String.class)));
				}catch(Exception e) {
					
				}
				break;
			case 5:
				try {
					list.add(new Library(
							template.queryForObject("select ID from Libraries where Branch_Name = '"+library.getBranchName()+"'", Integer.class),
							template.queryForObject("select Library_Name from Libraries where Branch_Name = '"+library.getBranchName()+"'", String.class),
							template.queryForObject("select Branch_Name from Libraries where Branch_Name = '"+library.getBranchName()+"'", String.class),
							template.queryForObject("select Mailing_Address from Libraries where Branch_Name = '"+library.getBranchName()+"'", String.class),
							template.queryForObject("select City from Libraries where Branch_Name = '"+library.getBranchName()+"'", String.class),
							template.queryForObject("select Library_Email_Address from Libraries where Branch_Name = '"+library.getBranchName()+"'", String.class),
							template.queryForObject("select ZIP_Code from Libraries where Branch_Name = '"+library.getBranchName()+"'", Integer.class),
							template.queryForObject("select County from Libraries where Branch_Name = '"+library.getBranchName()+"'", String.class)));
				}catch(Exception e) {
					
				}
				break;
			default:
				list.add(new Library("Search Not Found"));
				break;
		}
		
		
		return list;
	}
	
	//Save the Player
	public void saveLibrary(Library library) {
		template.update("insert into Libraries(ID, Library_Name, Branch_Name, Mailing_Address, City, State, ZIP_Code, County, Library_Email_Address) values(?, ?, ?, ?, ?, ?, ?, ?, ?)", template.queryForObject("select max(ID) from Libraries", Integer.class), library.getLibName(), library.getBranchName(), library.getAddr(), library.getCity(), null, library.getZip(), library.getCounty(), library.getEmail());
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
