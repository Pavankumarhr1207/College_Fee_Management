<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Fee Payment</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body { font-family: 'Arial', sans-serif; }
        .error { color: red; }
        .success { color: green; } /* Add style for success message */
    </style>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Delete Fee Payment</h2>

        <%-- Display success message if available --%>
        <%
            String message = request.getParameter("message");
            if (message != null && !message.trim().isEmpty()) {
        %>
                <p class="success text-center mb-4"><%= message %></p>
        <%
            }
        %>

        <%-- Display error message if available (from previous forwards) --%>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error mb-4"><%= request.getAttribute("error") %></p>
        <% } %>

        <form action="deleteFeePayment" method="post"  class="space-y-4">
            <div>
                <label for="paymentID" class="block text-sm font-medium text-gray-700">Payment ID</label>
                <input type="number" id="paymentID" name="paymentID" required
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            <button type="submit"
                    class="w-full bg-red-600 text-white py-2 px-4 rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500">
                Delete Payment
            </button>
        </form>
        <a href="index.jsp" class="block text-center mt-4 text-indigo-600 hover:text-indigo-800">Back to Home</a>
    </div>
</body>
</html>