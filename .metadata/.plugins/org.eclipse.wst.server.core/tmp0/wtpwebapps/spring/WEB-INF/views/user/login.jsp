<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
</head>
<body>
<h1>Login Page</h1>
<form action="/member/login" method="post">
ID : <input type="text" name="id"><br>
Password : <input type="password" name="pw"><br>
<button type="submit">로그인</button>
</form>
</body>
</html>