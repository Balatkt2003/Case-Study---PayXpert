package com.java.dao;

import com.java.model.Tax;
import java.util.List;

public interface TaxDAO {
    Tax calculateTax(int employeeId, int taxYear);
    Tax getTaxById(int taxId);
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);
}