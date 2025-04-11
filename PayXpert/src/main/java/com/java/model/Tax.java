package com.java.model;

public class Tax {
	private int TaxID;
	public int getTaxID() {
		return TaxID;
	}
	public void setTaxID(int taxID) {
		TaxID = taxID;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public int getTaxYear() {
		return TaxYear;
	}
	public void setTaxYear(int taxYear) {
		TaxYear = taxYear;
	}
	private int EmployeeID;
	public Tax(int taxID, int employeeID, int taxYear) {
		super();
		TaxID = taxID;
		EmployeeID = employeeID;
		TaxYear = taxYear;
	}
	private int TaxYear;
	public Tax() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setTaxAmount(double double1) {
		// TODO Auto-generated method stub
		
	}
	public int getTax() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getTaxAmount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setTaxableIncome(double double1) {
		// TODO Auto-generated method stub
		
	}
	public int getTaxableIncome() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
