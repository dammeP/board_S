<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
</script>

<ul class="nav nav-sidebar">
	<li class="active"><a href="${pageContext.request.contextPath }/CBoardInsert">게시판생성</a></li>
	<c:forEach items="${cBoardList }" var="cBoardList">
		<c:if test="${cBoardList.cboardUseCheck == '0' }">
			<li class="active" id="category"><a href="${pageContext.request.contextPath }/boardList?cboardNo=${cBoardList.cboardNo}">${cBoardList.cboardCategory }</a></li>
		</c:if> 
	</c:forEach>
</ul>
