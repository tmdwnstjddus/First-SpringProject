<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<script type="text/javascript">
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
	
	window.addEventListener("load", function(event) { //js의 main 함수
		var btnCancel = document.querySelector("#cancel_button");
		btnCancel.addEventListener("click", function(event){ 
			location.href = "list?keyField=all&keyWord="; //주소창에 list.action을 입력하고 엔터
			//history.back(); //브라우저의 이전 버튼을 클릭
           });
    	});
</script>
	<meta charset="utf-8" />
	<title>대출서비스 기록 업로드</title>
	<link rel="Stylesheet" href="/springlol/resources/styles/Default.css" />
	<link rel="Stylesheet" href="/springlol/resources/styles/Input2.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">도서 대출 기록 등록</div>
		        <form action="write"
		        	  method="post"
		        	  enctype="multipart/form-data">
		        <table>
             	  <tr>
                      <th>도서번호</th>
                      <td>
                          <input type="text" name="bookCode" style="width:580px" />
                      </td>
                  </tr>
                  <tr>
                      <th>도서이름</th>
                      <td>
                          <input type="text" name="title" style="width:580px" />
                      </td>
                  </tr>
                  <tr>
                      <th>회원번호</th>
                      <td>
                          <input type="text" name="memberCode" style="width:580px" />
                      </td>
                  </tr>
                  <tr>
                      <th>회원이름</th>
                      <td>
                          <input type="text" name="memberName" style="width:580px" />
                      </td>
                  </tr>
		            <tr>
		                <th>관리자 아이디</th>
		                <td>
		                	<input type="hidden" name="uploader" value="${ loginuser.memberId }">
		                	${ loginuser.memberId }		                	
		                </td>
		            </tr>
		             <tr>
                      <th>도서 이미지</th>
                      <td>
                      <div id="imagePreview"></div><br>
                          <input type="file" id="attach" name="attach" onchange="InputImage();" style="width:580px;height:20px" />
                      </td>
                  </tr>
                  
                  <tr>
                      <th>기타사항</th>
                      <td>
                         <textarea name="content" style="width:580px" rows="15"></textarea>
                      </td>
                  </tr>
                  <tr>
			          <th>반납 예정일</th>
			          <td>            
	                   	 <input type="date" name="returnDate" style="width:580px" />
				      </td>
				  </tr>  
                  <tr>
                      <th>반납확인</th>
                      <td>
                        <select style="height: 25px" name="returnCheck" size=1>
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