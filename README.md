# 📘 COLLEGE FEE MANAGEMENT SYSTEM
# 🔍 Overview:
This project manages student fee payments and provides comprehensive tools for tracking and reporting.

# 💼 Core Features:

➕ Add Payment Record

✏️ Update Payment Record

🗑️ Delete Payment Record

📄 View Payment History

# 📊 Reporting Capabilities:

📌 Unpaid Fees List

⏰ Overdue Payments Report

📆 Total Fee Collection by Period

# 🧾 Benefits:

✔️ Efficient fee tracking

✔️ Improved administrative control

✔️ Easy access to student payment history

✔️ Periodic financial reporting

# 📋 Prerequisites
Before running this application, ensure the following are installed:

☕ Java Development Kit (JDK) – Version 8 or higher

🌐 Apache Tomcat – Version 9.0 or higher

🐬 MySQL Server 5.7 or 🧰 XAMPP Server

🔌 MySQL JDBC Driver – mysql-connector-java

💻 IDE Options:🧠 Eclipse (J2EE), 🚀 IntelliJ IDEA, or 🛠️ Any Java-compatible IDE
  
🌎 Web Browser:🌐 Chrome, 🦊 Firefox, or 🧭 Edge

## 🛠️ Project Structure

![Project Structure](https://raw.githubusercontent.com/Pavankumarhr1207/College_Fee_Management/main/Screenshot/Screenshot-Project%20structure.png)

## ⚙️ Installation & Setup

### Step 1: Clone/Download the Project
Download all the project files and organize them according to the project structure above.

### Step 2: Database Configuration
1. Start your MySQL server
2. Run the database setup scripts provided above
3. Update database credentials in `FeePaymentDAO.java`:
   ```java
   connection = DriverManager.getConnection(
       "jdbc:mysql://localhost:3306/your_databaseaname", 
       "your_username", 
       "your_password");
   ```

### Step 3: Add MySQL JDBC Driver
1. Download MySQL Connector/J from the official MySQL website
2. Add the JAR file to your project's `WEB-INF/lib` directory
3. If using an IDE, add it to your build path

### Step 4: Deploy to Tomcat
1. Create a new Dynamic Web Project in your IDE
2. Copy all source files to the appropriate folders
3. Deploy the project to Tomcat server
4. Start the Tomcat server

### Step 5: Access the Application
Open your web browser and navigate to:
```
http://localhost:8080/CollegeFeeWebApp/index.jsp
```

## 🖼️ Screenshots

### 🏠 Home Page
<img src="https://github.com/Pavankumarhr1207/College_Fee_Management/blob/main/Screenshot/Screenshot-homepage.png" alt="Home Page" width="700"/>

### ➕ Add Fee Payment Page
<img src="https://github.com/Pavankumarhr1207/College_Fee_Management/blob/main/Screenshot/Screenshot-addpage.png" alt="Add Marks Page" width="700"/>

### ❌ Delete Fee Payment Page
<img src="https://github.com/Pavankumarhr1207/College_Fee_Management/blob/main/Screenshot/Screenshot-deletepage.png" alt="Search Student" width="700"/>

### 🆕 Update Fee Payment Page
<img src="https://github.com/Pavankumarhr1207/College_Fee_Management/blob/main/Screenshot/Screenshot-upadatepage.png" alt="Search Student" width="700"/>

### 📋 Display Fee Payments
<img src="https://github.com/Pavankumarhr1207/College_Fee_Management/blob/main/Screenshot/Screenshot-displaypage.png" alt="Display Marks" width="700"/>

### 🚀 Generate Page
<img src="https://github.com/Pavankumarhr1207/College_Fee_Management/blob/main/Screenshot/Screenshot-generatepage.png" alt="Search Student" width="700"/>

### 📊 Report Page
<img src="https://github.com/Pavankumarhr1207/College_Fee_Management/blob/main/Screenshot/Screenshot-reportpage.png" alt="Search Student" width="700"/>




