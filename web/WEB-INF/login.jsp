<%-- 
    Document   : login
    Created on : 2-Oct-2018, 6:28:30 PM
    Author     : Mason
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remember Me</title>
    </head>
    <body>
        <h1>Remember Me Login Page</h1>
        
        <form method="post" action="login">
            UserName: <input type="text" name="username" value="${user_name}"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Login"><br>
            <input type="checkbox" name="cache"> Remember Me
        </form>
            ${feedback}
    </body>
</html>
