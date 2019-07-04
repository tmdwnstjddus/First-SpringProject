
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<script type="text/javascript">
	window.addEventListener("load", function(event) { //js의 main 함수
		var btnCancel = document.querySelector("#cancel_button");
		btnCancel.addEventListener("click", function(event){ 
			location.href = "/springlol/upload/list?keyField=all&keyWord="; //주소창에 list.action을 입력하고 엔터
			//history.back(); //브라우저의 이전 버튼을 클릭
           });
    	});
</script>

	<meta charset="utf-8" />
	<title>대출 도서 정보</title>
	<link rel="Stylesheet" href="/springlol/resources/styles/Default.css" />
	<link rel="Stylesheet" href="/springlol/resources/styles/Input2.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">대출 도서 정보</div>
		        <form action="/springlol/upload/update"
		        	  method="post"
		        	  enctype="multipart/form-data">
		        <input type="hidden" name="uploadNo" value="${ upload.uploadNo }">
		        <table>
		            <tr>
                      <th>도서 코드</th>
                      <td>
                          <input type="text" name="bookCode" style="width:580px" value="${ upload.bookCode }" />
                      </td>
                  </tr>
                  <tr>
                      <th>도서 이름</th>
                      <td>
                          <input type="text" name="title" style="width:580px" value="${ upload.title }" />
                      </td>
                  </tr>
                   <tr>
                      <th>회원 코드</th>
                      <td>
                          <input type="text" name="memberCode" style="width:580px" value="${ upload.memberCode }" />
                      </td>
                  </tr>
                   <tr>
                      <th>회원 이름</th>
                      <td>
                          <input type="text" name="memberName" style="width:580px" value="${ upload.memberName }" />
                      </td>
                  </tr>
                  <tr>
                      <th>작성자</th>
                      <td>
                         
                         <input type="hidden" name="uploader" value="${ loginuser.memberId }>">
                         ${ loginuser.memberId }
                      </td>
                  </tr>
		            <tr>
		                <th>첨부자료</th>
		                <td>
		                	<c:forEach var="file" items="${ upload.files }">
			                ${ file.userFileName } 
			                [<a href="/springlol/upload/delete-file/${ upload.uploadNo }/${ file.uploadFileNo }">삭제</a>]<br>
			                </c:forEach>
			                <br>
		                    <input type="file" name="attach" style="width:580px;height:20px" />
		                </td>
		            </tr>
		            <tr>
                      <th>기타사항</th>
                      <td>
                         <textarea name="content" style="width:580px" rows="15">${ upload.content }</textarea>
                      </td>
                  </tr>
                  <tr>
			          <th>반납 예정일</th>
			          <td>            
	                   	 <input type="date" name="returnDate" style="width:580px" value="${ upload.returnDate }" />
				      </td>
				  </tr>  
                  <tr>
                      <th>반납확인</th>
                      <td>                      
                        <select style="height: 25px" name="returnCheck" size=1 >
                        	<option value="미반납">미반납</option>
                        	<option value="반납">반납</option>
                        </select>
                      </td>
                  </tr>
              </table>
		        <div class="buttons">
		        	<input type="submit" value="자료등록" style="height:25px" />
		        	<input id="cancel_button" type="button" value="취소" style="height:25px"  />
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>