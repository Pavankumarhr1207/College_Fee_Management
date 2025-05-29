package com.servlet;

import com.dao.FeePaymentDAO;
import com.model.FeePayment;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/addFeePayment")
public class AddFeePaymentServlet extends HttpServlet {
    private FeePaymentDAO feePaymentDAO;

    @Override
    public void init() throws ServletException {
        feePaymentDAO = new FeePaymentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String studentIDStr = request.getParameter("studentID");
            String studentName = request.getParameter("studentName");
            String paymentDateStr = request.getParameter("paymentDate");
            String amountStr = request.getParameter("amount");
            String status = request.getParameter("status");

            if (studentIDStr == null || studentName == null || paymentDateStr == null || amountStr == null || status == null ||
                studentIDStr.trim().isEmpty() || studentName.trim().isEmpty() || paymentDateStr.trim().isEmpty() || amountStr.trim().isEmpty() || status.trim().isEmpty()) {
                request.setAttribute("error", "All fields are required.");
                request.getRequestDispatcher("feepaymentadd.jsp").forward(request, response);
                return;
            }

            int studentID;
            double amount;
            try {
                studentID = Integer.parseInt(studentIDStr);
                amount = Double.parseDouble(amountStr);
                if (studentID <= 0 || amount <= 0) {
                    request.setAttribute("error", "Student ID and Amount must be positive.");
                    request.getRequestDispatcher("feepaymentadd.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Student ID or Amount format.");
                request.getRequestDispatcher("feepaymentadd.jsp").forward(request, response);
                return;
            }

            try {
                Date.valueOf(paymentDateStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", "Invalid date format. Use YYYY-MM-DD.");
                request.getRequestDispatcher("feepaymentadd.jsp").forward(request, response);
                return;
            }

            // Updated: Allow "Not Paid" as a valid status
            if (!status.equals("Paid") && !status.equals("Overdue") && !status.equals("Not Paid")) {
                request.setAttribute("error", "Status must be 'Paid', 'Overdue', or 'Not Paid'.");
                request.getRequestDispatcher("feepaymentadd.jsp").forward(request, response);
                return;
            }

            FeePayment payment = new FeePayment();
            payment.setStudentID(studentID);
            payment.setStudentName(studentName);
            payment.setPaymentDate(Date.valueOf(paymentDateStr));
            payment.setAmount(amount);
            payment.setStatus(status);

            feePaymentDAO.addFeePayment(payment);

            // Add success message as a query parameter for redirection
            String successMessage = URLEncoder.encode("Fee payment added successfully!", StandardCharsets.UTF_8.toString());
            response.sendRedirect("displayFeePayments?message=" + successMessage);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("feepaymentadd.jsp").forward(request, response);
        }
    }
}