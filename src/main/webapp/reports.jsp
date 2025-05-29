<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reports</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body { font-family: 'Arial', sans-serif; }
    </style>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md text-center">
        <h2 class="text-2xl font-bold mb-6 text-gray-800">Reports</h2>
        <div class="space-y-4">
            <a href="report_form.jsp?reportType=overdue"
               class="block bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700">Overdue Payments</a>
            <a href="report_form.jsp?reportType=nonPaid"
               class="block bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700">Non-Paid Students</a>
            <a href="report_form.jsp?reportType=totalCollection"
               class="block bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700">Total Collection</a>
        </div>
        <a href="index.jsp" class="block text-center mt-4 text-indigo-600 hover:text-indigo-800">Back to Home</a>
    </div>
</body>
</html>