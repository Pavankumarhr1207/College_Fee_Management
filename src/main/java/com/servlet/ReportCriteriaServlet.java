package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/reportCriteria")
public class ReportCriteriaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportType = request.getParameter("reportType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Basic validation for report type
        if (reportType == null || reportType.trim().isEmpty()) {
            request.setAttribute("error", "Report type is required.");
            request.getRequestDispatcher("report_form.jsp").forward(request, response);
            return;
        }

        // Validate dates for reports that require them
        if (("nonPaid".equals(reportType) || "totalCollection".equals(reportType)) &&
            (startDate == null || endDate == null || startDate.trim().isEmpty() || endDate.trim().isEmpty())) {
            request.setAttribute("error", "Start and end dates are required for this report type.");
            request.getRequestDispatcher("report_form.jsp").forward(request, response);
            return;
        }

        // Date format validation if dates are provided
        if (startDate != null && !startDate.trim().isEmpty()) {
            try {
                java.sql.Date.valueOf(startDate);
                java.sql.Date.valueOf(endDate); // Validate endDate as well
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", "Invalid date format. Use YYYY-MM-DD.");
                request.getRequestDispatcher("report_form.jsp").forward(request, response);
                return;
            }
        }

        // Redirect to the /report servlet, passing parameters via URL
        String redirectURL = "report?reportType=" + URLEncoder.encode(reportType, StandardCharsets.UTF_8.toString());
        if (startDate != null && !startDate.trim().isEmpty()) {
            redirectURL += "&startDate=" + URLEncoder.encode(startDate, StandardCharsets.UTF_8.toString());
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            redirectURL += "&endDate=" + URLEncoder.encode(endDate, StandardCharsets.UTF_8.toString());
        }

        response.sendRedirect(redirectURL);
    }
}