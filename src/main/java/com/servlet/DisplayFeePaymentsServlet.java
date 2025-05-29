package com.servlet;

import com.dao.FeePaymentDAO;
import com.model.FeePayment;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/displayFeePayments")
public class DisplayFeePaymentsServlet extends HttpServlet {
    private FeePaymentDAO feePaymentDAO;

    @Override
    public void init() throws ServletException {
        feePaymentDAO = new FeePaymentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<FeePayment> payments = feePaymentDAO.getAllPayments();
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("feepaymentdisplay.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("feepaymentdisplay.jsp").forward(request, response);
        }
    }
}