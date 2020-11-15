<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
td {
	width: 100px;
}
</style>

				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시판 등록</h2>
						<div class="table-responsive">
							<form id="frm" action="${cp }/cboard/insert" method="POST">
								<table class="table table-striped">
									<tr>
										<input type="hidden" id="userId" name="userId"value=${S_USERS.userId }>
										<td>게시판이름</td>
										<td><input type="text" id="cboardCategory"
											name="cboardCategory"></td>
										<td><select name="cboardUseCheck">
												<option value="0">사용</option>
												<option value="1">미사용</option>
										</select></td>
										<td><input name="Btn" type="submit" value="생성"></td>
									</tr>
									<br>
								</table>
							</form>
						</div>

						<h2 class="sub-header">게시판 목록 조회</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<c:forEach items="${cboardList }" var="cboardList">
									<form id="frm" action="${cp }/CBoardUpdate" method="POST">
									<tr>
										<input type="hidden" id="userId" name="userId" value=${S_USERS.userId }/>
										<td>게시판이름</td>
										<td><input type="text" id="cboardCategory"
											name="cboardCategory" value="${cboardList.cboardCategory }"></td>
										<td><select name="cboardUseCheck">
												<c:if test="${cboardList.cboardUseCheck == '0' }">
													<option value="0">사용</option>
													<option value="1">미사용</option>
												</c:if>
												<c:if test="${cBoardList.cboardUseCheck == '1' }">
													<option value="1">미사용</option>
													<option value="0">사용</option>
												</c:if>
										</select></td>
										<td><input name="Btn" type="submit" value="수정"></td>
									</tr>
									</form>
								</c:forEach>
							</table>
						</div>


					</div>
				</div>
