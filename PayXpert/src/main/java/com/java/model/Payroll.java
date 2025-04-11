package com.java.model;

import java.util.Date;

public class Payroll {
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
	public Date getPayPeriodStartDate() {
		return PayPeriodStartDate;
	}
	public Payroll(int recordID, int employeeID, Date payPeriodStartDate, Date payPeriodEndDate, double basicSalary,
			double overTimePay, double deductions) {
		super();
		RecordID = recordID;
		EmployeeID = employeeID;
		PayPeriodStartDate = payPeriodStartDate;
		PayPeriodEndDate = payPeriodEndDate;
		BasicSalary = basicSalary;
		OverTimePay = overTimePay;
		Deductions = deductions;
	}
	public Payroll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setPayPeriodStartDate(Date payPeriodStartDate) {
		PayPeriodStartDate = payPeriodStartDate;
	}
	public Date getPayPeriodEndDate() {
		return PayPeriodEndDate;
	}
	public void setPayPeriodEndDate(Date payPeriodEndDate) {
		PayPeriodEndDate = payPeriodEndDate;
	}
	public double getBasicSalary() {
		return BasicSalary;
	}
	public void setBasicSalary(double basicSalary) {
		BasicSalary = basicSalary;
	}
	public double getOverTimePay() {
		return OverTimePay;
	}
	public void setOverTimePay(double overTimePay) {
		OverTimePay = overTimePay;
	}
	public double getDeductions() {
		return Deductions;
	}
	public void setDeductions(double deductions) {
		Deductions = deductions;
	}
	private int EmployeeID;
    private Date PayPeriodStartDate;
    private Date PayPeriodEndDate;
    private double BasicSalary;
    private double OverTimePay;
    private double Deductions;

}
