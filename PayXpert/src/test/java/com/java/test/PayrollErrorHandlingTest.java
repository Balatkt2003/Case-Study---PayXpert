package com.java.test;

import com.java.dao.EmployeeDAOImpl;
import com.java.dao.PayrollDAOImpl;
import com.java.model.Employee;
import com.java.model.Payroll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PayrollErrorHandlingTest {

    private EmployeeDAOImpl employeeDAO;
    private PayrollDAOImpl payrollDAO;

    @BeforeEach
    void setUp() {
        employeeDAO = new EmployeeDAOImpl();
        payrollDAO = new PayrollDAOImpl();
    }

    @Test
    void testVerifyErrorHandlingForInvalidEmployeeData() {
        Employee tempEmployee = new Employee();
        tempEmployee.setEmployeeID(9999);
        tempEmployee.setFirstName("Temp");
        tempEmployee.setLastName("Employee");
        tempEmployee.setDateOfBirth(new Date());
        tempEmployee.setJoiningDate(new Date());
        tempEmployee.setGender("Other");
        tempEmployee.setEmail("temp@example.com");
        tempEmployee.setPhoneNumber(123456789);
        tempEmployee.setAddress("Temp Address");
        tempEmployee.setPosition("Temp Position");

        //check if employee exists before adding.
        if (employeeDAO.getEmployeeById(9999) == null) {
            employeeDAO.addEmployee(tempEmployee);
        }

        Date startDate = new Date(System.currentTimeMillis() - 86400000);
        Date endDate = new Date();

        Payroll payroll = payrollDAO.generatePayroll(9999, startDate, endDate);
        assertNotNull(payroll);
        assertEquals(9999, payroll.getEmployeeID());

        employeeDAO.removeEmployee(9999);

        // Test with invalid date range
        startDate = new Date();
        endDate = new Date(System.currentTimeMillis() - 86400000); // Yesterday

        payroll = payrollDAO.generatePayroll(1, endDate, startDate);
        assertNull(payroll);

        Employee employee = new Employee();
        employee.setEmployeeID(7);
        employee.setFirstName("test");
        employee.setLastName("test");
        employee.setDateOfBirth(new Date());
        employee.setJoiningDate(new Date());
        employee.setGender("test");
        employee.setEmail("test");
        employee.setPhoneNumber(-1);
        employee.setAddress("test");
        employee.setPosition("test");

        boolean employeeAdded = employeeDAO.addEmployee(employee);
        assertTrue(employeeAdded);

        Employee retrievedEmployee = employeeDAO.getEmployeeById(7);
        assertEquals(-1, retrievedEmployee.getPhoneNumber());
    }

    @Test
    void testGetPayrollById() {
        // Assuming a payroll record with ID 1 exists in the database
        int payrollId = 1;

        Payroll payroll = payrollDAO.getPayrollById(payrollId);

        if (payroll != null) {
            assertEquals(payrollId, payroll.getRecordID());
            assertNotNull(payroll.getPayPeriodStartDate());
            assertNotNull(payroll.getPayPeriodEndDate());
            assertTrue(payroll.getBasicSalary() >= 0);
            assertTrue(payroll.getOverTimePay() >= 0);
            assertTrue(payroll.getDeductions() >= 0);
        } else {
            System.out.println("Warning: Payroll record with ID " + payrollId + " not found in database. Test may be invalid.");
        }
    }

    @Test
    void testGetPayrollsForEmployee() {
        int employeeId = 1;

        List<Payroll> payrolls = payrollDAO.getPayrollsForEmployee(employeeId);

        assertNotNull(payrolls);
        for (Payroll payroll : payrolls) {
            assertEquals(employeeId, payroll.getEmployeeID());
            assertNotNull(payroll.getPayPeriodStartDate());
            assertNotNull(payroll.getPayPeriodEndDate());
            assertTrue(payroll.getBasicSalary() >= 0);
            assertTrue(payroll.getOverTimePay() >= 0);
            assertTrue(payroll.getDeductions() >= 0);
        }
    }

    @Test
    void testGetPayrollsForPeriod() {
        Date startDate = new Date(System.currentTimeMillis() - 86400000 * 7); // 7 days ago
        Date endDate = new Date();

        List<Payroll> payrolls = payrollDAO.getPayrollsForPeriod(startDate, endDate);

        assertNotNull(payrolls);
        for (Payroll payroll : payrolls) {
            assertTrue(payroll.getPayPeriodStartDate().compareTo(startDate) >= 0);
            assertTrue(payroll.getPayPeriodEndDate().compareTo(endDate) <= 0);
            assertTrue(payroll.getBasicSalary() >= 0);
            assertTrue(payroll.getOverTimePay() >= 0);
            assertTrue(payroll.getDeductions() >= 0);
        }
    }
}