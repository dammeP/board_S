<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>




				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시글 목록</h2>
						<div class="table-responsive">
								<table class="table table-striped">
									<tr>
										<th>게시글 번호</th>
										<th>제목</th>
										<th>작성자 아이디</th>
										<th>작성 일시</th>
									</tr>
									<tbody id="boardListID">
										<c:forEach items="${boardList }" var="boardList">
											<tr class="a" data-boardNo="${boardList.boardNo }">
													<td>${boardList.boardNo }</td>
												<c:choose>
													<c:when test="${boardList.boardUseCheck == '1'}">
														<td>[삭제된 게시물 입니다]</td>
													</c:when>
													<c:otherwise>
														<td><a href="${pageContext.request.contextPath }/board/detail?boardNo=${boardList.boardNo}">${boardList.boardTitle }</a></td>
													</c:otherwise>
												</c:choose>
														<td>${boardList.userId }</td>
														<td><fmt:formatDate value="${boardList.boardDate }" pattern="yyyy-MM-dd" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
						</div>
						<a href="${cp}/board/regist?cboardNo=${cboardNo}" class="btn btn-default pull-right">새글 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<c:if test="${page != 1}">
									<li><a href="${pageContext.request.contextPath }/board/category?cboardNo=${cboardNo}&page=${1}"> << </a></li>
								</c:if>
								<c:if test="${page != 1}">
									<li><a href="${pageContext.request.contextPath }/board/category?cboardNo=${cboardNo}&page=${page - 1}"> < </a></li>
								</c:if>
								<c:forEach var="i" begin="1" end="${pages }">
									<c:choose>
										<c:when test=" ${i == page}">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath }/board/category?cboardNo=${cboardNo}&page=${i}">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${page != pages}">
									<li><a href="${pageContext.request.contextPath }/board/category?cboardNo=${cboardNo}&page=${page + 1}"> > </a></li>
								</c:if>
								<c:if test="${page != pages}">
									<li><a href="${pageContext.request.contextPath }/board/category	?cboardNo=${cboardNo}&page=${pages}"> >> </a></li>
								</c:if>
							</ul>
						</div>

					</div>
				</div>
