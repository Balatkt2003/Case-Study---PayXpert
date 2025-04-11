package com.java.test;

import com.java.dao.PayrollDAOImpl;
import com.java.model.Payroll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PayrollCalculationTest {

    private PayrollDAOImpl payrollDAO;

    @BeforeEach
    void setUp() {
        payrollDAO = new PayrollDAOImpl();
    }

    @Test
    void testCalculateGrossSalaryForEmployee() {
        int employeeId = 1;
        Date startDate = new Date(System.currentTimeMillis() - 86400000); // Yesterday
        Date endDate = new Date();

        Payroll payroll = payrollDAO.generatePayroll(employeeId, startDate, endDate);

        assertNotNull(payroll);
        assertEquals(1, payroll.getEmployeeID());
        assertTrue(payroll.getBasicSalary() >= 0);
        assertTrue(payroll.getOverTimePay() >= 0);

        double grossSalary = payroll.getBasicSalary() + payroll.getOverTimePay();
        assertTrue(grossSalary >= 0);
    }

    @Test
    void testCalculateNetSalaryAfterDeductions() {
        int employeeId = 2;
        Date startDate = new Date(System.currentTimeMillis() - 86400000);
        Date endDate = new Date();

        Payroll payroll = payrollDAO.generatePayroll(employeeId, startDate, endDate);

        assertNotNull(payroll);
        assertEquals(2, payroll.getEmployeeID());
        assertTrue(payroll.getDeductions() >= 0);

        double netSalary = payroll.getBasicSalary() + payroll.getOverTimePay() - payroll.getDeductions();
        assertTrue(netSalary >= 0);
    }
}