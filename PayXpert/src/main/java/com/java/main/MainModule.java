package com.java.main;

import com.java.dao.*;
import com.java.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinancialRecordsDAO financialDAO = new FinancialRecordsDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        PayrollDAO payrollDAO = new PayrollDAOImpl();
        TaxDAO taxDAO = new TaxDAOImpl();

        while (true) {
            System.out.println("\n======= MENU =======");
            System.out.println("1. Add Financial Record");
            System.out.println("2. Get Financial Record by ID");
            System.out.println("3. Get Financial Records for Employee");
            System.out.println("4. Get Financial Records by Date");
            System.out.println("5. Add Employee");
            System.out.println("6. Generate Payroll");
            System.out.println("7. Calculate Tax");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int empId1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amt = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Record Type: ");
                    String type = scanner.nextLine();
                    FinancialRecords record = financialDAO.addFinancialRecord(empId1, desc, amt, type);
                    System.out.println("Record Added with ID: " + record.getRecordID());
                    break;

                case 2:
                    System.out.print("Enter Record ID: ");
                    int recordId = scanner.nextInt();
                    FinancialRecords recById = financialDAO.getFinancialRecordById(recordId);
                    if (recById != null) {
                        System.out.println(recById);
                    } else {
                        System.out.println("Record not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID: ");
                    int empId2 = scanner.nextInt();
                    List<FinancialRecords> empRecords = financialDAO.getFinancialRecordsForEmployee(empId2);
                    if (!empRecords.isEmpty()) {
                        empRecords.forEach(System.out::println);
                    } else {
                        System.out.println("No records found for this employee.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Record Date (yyyy-MM-dd): ");
                        String dateStr = scanner.nextLine();
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                        List<FinancialRecords> recordsByDate = financialDAO.getFinancialRecordsForDate(date);
                        if (!recordsByDate.isEmpty()) {
                            recordsByDate.forEach(System.out::println);
                        } else {
                            System.out.println("No records found for this date.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                    }
                    break;

                case 5:
                    Employee employee = new Employee();
                    System.out.print("Enter Employee ID: ");
                    employee.setEmployeeID(scanner.nextInt());
                    scanner.nextLine(); // Consume newline after nextInt()
                    System.out.print("Enter First Name: ");
                    employee.setFirstName(scanner.nextLine());
                    System.out.print("Enter Last Name: ");
                    employee.setLastName(scanner.nextLine());
                    try {
                        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
                        employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));
                        System.out.print("Enter Joining Date (yyyy-MM-dd): ");
                        employee.setJoiningDate(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                        break;
                    }
                    System.out.print("Enter Gender: ");
                    employee.setGender(scanner.nextLine());
                    System.out.print("Enter Email: ");
                    employee.setEmail(scanner.nextLine());
                    System.out.print("Enter Phone Number: ");
                    try{
                        employee.setPhoneNumber(Integer.parseInt(scanner.nextLine())); // Parse int from line
                    } catch (NumberFormatException e){
                        System.out.println("Invalid phone number format.");
                        break;
                    }
                    System.out.print("Enter Address: ");
                    employee.setAddress(scanner.nextLine());
                    System.out.print("Enter Position: ");
                    employee.setPosition(scanner.nextLine());
                    boolean added = employeeDAO.addEmployee(employee);
                    if (added) {
                        System.out.println("Employee Added.");
                    } else {
                        System.out.println("Failed to add employee.");
                    }
                    break;

                case 6:
                    System.out.print("Enter Employee ID for Payroll: ");
                    int payrollEmpId = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        System.out.print("Enter Pay Period Start Date (yyyy-MM-dd): ");
                        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                        System.out.print("Enter Pay Period End Date (yyyy-MM-dd): ");
                        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                        Payroll payroll = payrollDAO.generatePayroll(payrollEmpId, startDate, endDate);
                        System.out.println("Payroll Generated: " + payroll);
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                    }
                    break;

                case 7:
                    System.out.print("Enter Employee ID for Tax Calculation: ");
                    int taxEmpId = scanner.nextInt();
                    System.out.print("Enter number of year for Tax Calculation: ");
                    int taxYear = scanner.nextInt();
                    Tax tax = taxDAO.calculateTax(taxEmpId, taxYear);
                    System.out.println("Tax Details: " + tax);
                    break;

                case 0:
                    System.out.println("Exiting... Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}