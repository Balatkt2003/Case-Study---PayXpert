package com.java.dao;

import com.java.model.FinancialRecords;
import com.java.util.ConnectionHelper;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class FinancialRecordsDAOImpl implements FinancialRecordsDAO {

    @Override
    public FinancialRecords addFinancialRecord(int employeeId, String description, double amount, String recordType) {
        FinancialRecords record = new FinancialRecords();
        record.setEmployeeID(employeeId);
        record.setDescription(description);
        record.setAmount(amount);
        record.setRecordType(recordType);
        record.setRecordDate(new java.sql.Date(System.currentTimeMillis()));

        String sql = "INSERT INTO FinancialRecord (EmployeeID, Description, Amount, RecordType, RecordDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, employeeId);
            ps.setString(2, description);
            ps.setDouble(3, amount);
            ps.setString(4, recordType);
            ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    record.setRecordID(rs.getInt(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return record;
    }

    @Override
    public FinancialRecords getFinancialRecordById(int recordId) {
        FinancialRecords record = null;
        String sql = "SELECT * FROM FinancialRecord WHERE RecordID = ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, recordId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                record = extractRecord(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return record;
    }

    @Override
    public List<FinancialRecords> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecords> records = new ArrayList<>();
        String sql = "SELECT * FROM FinancialRecord WHERE EmployeeID = ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                records.add(extractRecord(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<FinancialRecords> getFinancialRecordsForDate(Date recordDate) {
        List<FinancialRecords> records = new ArrayList<>();
        String sql = "SELECT * FROM FinancialRecord WHERE RecordDate = ?";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(recordDate.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                records.add(extractRecord(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    private FinancialRecords extractRecord(ResultSet rs) throws SQLException {
        FinancialRecords record = new FinancialRecords();
        record.setRecordID(rs.getInt("RecordID"));
        record.setEmployeeID(rs.getInt("EmployeeID"));
        record.setDescription(rs.getString("Description"));
        record.setAmount(rs.getDouble("Amount"));
        record.setRecordType(rs.getString("RecordType"));
        record.setRecordDate(rs.getDate("RecordDate"));
        return record;
    }

	
}