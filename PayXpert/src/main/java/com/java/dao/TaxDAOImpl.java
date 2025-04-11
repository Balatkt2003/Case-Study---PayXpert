package com.java.dao;

import com.java.model.Tax;
import com.java.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaxDAOImpl implements TaxDAO {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Tax calculateTax(int employeeId, int taxYear) {
        Tax tax = new Tax();
        tax.setEmployeeID(employeeId);
        tax.setTaxYear(taxYear);

        double income = getEmployeeIncome(employeeId, taxYear);
        double taxRate = getTaxRate(income, taxYear);
        double taxAmount = income * taxRate;

        tax.setTaxAmount(taxAmount);
        tax.setTaxableIncome(income);

        String sql = "INSERT INTO Tax (EmployeeID, TaxYear, TaxableIncome, TaxAmount) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, employeeId);
            ps.setInt(2, taxYear);
            ps.setDouble(3, income);
            ps.setDouble(4, taxAmount);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    tax.setTaxID(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        return tax;
    }

    private double getEmployeeIncome(int employeeId, int taxYear) {
        double income = 0;
        String sql = "SELECT SUM(BasicSalary) FROM Payroll WHERE EmployeeID = ? AND YEAR(PayPeriodEndDate) = ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setInt(2, taxYear);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                income = rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        return income;
    }

    private double getTaxRate(double income, int taxYear) {
        // Example tax rate calculation based on income and year.
        // Replace with your actual tax rate logic.
        if (taxYear == 2024) {
            if (income > 50000) {
                return 0.25;
            } else if (income > 20000) {
                return 0.15;
            } else {
                return 0.05;
            }
        } else {
            // Default tax rate or logic for other years.
            return 0.10;
        }
    }

    @Override
    public Tax getTaxById(int taxId) {
        Tax tax = null;
        String sql = "SELECT * FROM Tax WHERE TaxID = ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, taxId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tax = extractTax(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        return tax;
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> taxList = new ArrayList<>();
        String sql = "SELECT * FROM Tax WHERE EmployeeID = ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                taxList.add(extractTax(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        return taxList;
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> taxList = new ArrayList<>();
        String sql = "SELECT * FROM Tax WHERE TaxYear = ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, taxYear);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                taxList.add(extractTax(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        return taxList;
    }

    private Tax extractTax(ResultSet rs) throws SQLException {
        Tax tax = new Tax();
        tax.setTaxID(rs.getInt("TaxID"));
        tax.setEmployeeID(rs.getInt("EmployeeID"));
        tax.setTaxYear(rs.getInt("TaxYear"));
        tax.setTaxAmount(rs.getDouble("TaxAmount"));
        tax.setTaxableIncome(rs.getDouble("TaxableIncome"));
        return tax;
    }
}