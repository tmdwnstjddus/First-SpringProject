
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>소장 자료</title>
	<link rel="Stylesheet" href="/springlol/resources/styles/Default.css" />
</head>
<body>

	<div id="pageContainer">
	
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
			<h1>소장 자료</h1>
			<br />
			<table border="1" style="width:800px;margin:0 auto">
				<tr style="background-color:orange;height:30px">
					<th style="width:50px">순서</th>
					<th style="width:100px">도서번호</th>
					<th style="width:300px">도서이름</th>
					<th style="width:120px;text-align:center">반납확인</th>
				</tr>
				<c:forEach var="upload" items="${ uploads }">
				<tr style="height:30px">
					<td>${ upload.uploadNo }</td>
					<td>${ upload.bookCode }</td>
					<td>${ upload.title }</td>
					<td>${ upload.returnCheck }</td>
				</tr>
				</c:forEach>
				
				
			</table>
			<br /><br /><br /><br />
		
		</div>
		
	</div>
		

</body>
</html>











