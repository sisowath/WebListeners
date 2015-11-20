<%-- 
    Document   : login
    Created on : 2015-11-20, 09:33:25
    Author     : usager
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Authentification</title>
    </head>
    <body>
        <h1>Authentification</h1>
        <form action="controller?" method="get">
            <table border="1px solid black">
                <tr>
                    <td>Nom d'utilisateur </td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password </td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                    <input type="hidden" name="action" value="login"/>
                    <input type="submit" value="Me Connecter" width="500"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
