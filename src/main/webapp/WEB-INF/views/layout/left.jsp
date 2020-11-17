<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
</script>

<ul class="nav nav-sidebar">
	<li class="active"><a href="${pageContext.request.contextPath }/cboard/insert">게시판생성</a></li>
	<c:forEach items="${S_cboardList }" var="S_cboardList">
		<c:if test="${S_cboardList.cboardUseCheck == '0' }">
			<li class="active" id="category"><a href="${pageContext.request.contextPath }/board/category?cboardNo=${S_cboardList.cboardNo}">${S_cboardList.cboardCategory }</a></li>
		</c:if> 
	</c:forEach>
</ul>
