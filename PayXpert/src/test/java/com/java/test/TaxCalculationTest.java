package com.java.test;

import com.java.dao.TaxDAOImpl;
import com.java.model.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaxCalculationTest {

    private TaxDAOImpl taxDAO;

    @BeforeEach
    void setUp() {
        taxDAO = new TaxDAOImpl();
    }

    @Test
    void testCalculateTax() {
        int employeeId = 1;
        int taxYear = 2024;

        Tax tax = taxDAO.calculateTax(employeeId, taxYear);

        assertNotNull(tax);
        assertEquals(employeeId, tax.getEmployeeID());
        assertEquals(taxYear, tax.getTaxYear());
        assertTrue(tax.getTaxAmount() >= 0);
        assertTrue(tax.getTaxableIncome() >= 0);
    }

    @Test
    void testGetTaxById() {
        // Assuming a tax record with ID 1 exists in the database
        int taxId = 1;

        Tax tax = taxDAO.getTaxById(taxId);

        if(tax != null) {
          assertEquals(taxId, tax.getTaxID());
          assertTrue(tax.getTaxAmount() >= 0);
          assertTrue(tax.getTaxableIncome() >= 0);
        } else {
          System.out.println("Warning: Tax record with ID "+taxId+" not found in database. Test may be invalid.");
        }
    }

    @Test
    void testGetTaxesForEmployee() {
        int employeeId = 1;

        List<Tax> taxes = taxDAO.getTaxesForEmployee(employeeId);

        assertNotNull(taxes);
        for (Tax tax : taxes) {
            assertEquals(employeeId, tax.getEmployeeID());
            assertTrue(tax.getTaxAmount() >= 0);
            assertTrue(tax.getTaxableIncome() >= 0);
        }
    }

    @Test
    void testGetTaxesForYear() {
        int taxYear = 2024;

        List<Tax> taxes = taxDAO.getTaxesForYear(taxYear);

        assertNotNull(taxes);
        for (Tax tax : taxes) {
            assertEquals(taxYear, tax.getTaxYear());
            assertTrue(tax.getTaxAmount() >= 0);
            assertTrue(tax.getTaxableIncome() >= 0);
        }
    }

    @Test
    void testTaxCalculationWithHighIncome() {
        int employeeId = 3;
        int taxYear = 2024;

        Tax tax = taxDAO.calculateTax(employeeId, taxYear);

        assertNotNull(tax);
        assertTrue(tax.getTaxAmount() >= 0);
        if(tax.getTaxableIncome() > 50000) {
            assertTrue(tax.getTaxAmount() >= 12500); // 25% tax rate on income above 50000
        }
    }
}