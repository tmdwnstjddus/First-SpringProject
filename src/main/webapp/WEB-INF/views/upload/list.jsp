<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<script type="text/javascript">
	function searchCheck(frm){
        //검색
       
        if(frm.keyWord.value ==""){
            alert("검색 단어를 입력하세요.");
            frm.keyWord.focus();
            return;
        }
        frm.submit();      
    }

	</script>
	
	
	<meta charset="utf-8" />
	<title>도서 기록 관리 자료실</title>
	<link rel="Stylesheet" href="/springlol/resources/styles/Default.css" />
</head>
<body>

	<div id="pageContainer">
	
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
			<h1>도서 기록 관리 자료실</h1>
			[ <a href="write">자료 등록</a> ]
			<br /><br />
			<table border="1" style="width:850px;margin:0 auto">
				<tr style="background-color:orange;height:30px">
					<th style="width:50px">순서</th>
					<th style="width:100px">도서번호</th>
					<th style="width:350px">도서이름</th>
					<th style="width:120px">회원번호</th>
					<th style="width:120px">회원이름</th>
					<th style="width:130px">관리자 아이디</th>
					<th style="width:130px;text-align:center">대출일(작성일)</th>
					<th style="width:135px;text-align:center">반납 예정일</th>
					<th style="width:120px;text-align:center">반납확인</th>
				</tr>
				<c:forEach var="upload" items="${ upload }">
				<tr style="height:30px">
					<td>${ upload.uploadNo }</td>
					<td>${ upload.bookCode }</td>
					<td style='text-align:left;padding-left:10px'>
			<a href="detail?uploadno=${ upload.uploadNo }" style="text-decoration: none">
							${ upload.title }
						</a>
					</td>
					<td>${ upload.memberCode }</td>
					<td>${ upload.memberName }</td>
					<td>${ upload.uploader }</td>
					<td>${ upload.regDate }</td>
					<td>${ upload.returnDate }</td> <!-- 반납일 작성해야함  -->
					<td>${ upload.returnCheck }</td>
				</tr>
				</c:forEach>
			</table>
			<br />
			<div>  
           		 <form action="list?keyField=all&keyWord=" method="get">
           			 <select style="height: 25px; margin-top: 5px" name="keyField">
		                <option value="all"> ----선택----</option>
		                <option value="bookCode">도서번호</option>
		                <option value="title">도서이름</option>
		                <option value="memberCode">회원번호</option>
		                <option value="memberName">회원이름</option> 
            		</select>
           		<input type="text" name="keyWord" />
            	<input type="submit" value="검색" onclick="searchCheck(form)" />
            	</form>    
   		 	</div>
		
		</div>
		
	</div>
		

</body>
</html>











