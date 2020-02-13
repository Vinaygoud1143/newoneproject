package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;

@Entity
public class Student {

	@Id
	
	private int sno;
	private String sname;
	private String sdepartment;
	private String saddress;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSdepartment() {
		return sdepartment;
	}
	public void setSdepartment(String sdepartment) {
		this.sdepartment = sdepartment;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	
	
	
	
}
