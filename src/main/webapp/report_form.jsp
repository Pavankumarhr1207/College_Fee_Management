<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generate Report</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body { font-family: 'Arial', sans-serif; }
        .error { color: red; }
    </style>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Generate Report</h2>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error mb-4"><%= request.getAttribute("error") %></p>
        <% } %>
        <form action="reportCriteria" method="post" class="space-y-4">
            <div>
                <label for="reportType" class="block text-sm font-medium text-gray-700">Report Type</label>
                <select id="reportType" name="reportType" required
                        class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
                    <option value="">Select Report</option>
                    <option value="overdue">Overdue Payments</option>
                    <option value="nonPaid">Non-Paid Students</option>
                    <option value="paid">Paid Payments</option> <%-- NEW: Added Paid Payments option --%>
                    <option value="totalCollection">Total Collection</option>
                </select>
            </div>
            <div>
                <label for="startDate" class="block text-sm font-medium text-gray-700">Start Date (YYYY-MM-DD)</label>
                <input type="date" id="startDate" name="startDate"
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            <div>
                <label for="endDate" class="block text-sm font-medium text-gray-700">End Date (YYYY-MM-DD)</label>
                <input type="date" id="endDate" name="endDate"
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            <button type="submit"
                    class="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500">
                Generate Report
            </button>
        </form>
        <a href="index.jsp" class="block text-center mt-4 text-indigo-600 hover:text-indigo-800">Back to Home</a>
    </div>
</body>
</html>