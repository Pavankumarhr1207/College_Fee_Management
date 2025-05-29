<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>          <%-- Import List --%>
<%@ page import="com.model.FeePayment" %>    <%-- Import FeePayment model --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fee Payments</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body { font-family: 'Arial', sans-serif; }
        .error { color: red; }
        .success { color: green; } /* Added for success message styling */
    </style>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-4xl">
        <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Fee Payments</h2>

        <%-- Display success message if available --%>
        <%
            String message = request.getParameter("message");
            if (message != null && !message.trim().isEmpty()) {
        %>
                <p class="success text-center mb-4"><%= message %></p>
        <%
            }
        %>
        <%-- Display error message if available (from previous forwards or redirects) --%>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error text-center mb-4"><%= request.getAttribute("error") %></p>
        <% } %>

        <div class="overflow-x-auto">
            <table class="min-w-full divide-y divide-gray-200">
                <thead class="bg-gray-50">
                    <tr>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Payment ID</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Student ID</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Student Name</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Payment Date</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Amount</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                    </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                    <%
                        // Corrected: Added explicit type parameter to List to suppress unchecked cast warning
                        @SuppressWarnings("unchecked") // Suppress the unchecked cast warning
                        List<FeePayment> payments = (List<FeePayment>) request.getAttribute("payments");
                        if (payments != null && !payments.isEmpty()) { // Added check for empty list
                            for (FeePayment payment : payments) { // Use FeePayment directly
                    %>
                            <tr>
                                <td class="px-6 py-4 whitespace-nowrap"><%= payment.getPaymentID() %></td>
                                <td class="px-6 py-4 whitespace-nowrap"><%= payment.getStudentID() %></td>
                                <td class="px-6 py-4 whitespace-nowrap"><%= payment.getStudentName() %></td>
                                <td class="px-6 py-4 whitespace-nowrap"><%= payment.getPaymentDate() %></td>
                                <td class="px-6 py-4 whitespace-nowrap"><%= String.format("%.2f", payment.getAmount()) %></td>
                                <td class="px-6 py-4 whitespace-nowrap"><%= payment.getStatus() %></td>
                            </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="6" class="px-6 py-4 text-center text-gray-500">No fee payments found.</td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

        <a href="index.jsp" class="block text-center mt-4 text-indigo-600 hover:text-indigo-800">Back to Home</a>
    </div>
</body>
</html>