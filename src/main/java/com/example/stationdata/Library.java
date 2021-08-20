package com.example.stationdata;

import java.util.List;

public class Library {

	private String libname, addr, city, website, county;
	private int id;
	
	public Library() {}
	
	public Library(String libname) {
		this.libname = libname;
	}
	public Library(String libname, String addr, String city, String county) {
		this.libname = libname;
		this.addr = addr;
		this.city = city;
		this.county = county;
	}
	
	public Library(int id, String libname, String addr, String city, String website, String county) {
		this.id = id;
		this.libname = libname;
		this.addr = addr;
		this.city = city;
		this.website = website;
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
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getWeb() {
		return website;
	}
	
	public void setWeb(String website) {
		this.website = website;
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
		return "" + this.id + " " + this.libname + " " + this.addr + " " + this.city + " " + this.website + " " + this.county + "\n";
	}
}
