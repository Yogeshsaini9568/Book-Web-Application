<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${appName} - Find Your Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #141e30, #243b55);
            color: #f8f9fa;
            font-family: 'Poppins', sans-serif;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            text-align: center;
        }
        .container {
            width: 90%;
            max-width: 800px;
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            padding: 40px;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .form-label {
            font-size: 1.2rem;
        }
        .admin-login {
            font-size: 0.9rem;
        }
        .admin-login a {
            color: #f8f9fa;
            text-decoration: underline;
        }
        .admin-login a:hover {
            color: #007bff;
        }
        .tagline {
            font-size: 1.1rem;
            font-style: italic;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mb-3">${appName}</h1>
        <p class="tagline">Find your favorite books instantly!</p>
        <form action="getBooks" method="get" class="mb-4">
            <input type="text" id="bookName" name="name" maxlength="30" class="form-control text-center mb-2" placeholder="E.g., Harry Potter" required>
            <button type="submit" class="btn btn-custom">Search Book</button>
        </form>
        <p>
            Books transport us to new worlds, broaden our perspectives, and inspire creativity. Whether you're searching for classic literature, modern bestsellers, or academic resources, our library has it all. 
            Discover insightful books that spark curiosity and expand knowledge.
        </p>
        <p>
            Reading enhances our understanding of different cultures, ideas, and experiences. Whether you prefer fiction, history, or self-help books, the right book is waiting for you. Start your journey today!
        </p>
        <div class="admin-login mt-3">
            <p>Are you an admin? <a href="login">Login here</a></p>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
