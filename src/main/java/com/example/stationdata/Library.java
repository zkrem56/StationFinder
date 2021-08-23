package com.example.stationdata;

import java.util.List;

public class Library {

	private String libname, branchname, addr, city, email, county;
	private int id, zip;
	
	public Library() {}
	
	public Library(String libname) {
		this.libname = libname;
	}
	public Library(String libname, String addr, String city, int zip, String county) {
		this.libname = libname;
		this.addr = addr;
		this.city = city;
		this.zip = zip;
		this.county = county;
	}
	
	public Library(int id, String libname, String branchname, String addr, String city, String email, int zip, String county) {
		this.id = id;
		this.libname = libname;
		this.branchname = branchname;
		this.addr = addr;
		this.city = city;
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
		return libname;
	}
	
	public void setLibName(String libname) {
		this.libname = libname;
	}
	
	public String getBranchName() {
		return branchname;
	}
	
	public void setBranchName(String branchname) {
		this.branchname = branchname;
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
		return "" + this.id + " " + this.libname + " " + this.addr + " " + this.city + " " + this.email + " " + this.county + "\n";
	}
}
