package com.java.model;

import java.util.Date;


public class FinancialRecords {
	private int RecordID;
	public int getRecordID() {
		return RecordID;
	}
	public void setRecordID(int recordID) {
		RecordID = recordID;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public Date getRecordDate() {
		return RecordDate;
	}
	public void setRecordDate(Date recordDate) {
		RecordDate = recordDate;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount2) {
		Amount = amount2;
	}
	public String getRecordType() {
		return RecordType;
	}
	public void setRecordType(String recordType) {
		RecordType = recordType;
	}
	public FinancialRecords(int recordID, int employeeID, Date recordDate, String description, int amount,
			String recordType) {
		super();
		RecordID = recordID;
		EmployeeID = employeeID;
		RecordDate = recordDate;
		Description = description;
		Amount = amount;
		RecordType = recordType;
	}
	public FinancialRecords() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int EmployeeID;
	private Date RecordDate;
	private String Description;
	private double Amount;
	private String RecordType;
	

}
