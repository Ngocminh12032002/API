package com.springbootsoap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "employee_id")
	private long employeeId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "department")
	private String department;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	public Employee() {
		super();
	}

	public Employee(String name, String department, String phone, String address) {
		super();
		this.name = name;
		this.department = department;
		this.phone = phone;
		this.address = address;
	}

	public Employee(long employeeId, String name, String department, String phone, String address) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.phone = phone;
		this.address = address;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
}
