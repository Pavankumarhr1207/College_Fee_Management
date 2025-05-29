package com.servlet;

import com.dao.FeePaymentDAO;
import com.model.FeePayment;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder; // Import this for URL encoding
import java.nio.charset.StandardCharsets; // Import this for character encoding
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/updateFeePayment")
public class UpdateFeePaymentServlet extends HttpServlet {
    private FeePaymentDAO feePaymentDAO;

    @Override
    public void init() throws ServletException {
        feePaymentDAO = new FeePaymentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String paymentIDStr = request.getParameter("paymentID");
            String studentIDStr = request.getParameter("studentID");
            String studentName = request.getParameter("studentName");
            String paymentDateStr = request.getParameter("paymentDate");
            String amountStr = request.getParameter("amount");
            String status = request.getParameter("status");

            if (paymentIDStr == null || studentIDStr == null || studentName == null || paymentDateStr == null || amountStr == null || status == null ||
                paymentIDStr.trim().isEmpty() || studentIDStr.trim().isEmpty() || studentName.trim().isEmpty() || paymentDateStr.trim().isEmpty() || amountStr.trim().isEmpty() || status.trim().isEmpty()) {
                request.setAttribute("error", "All fields are required.");
                request.getRequestDispatcher("feepaymentupdate.jsp").forward(request, response);
                return;
            }

            int paymentID, studentID;
            double amount;
            try {
                paymentID = Integer.parseInt(paymentIDStr);
                studentID = Integer.parseInt(studentIDStr);
                amount = Double.parseDouble(amountStr);
                if (paymentID <= 0 || studentID <= 0 || amount <= 0) {
                    request.setAttribute("error", "Payment ID, Student ID, and Amount must be positive.");
                    request.getRequestDispatcher("feepaymentupdate.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Payment ID, Student ID, or Amount format.");
                request.getRequestDispatcher("feepaymentupdate.jsp").forward(request, response);
                return;
            }

            try {
                Date.valueOf(paymentDateStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", "Invalid date format. Use YYYY-MM-DD.");
                request.getRequestDispatcher("feepaymentupdate.jsp").forward(request, response);
                return;
            }

            // Updated: Allow "Not Paid" as a valid status for consistency
            if (!status.equals("Paid") && !status.equals("Overdue") && !status.equals("Not Paid")) {
                request.setAttribute("error", "Status must be 'Paid', 'Overdue', or 'Not Paid'.");
                request.getRequestDispatcher("feepaymentupdate.jsp").forward(request, response);
                return;
            }

            FeePayment payment = new FeePayment();
            payment.setPaymentID(paymentID);
            payment.setStudentID(studentID);
            payment.setStudentName(studentName);
            payment.setPaymentDate(Date.valueOf(paymentDateStr));
            payment.setAmount(amount);
            payment.setStatus(status);

            feePaymentDAO.updateFeePayment(payment);

            // Add success message as a query parameter for redirection
            String successMessage = URLEncoder.encode("Fee payment updated successfully!", StandardCharsets.UTF_8.toString());
            response.sendRedirect("feepaymentupdate.jsp?message=" + successMessage); // Redirect to update page with message
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("feepaymentupdate.jsp").forward(request, response);
        }
    }
}