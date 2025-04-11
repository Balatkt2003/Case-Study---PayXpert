package com.java.dao;

import com.java.model.Payroll;
import java.util.Date;
import java.util.List;

public interface PayrollDAO {
    Payroll getPayrollById(int payrollId);
    List<Payroll> getPayrollsForEmployee(int employeeId);
    List<Payroll> getPayrollsForPeriod(Date startDate, Date endDate);
    Payroll generatePayroll(int employeeId, Date startDate, Date endDate);
	Payroll generatePayroll(int payrollEmpId, double salary);
}