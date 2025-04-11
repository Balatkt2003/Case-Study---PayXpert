package com.java.test;

import com.java.dao.EmployeeDAOImpl;
import com.java.dao.PayrollDAOImpl;
import com.java.model.Employee;
import com.java.model.Payroll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PayrollProcessingTest {

    private PayrollDAOImpl payrollDAO;

    @BeforeEach
    void setUp() {
        payrollDAO = new PayrollDAOImpl();
    }

    @Test
    void testProcessPayrollForMultipleEmployees() {
        List<Integer> employeeIds = new ArrayList<>();
        employeeIds.add(4);
        employeeIds.add(5);
        employeeIds.add(6);

        Date startDate = new Date(System.currentTimeMillis() - 86400000);
        Date endDate = new Date();

        List<Payroll> payrolls = new ArrayList<>();
        for (Integer employeeId : employeeIds) {
            payrolls.add(payrollDAO.generatePayroll(employeeId, startDate, endDate));
        }

        assertEquals(employeeIds.size(), payrolls.size());
        for (Payroll payroll : payrolls) {
            assertNotNull(payroll);
            assertTrue(payroll.getBasicSalary() >= 0);
        }
    }
}