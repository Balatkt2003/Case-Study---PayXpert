package com.java.dao;

import com.java.model.FinancialRecords;
import java.util.Date;
import java.util.List;

public interface FinancialRecordsDAO {
    FinancialRecords addFinancialRecord(int employeeId, String description, double amount, String recordType);
    FinancialRecords getFinancialRecordById(int recordId);
    List<FinancialRecords> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecords> getFinancialRecordsForDate(Date recordDate);
}