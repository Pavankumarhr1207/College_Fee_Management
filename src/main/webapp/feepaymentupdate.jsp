<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Fee Payment</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body { font-family: 'Arial', sans-serif; }
        .error { color: red; }
        .success { color: green; } /* Added style for success message */
    </style>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Update Fee Payment</h2>

        <%-- Display success message if available --%>
        <%
            String message = request.getParameter("message");
            if (message != null && !message.trim().isEmpty()) {
        %>
                <p class="success text-center mb-4"><%= message %></p>
        <%
            }
        %>

        <%-- Display error message if available --%>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error mb-4"><%= request.getAttribute("error") %></p>
        <% } %>
        <form action="updateFeePayment" method="post" class="space-y-4">
            <div>
                <label for="paymentID" class="block text-sm font-medium text-gray-700">Payment ID</label>
                <input type="number" id="paymentID" name="paymentID" required
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            <div>
                <label for="studentID" class="block text-sm font-medium text-gray-700">Student ID</label>
                <input type="number" id="studentID" name="studentID" required
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            <div>
                <label for="studentName" class="block text-sm font-medium text-gray-700">Student Name</label>
                <input type="text" id="studentName" name="studentName" required
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            <div>
                <label for="paymentDate" class="block text-sm font-medium text-gray-700">Payment Date</label>
                <input type="date" id="paymentDate" name="paymentDate" required
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            <div>
                <label for="amount" class="block text-sm font-medium text-gray-700">Amount</label>
                <input type="number" id="amount" name="amount" step="0.01" required
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            <div>
                <label for="status" class="block text-sm font-medium text-gray-700">Status</label>
                <select id="status" name="status" required
                        class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                    <option value="Paid">Paid</option>
                    <option value="Overdue">Overdue</option>
                    <option value="Not Paid">Not Paid</option> <%-- Added 'Not Paid' for consistency --%>
                </select>
            </div>
            <button type="submit"
                    class="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                Update Payment
            </button>
        </form>
        <a href="index.jsp" class="block text-center mt-4 text-indigo-600 hover:text-indigo-800">Back to Home</a>
    </div>
</body>
</html>