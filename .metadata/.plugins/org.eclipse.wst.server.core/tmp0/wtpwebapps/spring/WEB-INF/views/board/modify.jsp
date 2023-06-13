<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/modify" method="post">
	<input type="hidden" name="bno" value="${board.bno}">
	writer : ${board.writer}<br><br>
	title : <input type="text" name=title placeholder="${board.title}"><br><br>
	content : <input type="text" name=content placeholder="${board.content}"><br><br>
	reg_date : ${board.reg_date}<br><br>
	<button>수정완료</button>
	</form>
</body>
</html>