package com.dao;

import com.model.FeePayment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeePaymentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/college_fee_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Replace with your MySQL password

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }

    public void addFeePayment(FeePayment payment) throws SQLException {
        String sql = "INSERT INTO FeePayments (StudentID, StudentName, PaymentDate, Amount, Status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getStudentID());
            stmt.setString(2, payment.getStudentName());
            stmt.setDate(3, payment.getPaymentDate());
            stmt.setDouble(4, payment.getAmount());
            stmt.setString(5, payment.getStatus());
            stmt.executeUpdate();
        }
    }

    public void updateFeePayment(FeePayment payment) throws SQLException {
        String sql = "UPDATE FeePayments SET StudentID = ?, StudentName = ?, PaymentDate = ?, Amount = ?, Status = ? WHERE PaymentID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getStudentID());
            stmt.setString(2, payment.getStudentName());
            stmt.setDate(3, payment.getPaymentDate());
            stmt.setDouble(4, payment.getAmount());
            stmt.setString(5, payment.getStatus());
            stmt.setInt(6, payment.getPaymentID());
            stmt.executeUpdate();
        }
    }

    public void deleteFeePayment(int paymentID) throws SQLException {
        String sql = "DELETE FROM FeePayments WHERE PaymentID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentID);
            stmt.executeUpdate();
        }
    }

    public List<FeePayment> getAllPayments() throws SQLException {
        List<FeePayment> payments = new ArrayList<>();
        String sql = "SELECT * FROM FeePayments";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FeePayment payment = new FeePayment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setStudentID(rs.getInt("StudentID"));
                payment.setStudentName(rs.getString("StudentName"));
                payment.setPaymentDate(rs.getDate("PaymentDate"));
                payment.setAmount(rs.getDouble("Amount"));
                payment.setStatus(rs.getString("Status"));
                payments.add(payment);
            }
        }
        return payments;
    }

    public List<FeePayment> getOverduePayments() throws SQLException {
        List<FeePayment> payments = new ArrayList<>();
        String sql = "SELECT * FROM FeePayments WHERE Status = 'Overdue'";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FeePayment payment = new FeePayment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setStudentID(rs.getInt("StudentID"));
                payment.setStudentName(rs.getString("StudentName"));
                payment.setPaymentDate(rs.getDate("PaymentDate"));
                payment.setAmount(rs.getDouble("Amount"));
                payment.setStatus(rs.getString("Status"));
                payments.add(payment);
            }
        }
        return payments;
    }

    // New method: Get Paid Payments
    public List<FeePayment> getPaidPayments() throws SQLException {
        List<FeePayment> payments = new ArrayList<>();
        String sql = "SELECT * FROM FeePayments WHERE Status = 'Paid'";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FeePayment payment = new FeePayment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setStudentID(rs.getInt("StudentID"));
                payment.setStudentName(rs.getString("StudentName"));
                payment.setPaymentDate(rs.getDate("PaymentDate"));
                payment.setAmount(rs.getDouble("Amount"));
                payment.setStatus(rs.getString("Status"));
                payments.add(payment);
            }
        }
        return payments;
    }

    public List<FeePayment> getNonPaidStudents(String startDate, String endDate) throws SQLException {
        List<FeePayment> payments = new ArrayList<>();
        String sql = "SELECT * FROM FeePayments WHERE PaymentDate NOT BETWEEN ? AND ? AND Status != 'Paid'";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FeePayment payment = new FeePayment();
                    payment.setPaymentID(rs.getInt("PaymentID"));
                    payment.setStudentID(rs.getInt("StudentID"));
                    payment.setStudentName(rs.getString("StudentName"));
                    payment.setPaymentDate(rs.getDate("PaymentDate"));
                    payment.setAmount(rs.getDouble("Amount"));
                    payment.setStatus(rs.getString("Status"));
                    payments.add(payment);
                }
            }
        }
        return payments;
    }

    public double getTotalCollection(String startDate, String endDate) throws SQLException {
        String sql = "SELECT SUM(Amount) AS Total FROM FeePayments WHERE PaymentDate BETWEEN ? AND ? AND Status = 'Paid'";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("Total");
                }
            }
        }
        return 0.0;
    }
}