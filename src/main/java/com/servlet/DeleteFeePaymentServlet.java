package com.servlet;

import com.dao.FeePaymentDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder; // Import this
import java.nio.charset.StandardCharsets; // Import this
import java.sql.SQLException;

@WebServlet("/deleteFeePayment")
public class DeleteFeePaymentServlet extends HttpServlet {
    private FeePaymentDAO feePaymentDAO;

    @Override
    public void init() throws ServletException {
        feePaymentDAO = new FeePaymentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String paymentIDStr = request.getParameter("paymentID");
            if (paymentIDStr == null || paymentIDStr.trim().isEmpty()) {
                request.setAttribute("error", "Payment ID is required.");
                request.getRequestDispatcher("feepaymentdelete.jsp").forward(request, response);
                return;
            }

            int paymentID;
            try {
                paymentID = Integer.parseInt(paymentIDStr);
                if (paymentID <= 0) {
                    request.setAttribute("error", "Payment ID must be positive.");
                    request.getRequestDispatcher("feepaymentdelete.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Payment ID format.");
                request.getRequestDispatcher("feepaymentdelete.jsp").forward(request, response);
                return;
            }

            feePaymentDAO.deleteFeePayment(paymentID);

            // Add success message as a query parameter for redirection
            String successMessage = URLEncoder.encode("Fee payment deleted successfully!", StandardCharsets.UTF_8.toString());
            response.sendRedirect("feepaymentdelete.jsp?message=" + successMessage); // Redirect back to the delete page with message

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("feepaymentdelete.jsp").forward(request, response);
        }
    }
}