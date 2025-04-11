package com.java.model;

import java.util.Date;


public class Employee {
	private int EmployeeID;
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public Date getJoiningDate() {
		return JoiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		JoiningDate = joiningDate;
	}
	public Employee(int employeeID, String firstName, String lastName, Date dateOfBirth, String gender, String email,
			int phoneNumber, String address, String position, Date joiningDate) {
		super();
		EmployeeID = employeeID;
		FirstName = firstName;
		LastName = lastName;
		this.dateOfBirth = dateOfBirth;
		Gender = gender;
		Email = email;
		PhoneNumber = phoneNumber;
		Address = address;
		Position = position;
		JoiningDate = joiningDate;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private String  FirstName;
	private String LastName;
	private Date dateOfBirth;
	private String Gender;
	private String Email;
	private int PhoneNumber;
	private String Address;
	private String Position;
	private Date JoiningDate;

}
