package com.example.stationdata;

import java.util.List;

public class Library {

	private String name, addr, city, website, county;
	private int id;
	
	public Library() {}
	
	public Library(String name) {
		this.name = name;
	}
	
	public Library(int id, String name, String addr, String city, String website, String county) {
		this.id = id;
		this.name = name;
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
	
	public String toString() {
		return "" + this.id + " " + this.name + " " + this.addr + " " + this.city + " " + this.website + " " + this.county + "\n";
	}
}
