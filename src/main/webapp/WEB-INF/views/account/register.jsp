<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>관리자등록</title>
	<link rel="Stylesheet" href="/springlol/resources/styles/Default.css" />
	<link rel="Stylesheet" href="/springlol/resources/styles/Input.css" />

</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain" style="width: 545px">
		        <div class="inputsubtitle">관리자기본정보</div>

		        <form id="registerform" action="register" method="post"><!-- 상대경로표시 -->
		        <table style="width: 550px">
		            <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <input type="text" id="memberId" name="memberId" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<input type="password" id="passwd" name="passwd" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>관리자 등록 인증코드</th>
		                <td>
		                    <input type="password" id="confirm" name="confirm" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>
		                	<input type="text" id="email" name="email" style="width:280px" />
		                </td>
		            </tr>
		                       		            
		        </table>
		        <div class="buttons">
		        	<input id="register" type="submit" value="등록" style="height:25px" />
		        	<input id="cancel" type="button" value="취소" style="height:25px"  />

		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>

</body>
</html>