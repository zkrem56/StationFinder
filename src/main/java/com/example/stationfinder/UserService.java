package com.example.stationfinder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private JdbcTemplate template;
	
	private List<User> list = new ArrayList<>();
	public UserService() {
		System.out.println("Service Layer is created");
		
		for(int i = 1; true; i++) {
			try {
				this.list.add(new User(template.queryForObject("select ID from Users where ID = " + i, Integer.class),
						template.queryForObject("select person_name from Users where ID = " + i, String.class), 
						template.queryForObject("select username from Users where ID = " + i, String.class), 
						template.queryForObject("select password from Users where ID = " + i, String.class)));
			}
			catch (Exception e) {
				break;
			}
		}
	}
	
	//Return all the libraries
	public List<User> getAllTheUsers(){
		return list;
	}
	
	
	//Return Single Library
	public User getUser(int id) {
		for(User p: list) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	//Save the Player
	public void saveUser(User user) {
		this.list.add(user);
		template.update("insert into Users(person_name, username, password) values(?, ?, ?)", user.getName(), user.getUsername(), user.getPassword());
	}
	
	//Update the Library
	public void updateUser(User user) {
		for(User p: list) {
			if(p.getId() == user.getId()) {
				p.setName(user.getName());
			}
		}
	}
	
	//Remove Library
	public void deleteUser(int id) {
		list.remove(id);
	}
}
