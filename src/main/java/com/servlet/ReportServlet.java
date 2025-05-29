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

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    private FeePaymentDAO feePaymentDAO;

    @Override
    public void init() throws ServletException {
        feePaymentDAO = new FeePaymentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters using request.getParameter() as they are now from a redirect
        String reportType = request.getParameter("reportType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        try {
            if ("overdue".equals(reportType)) {
                List<FeePayment> overduePayments = feePaymentDAO.getOverduePayments();
                request.setAttribute("reportData", overduePayments);
                request.setAttribute("reportTitle", "Overdue Payments");
            } else if ("nonPaid".equals(reportType)) {
                if (startDate == null || endDate == null || startDate.trim().isEmpty() || endDate.trim().isEmpty()) {
                    request.setAttribute("error", "Start and end dates are required for non-paid students report.");
                    request.getRequestDispatcher("report_form.jsp").forward(request, response);
                    return;
                }
                List<FeePayment> nonPaidPayments = feePaymentDAO.getNonPaidStudents(startDate, endDate);
                request.setAttribute("reportData", nonPaidPayments);
                request.setAttribute("reportTitle", "Non-Paid Students from " + startDate + " to " + endDate);
            } else if ("paid".equals(reportType)) { // New: Handle Paid Payments report
                List<FeePayment> paidPayments = feePaymentDAO.getPaidPayments();
                request.setAttribute("reportData", paidPayments);
                request.setAttribute("reportTitle", "Paid Payments");
            } else if ("totalCollection".equals(reportType)) {
                if (startDate == null || endDate == null || startDate.trim().isEmpty() || endDate.trim().isEmpty()) {
                    request.setAttribute("error", "Start and end dates are required for total collection report.");
                    request.getRequestDispatcher("report_form.jsp").forward(request, response);
                    return;
                }
                double total = feePaymentDAO.getTotalCollection(startDate, endDate);
                request.setAttribute("totalCollection", total);
                request.setAttribute("reportTitle", "Total Collection from " + startDate + " to " + endDate);
            } else {
                request.setAttribute("error", "Invalid report type specified.");
                request.getRequestDispatcher("report_form.jsp").forward(request, response);
                return;
            }
            request.getRequestDispatcher("report_result.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("report_form.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            // This catches date format errors if they somehow bypass ReportCriteriaServlet's validation
            request.setAttribute("error", "Invalid date format. Use YYYY-MM-DD.");
            request.getRequestDispatcher("report_form.jsp").forward(request, response);
        }
    }
}