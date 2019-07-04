<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div id="header">    	
            <div class="title">
                <a href="/springlol/home">BOOK MANAGEMENT WEB</a>
            </div>
            <div class="links">
            <c:choose>
            <c:when test="${ empty loginuser }">
            	<a href="/springlol/account/login">관리자 로그인</a>
                <a href="/springlol/account/register">관리자 등록</a>
            </c:when>
            <c:otherwise>
            	${ loginuser.memberId }님 환영합니다.
            	<a href="/springlol/account/logout">로그아웃</a>
            </c:otherwise>
            </c:choose>
            </div>
        </div>
                
        <div id="menu">
            <div>
                <ul>
                	<li><a href="/springlol/upload/booklist">소장 자료</a></li>
					<li><a href="/springlol/upload/list?keyField=all&keyWord=">도서 기록 관리 자료실</a></li>
                </ul>
            </div>
		</div>