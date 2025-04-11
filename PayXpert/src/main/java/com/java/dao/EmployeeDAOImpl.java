package com.java.dao;

import com.java.model.Employee;
import com.java.util.ConnectionHelper;

import java.sql.*;
import java.util.*;

public class EmployeeDAOImpl implements EmployeeDAO {

    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM employee WHERE EmployeeID = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractEmployee(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = ConnectionHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(extractEmployee(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (EmployeeID, FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setEmployee(ps, employee);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, Email = ?, PhoneNumber = ?, Address = ?, Position = ?, JoiningDate = ? WHERE EmployeeID = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setDate(3, new java.sql.Date(employee.getDateOfBirth().getTime()));
            ps.setString(4, employee.getGender());
            ps.setString(5, employee.getEmail());
            ps.setInt(6, employee.getPhoneNumber());
            ps.setString(7, employee.getAddress());
            ps.setString(8, employee.getPosition());
            ps.setDate(9, new java.sql.Date(employee.getJoiningDate().getTime()));
            ps.setInt(10, employee.getEmployeeID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeEmployee(int employeeId) {
        String sql = "DELETE FROM employee WHERE EmployeeID = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Employee extractEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("EmployeeID"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getDate("DateOfBirth"),
                rs.getString("Gender"),
                rs.getString("Email"),
                rs.getInt("PhoneNumber"),
                rs.getString("Address"),
                rs.getString("Position"),
                rs.getDate("JoiningDate")
        );
    }

    private void setEmployee(PreparedStatement ps, Employee employee) throws SQLException {
        ps.setInt(1, employee.getEmployeeID());
        ps.setString(2, employee.getFirstName());
        ps.setString(3, employee.getLastName());
        ps.setDate(4, new java.sql.Date(employee.getDateOfBirth().getTime()));
        ps.setString(5, employee.getGender());
        ps.setString(6, employee.getEmail());
        ps.setInt(7, employee.getPhoneNumber());
        ps.setString(8, employee.getAddress());
        ps.setString(9, employee.getPosition());
        ps.setDate(10, new java.sql.Date(employee.getJoiningDate().getTime()));
    }

	@Override
	public Employee addEmployee(String name, String dept, String role) {
		// TODO Auto-generated method stub
		return null;
	}
}
