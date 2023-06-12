<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Falha no login</title>
    <link rel="stylesheet" href="Estilo.Css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <script>
        $(document).ready(function() {
            function redirectToLogin() {
                window.location.href = "Login.jsp";
            }
            
            // Aguarda 3 segundos e redireciona à página de login
            setTimeout(redirectToLogin, 3000);
        });
    </script>
</head>
<body>
    <h1>Falha no login</h1>
    <p>Login ou senha incorreta</p>
</body>
</html>
