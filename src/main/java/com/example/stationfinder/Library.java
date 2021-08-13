package com.example.stationfinder;

public class Library {

	private String name, addr, city, website, county;
	
	public Library() {}
	
	public Library(String name, String addr, String website, String county) {
		this.name = name;
		this.addr = addr;
		this.website = website;
		this.county = county;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
}
