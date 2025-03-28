<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${appName} - Book Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom Styles -->
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7f6;
        }
        .container {
            max-width: 900px;
            margin-top: 50px;
        }
        .book-card {
            background-color: #ffffff;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }
        .book-card h5 {
            color: #333;
            font-weight: bold;
        }
        .book-card p {
            font-size: 1.1rem;
            color: #666;
        }
        .book-card .label {
            font-weight: bold;
            color: #333;
        }
        .header {
            text-align: center;
            margin-bottom: 40px;
        }
        hr {
            border-top: 2px solid #ccc;
            width: 50%;
            margin: 20px auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Page Header -->
        <div class="header">
            <h1 class="display-4">${appName}</h1>
            <hr>
            <a href="/">Home</a>
        </div>

        <!-- Books Section -->
        <div>
            <h2>Books</h2>
            <div class="row m-2">
            	<c:forEach items="${books}" var="b">
            	<div class="card m-3 " style="width:max-content;">
			    	<img alt="book image" src="/getBookImage?name=${b.name}" height="100px" width="50%">
			    <p>Name: <b>${b.name}</b> </p>
			    	<p>Price: <b>${b.price}</b> </p>
			    	<p>Author Name: <b>${b.aname}</b> </p>
			    	<p>Publisher Name: <b>${b.pname}</b> </p>
			<div class="col">
	    		<form action="viewBook" method="post" target="_blank">
	    			<input type="hidden" name="name" value="${b.name}">
	    			<button class="btn btn-success" type="submit">View Book</button>
	    		</form>
	    	</div>
	    	<div class="col">
	    		<form action="downloadBook" method="post">
	    			<input type="hidden" name="name" value="${b.name}">
	    			<button class="btn btn-primary" type="submit">Download Book</button>
	    		</form>
	    	</div>
		    </div>
            </c:forEach>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
