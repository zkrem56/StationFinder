package com.example.stationdata;

import java.util.List;

public class Library {

	private String libName, branchName, addr, city, email, county, state;
	private int id, zip;
	
	public Library() {}
	
	public Library(String libName) {
		this.libName = libName;
	}
	public Library(String libName, String branchName, String addr, String city, int zip, String county) {
		this.libName = libName;
		this.branchName = branchName;
		this.addr = addr;
		this.city = city;
		this.zip = zip;
		this.county = county;
	}
	
	public Library(int id, String libName, String branchName, String addr, String city, String email, int zip, String county) {
		this.id = id;
		this.libName = libName;
		this.branchName = branchName;
		this.addr = addr;
		this.city = city;
		this.email = email;
		this.zip = zip;
		this.county = county;
	}
	
	public Library(int id, String libName, String branchName, String addr, String city, String state, String email, int zip, String county) {
		this.id = id;
		this.libName = libName;
		this.branchName = branchName;
		this.addr = addr;
		this.city = city;
		this.setState(state);
		this.email = email;
		this.zip = zip;
		this.county = county;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLibName() {
		return libName;
	}
	
	public void setLibName(String libName) {
		this.libName = libName;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getZip() {
		return zip;
	}
	
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public String getCounty() {
		return county;
	}
	
	public void setCounty(String county) {
		this.county = county;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString() {
		return "" + this.id + "\n" + this.libName + "\n" + this.branchName + "\n" + this.addr + "\n" + this.city + "\n" + this.zip + "\n" + this.county + "\n";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
