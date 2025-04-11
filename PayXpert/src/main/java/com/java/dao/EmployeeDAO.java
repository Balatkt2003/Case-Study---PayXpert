package com.java.dao;

import com.java.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    boolean addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean removeEmployee(int employeeId);
	Employee addEmployee(String name, String dept, String role);
	
}