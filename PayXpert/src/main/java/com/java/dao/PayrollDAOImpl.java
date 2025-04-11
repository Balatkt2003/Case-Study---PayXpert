package com.java.dao;

import com.java.model.Payroll;
import com.java.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PayrollDAOImpl implements PayrollDAO {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Payroll getPayrollById(int payrollId) {
        Payroll payroll = null;
        String sql = "SELECT * FROM Payroll WHERE RecordID = ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payrollId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                payroll = extractPayroll(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return payroll;
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> payrollList = new ArrayList<>();
        String sql = "SELECT * FROM Payroll WHERE EmployeeID = ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                payrollList.add(extractPayroll(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payrollList;
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(Date startDate, Date endDate) {
        List<Payroll> payrollList = new ArrayList<>();
        String sql = "SELECT * FROM Payroll WHERE PayPeriodStartDate >= ? AND PayPeriodEndDate <= ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                payrollList.add(extractPayroll(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payrollList;
    }

    @Override
    public Payroll generatePayroll(int employeeId, Date startDate, Date endDate) {
        Payroll payroll = new Payroll();
        payroll.setEmployeeID(employeeId);
        payroll.setPayPeriodStartDate(startDate);
        payroll.setPayPeriodEndDate(endDate);

        // Taking user input for payroll details
        System.out.print("Enter basic salary for employee ID " + employeeId + ": ");
        double basicSalary = scanner.nextDouble();

        System.out.print("Enter overtime pay: ");
        double overTimePay = scanner.nextDouble();

        System.out.print("Enter deductions: ");
        double deductions = scanner.nextDouble();

        payroll.setBasicSalary(basicSalary);
        payroll.setOverTimePay(overTimePay);
        payroll.setDeductions(deductions);

        String sql = "INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OverTimePay, Deductions) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, employeeId);
            ps.setDate(2, new java.sql.Date(startDate.getTime()));
            ps.setDate(3, new java.sql.Date(endDate.getTime()));
            ps.setDouble(4, basicSalary);
            ps.setDouble(5, overTimePay);
            ps.setDouble(6, deductions);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    payroll.setRecordID(rs.getInt(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payroll;
    }

    private Payroll extractPayroll(ResultSet rs) throws SQLException {
        Payroll payroll = new Payroll();
        payroll.setRecordID(rs.getInt("RecordID"));
        payroll.setEmployeeID(rs.getInt("EmployeeID"));
        payroll.setPayPeriodStartDate(rs.getDate("PayPeriodStartDate"));
        payroll.setPayPeriodEndDate(rs.getDate("PayPeriodEndDate"));
        payroll.setBasicSalary(rs.getDouble("BasicSalary"));
        payroll.setOverTimePay(rs.getDouble("OverTimePay"));
        payroll.setDeductions(rs.getDouble("Deductions"));
        return payroll;
    }

	@Override
	public Payroll generatePayroll(int payrollEmpId, double salary) {
		// TODO Auto-generated method stub
		return null;
	}
}
