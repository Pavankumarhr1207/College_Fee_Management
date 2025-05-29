<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Report Results</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body { font-family: 'Arial', sans-serif; }
        .error { color: red; }
    </style>
</head>
<body class="bg-gray-100 min-h-screen p-6">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-4xl mx-auto">
        <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">${reportTitle}</h2>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error mb-4 text-center"><%= request.getAttribute("error") %></p>
        <% } %>
        <c:choose>
            <c:when test="${not empty reportData}">
                <table class="w-full border-collapse">
                    <thead>
                        <tr class="bg-indigo-600 text-white">
                            <th class="py-2 px-4 border">Payment ID</th>
                            <th class="py-2 px-4 border">Student ID</th>
                            <th class="py-2 px-4 border">Student Name</th>
                            <th class="py-2 px-4 border">Payment Date</th>
                            <th class="py-2 px-4 border">Amount</th>
                            <th class="py-2 px-4 border">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="payment" items="${reportData}">
                            <tr class="hover:bg-gray-100">
                                <td class="py-2 px-4 border">${payment.paymentID}</td>
                                <td class="py-2 px-4 border">${payment.studentID}</td>
                                <td class="py-2 px-4 border">${payment.studentName}</td>
                                <td class="py-2 px-4 border">${payment.paymentDate}</td>
                                <td class="py-2 px-4 border">${payment.amount}</td>
                                <td class="py-2 px-4 border">${payment.status}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${not empty totalCollection}">
                <p class="text-center text-lg">Total Collection: ${totalCollection}</p>
            </c:when>
            <c:otherwise>
                <p class="text-center">No data available for this report.</p>
            </c:otherwise>
        </c:choose>
        <a href="report_form.jsp" class="block text-center mt-4 text-indigo-600 hover:text-indigo-800">Generate Another Report</a>
        <a href="index.jsp" class="block text-center mt-2 text-indigo-600 hover:text-indigo-800">Back to Home</a>
    </div>
</body>
</html>