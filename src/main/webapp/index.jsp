<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>College Fee Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body { 
            font-family: 'Arial', sans-serif; 
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        }
        .card {
            transition: all 0.3s ease;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
        }
        .btn {
            background: linear-gradient(135deg, #4f46e5 0%, #7c3aed 100%);
            transition: all 0.3s ease;
        }
        .btn:hover {
            background: linear-gradient(135deg, #3730a3 0%, #5b21b6 100%);
            transform: scale(1.02);
        }
    </style>
</head>
<body class="min-h-screen flex items-center justify-center p-4">
    <div class="card bg-white/95 backdrop-blur-sm p-8 rounded-2xl shadow-2xl w-full max-w-md">
        <div class="text-center mb-8">
            <div class="w-16 h-16 bg-gradient-to-r from-blue-500 to-purple-600 rounded-full flex items-center justify-center mx-auto mb-4">
                <svg class="w-8 h-8 text-white" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M10.394 2.08a1 1 0 00-.788 0l-7 3a1 1 0 000 1.84L5.25 8.051a.999.999 0 01.356-.257l4-1.714a1 1 0 11.788 1.84L7.667 9.088l1.94.831a1 1 0 00.787 0l7-3a1 1 0 000-1.84l-7-3z"/>
                </svg>
            </div>
            <h2 class="text-3xl font-bold text-gray-800">College Fee Management</h2>
          
        </div>
        
        <nav class="space-y-4">
            <a href="feepaymentadd.jsp" class="btn block w-full text-white py-3 px-6 rounded-xl text-center font-medium shadow-lg">
                <div class="flex items-center justify-center space-x-2">
                    <span>💰</span>
                    <span>Add Fee Payment</span>
                </div>
            </a>
            
            <a href="feepaymentupdate.jsp" class="btn block w-full text-white py-3 px-6 rounded-xl text-center font-medium shadow-lg">
                <div class="flex items-center justify-center space-x-2">
                    <span>✏️</span>
                    <span>Update Fee Payment</span>
                </div>
            </a>
            
            <a href="feepaymentdelete.jsp" class="btn block w-full text-white py-3 px-6 rounded-xl text-center font-medium shadow-lg">
                <div class="flex items-center justify-center space-x-2">
                    <span>🗑️</span>
                    <span>Delete Fee Payment</span>
                </div>
            </a>
            
            <a href="displayFeePayments" class="btn block w-full text-white py-3 px-6 rounded-xl text-center font-medium shadow-lg">
                <div class="flex items-center justify-center space-x-2">
                    <span>👁️</span>
                    <span>View All Payments</span>
                </div>
            </a>
            
            <a href="report_form.jsp" class="btn block w-full text-white py-3 px-6 rounded-xl text-center font-medium shadow-lg">
                <div class="flex items-center justify-center space-x-2">
                    <span>📊</span>
                    <span>Generate Report</span>
                </div>
            </a>
        </nav>
        
        <div class="mt-6 text-center">
            <p class="text-sm text-gray-500">© 2025 College Management System</p>
        </div>
    </div>

    <script>
        // Simple hover effects and click feedback
        document.querySelectorAll('.btn').forEach(btn => {
            btn.addEventListener('click', function(e) {
                // Create ripple effect
                const ripple = document.createElement('div');
                ripple.style.position = 'absolute';
                ripple.style.borderRadius = '50%';
                ripple.style.background = 'rgba(255,255,255,0.3)';
                ripple.style.transform = 'scale(0)';
                ripple.style.animation = 'ripple 0.6s linear';
                ripple.style.left = (e.clientX - this.offsetLeft - 25) + 'px';
                ripple.style.top = (e.clientY - this.offsetTop - 25) + 'px';
                ripple.style.width = '50px';
                ripple.style.height = '50px';
                
                this.style.position = 'relative';
                this.appendChild(ripple);
                
                setTimeout(() => {
                    ripple.remove();
                }, 600);
            });
        });
        
        // Add ripple animation
        const style = document.createElement('style');
        style.textContent = `
            @keyframes ripple {
                to {
                    transform: scale(4);
                    opacity: 0;
                }
            }
        `;
        document.head.appendChild(style);
    </script>
</body>
</html>