<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #0f2027, #203a43, #2c5364);
            color: #f8f9fa;
            font-family: 'Poppins', sans-serif;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            max-width: 400px;
            width: 100%;
        }
        .btn-custom {
            background-color: #28a745;
            color: white;
            border: none;
            position: relative;
            overflow: hidden;
            z-index: 1;
            transition: background-color 0.3s ease;
        }
        .btn-custom::before {
            content: '';
            position: absolute;
            top: 50%;
            left: 50%;
            width: 0;
            height: 0;
            background: rgba(0, 0, 0, 0.1);
            border-radius: 50%;
            transform: translate(-50%, -50%);
            transition: width 0.6s ease, height 0.6s ease;
            z-index: -1;
        }
        .btn-custom:hover::before {
            width: 300%;
            height: 300%;
        }
        .btn-custom:hover {
            background-color: #218838;
        }
        .form-label {
            font-size: 1.2rem;
        }
        .back-home {
            font-size: 0.9rem;
            text-align: center;
            margin-top: 15px;
        }
        .back-home a {
            color: #f8f9fa;
            text-decoration: underline;
        }
        .back-home a:hover {
            color: #28a745;
        }
    </style>
</head>
<body>
	
	
    <div class="container text-center">
    	<c:if test="${msg!=null}">
    		<div class="alert alert-warning alert-dismissible fade show" role="alert">
			  <strong>Message: </strong> ${msg}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
    	</c:if>
	    
        <h2 class="mb-4">User Registration</h2>
        <hr class="border border-light">
		
        <form action="adminRegistration" method="post" class="mt-4">
        <div class="mb-3">
                <input type="text" id="adminname" name="name" maxlength="30" class="form-control" placeholder="Enter your name" required>
            </div>
            <div class="mb-3">
                <input type="email" id="adminUsername" name="id" maxlength="30" class="form-control" placeholder="Enter your mail" required>
            </div>
            <div class="mb-3">
               	<input type="password" id="adminPassword" name="password" maxlength="30" class="form-control" placeholder="Enter password" required>
            </div>
            <button type="submit" class="btn btn-custom w-100 py-2">Registered</button>
        </form>

        <div class="back-home mt-3">
            <p>
                <a href="/">Back to home</a><br>
                <a href="AdminLogin.jsp">Already have an account?</a>
            </p>
        </div>
    </div>

</body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</html>
