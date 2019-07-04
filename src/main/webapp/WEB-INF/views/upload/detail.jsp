<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%-- JSTL의 함수를 제공하는 taglib --%>
    
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>대출 도서 정보</title>
	<link rel="Stylesheet" href="/springlol/resources/styles/Default.css" />
	<link rel="Stylesheet" href="/springlol/resources/styles/Input.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div style="width:810px; height:700px" id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">대출 도서 정보</div>
		        <table style="width:745px; height:400px">
	        	<tr>
	                <th>도서코드</th>
	                <td>${ upload.bookCode }</td>
	                <th>도서이름</th>
	                <td>${ upload.title }</td>
	            </tr>
	            <tr>
	                <th>회원코드</th>
	                <td>${ upload.memberCode }</td>
	                <th>회원이름</th>
	                <td>${ upload.memberName }</td>
	            </tr>
	            <tr>
	                <th colspan="1">관리자 아이디</th>
	                <td colspan="3">${ upload.uploader }</td>
	            </tr>
	            <tr>
	            	<th colspan="1">대출일(작성일)</th>
	            	<td colspan="3">${ upload.regDate }</td>
	            </tr>
	            <tr>
	            	<th colsapn="1">반납 예정일</th>
	            	<td colspan="3">${ upload.returnDate }</td>
	            </tr>
	            <tr>
	                <th colspan="1">사진자료</th>
                    <td colspan="3">
                    <div id="imagePreview"></div><br>
                   	  	<c:forEach var="file" items="${upload.files}">
                   	  	<a href="download/${ file.uploadFileNo }"> ${ file.userFileName } </a>
                   	  	<br>    
                     </c:forEach>           
                     </td>

	            </tr>
		            <tr>
		                <th colspan="1">기타사항</th>
<%-- 줄바꿈 문자열을 저장하고 있는 변수 만들기 --%>	
<c:set var="enter" value="
" />
		                <%-- upload.content 문자열에서 \r\n을 <br>로 변경 --%>
		                <td colspan="3">${ fn:replace(upload.content, enter, '<br>') }</td>
		            </tr>
		        <tr>
	            	<th colspan="1">반납확인</th>
	            	<td colspan="3">${ upload.returnCheck }</td>
	            </tr>
        </table>
        
        <div style="text-align: center; margin-left: 130px" class="buttons">
        	<!-- 로그인한 사용자와 글의 작성자가 같으면 삭제, 수정 버튼 활성화  -->
       		<c:if test="${loginuser.memberId eq upload.uploader }">
        	<input type="button" id="update_button" value="편집" style="height:35px" />
        	<input type="button" id="delete_button" value="삭제" style="height:35px"/>
        	</c:if>
        	
        	<input type="button" id="cancel_button" value="목록보기" style="height:35px"/>
        	<script type="text/javascript">
        	//브라우저가 html을 모두 읽고 처리할 준비가 되었을 때
        	window.addEventListener("load", function(event) { //js의 main 함수
        		var btnCancel = document.querySelector("#cancel_button");
        		btnCancel.addEventListener("click", function(event){ 
        			location.href = "list?keyField=all&keyWord="; //주소창에 list.action을 입력하고 엔터
        			//history.back(); //브라우저의 이전 버튼을 클릭
                   });
        		
        		var btnDelete = document.querySelector('#delete_button');
        		btnDelete.addEventListener('click', function(event) {
        			var ok = confirm("${upload.uploadNo}번 자료를 삭제할까요?");
        			if(ok){
        				//<a 를 통한 요청이므로 주소 뒤에 ?key=value 형식을 써서 데이터 전송
        				location.href = "delete/${ upload.uploadNo }";
        			}
        		});
        		
        		var btnUpdate = document.querySelector('#update_button');
        		btnUpdate.addEventListener('click', function(event) {
        			//<a 를 통한 요청이므로 주소 뒤에 ?key=value 형식을 써서 데이터 전송
        			location.href = "update/${ upload.uploadNo }";
        		});
        	});
        	
        	var InputImage = 
        		(function loadImageFile() {
        	    if (window.FileReader) {
        	        var ImagePre; 
        	        var ImgReader = new window.FileReader();
        	        var fileType = /^(?:image\/bmp|image\/gif|image\/jpeg|image\/png|image\/x\-xwindowdump|image\/x\-portable\-bitmap)$/i; 
        	
        	        ImgReader.onload = function (Event) {
        	            if (!ImagePre) {
        	                var newPreview = document.getElementById("imagePreview");
        	                ImagePre = new Image();
        	                ImagePre.style.width = "200px";
        	                ImagePre.style.height = "140px";
        	                newPreview.appendChild(ImagePre);
        	            }
        	            ImagePre.src = Event.target.result;
        	            
        	        };
        	
        	        return function () {
        	        	
        	            var img = document.getElementById("attach").files;
        	           
        	            if (!fileType.test(img[0].type)) { 
        	            	alert("이미지 파일을 업로드 하세요"); 
        	            	return; 
        	            }
        	            
        	            ImgReader.readAsDataURL(img[0]);
        	        }
        	
        	    }
        	   			
        	            document.getElementById("imagePreview").src = document.getElementById("attach").value;
        	
        	      
        	})();
        	</script>
		        </div>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>