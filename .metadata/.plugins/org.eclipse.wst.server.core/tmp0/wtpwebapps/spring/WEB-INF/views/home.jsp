<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<jsp:include page="./layout/header.jsp"></jsp:include>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  My Spring Project </P>

<c:if test="${ses.id != null}" >
	<a href="/member/logout"><button type="button" >로그아웃</button></a>
	<a href="/board/register"><button type="button" >글쓰기</button></a>
	<a href="/board/list"><button type="button" >글목록</button></a><br><br>
</c:if>
<c:if test="${ses.id == null }">
	<a href="/member/signup"><button type="button" >회원가입</button></a>
	<a href="/member/login"><button type="button" >로그인</button></a><br><br>
</c:if>
<script type="text/javascript">
	const msg_signup = '<c:out value="${msg_signup}"/>';
	const msg_login = '<c:out value="${msg_login}"/>';
	const msg_register='<c:out value="${msg_register}"/>'
	console.log("msg_signup >> "+msg_signup);
	console.log("msg_login >> "+msg_login);
	console.log("msg_register >> "+msg_register);
	if(msg_signup==='1'){
		alert('welcom');
	}
	
	if(msg_login==='1'){
		alert('welcom');
	}
	
	if(msg_register==='1'){
		alert('글쓰기 성공.');
	}
</script>
</body>
</html>
<jsp:include page="./layout/footer.jsp"></jsp:include>