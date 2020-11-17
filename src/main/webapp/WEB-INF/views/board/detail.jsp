<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>

$(function(){
	$('.delB').on('click',function(){
		var Pa = $(this).parents('.delDiv').find('.replyNo').attr('value');
		var boNo = $('.BONO').attr('value');
		
// 		alert(Pa);
		document.location="${cp}/ReplyDelete?boardNo="+boNo+"&replyNo="+Pa;
	})
	
})
</script>
<style>
#boardTitle{ font-size: 2.0em;}
.left-box{ color: #8B4513;}
#boardContent{ font-size: 1.2em;}
#hrLine{border: 3px solid #3C3C8C;}
#textS{width:700px; height:100px;  }
#reNm{width : 50px; height:50px; font-weight:bold;}
#reCo{width : 500px; height:100px; }
</style>
		<div class="row">
			<div class="col-sm-8 blog-main">
				<div class="form-group">
					<div class="col-sm-10">
						<label id="boardTitle" data-userid="${boardVO.boardTitle}"class="control=label">${boardVO.boardTitle}</label><br>
					</div>
				</div>
				<br>
				<div class="form-group">
					<div class="left-box">
						<label id="userId" class="control=label">작성자 : </label>
						<label id="userId" class="control=label">${boardVO.userId}</label>
					</div>
					<div class="left-box">
						<label id="boardDate" class="control=label">작성일자 : </label>
						<label id="boardDate" class="control=label">${boardVO.boardDate}</label>
					</div>
				</div>
				<br>
				<br>
				<div class="form-group">
					<div class="col-sm-10">
						<label id="boardContent"class="control=label">${boardVO.boardContent}</label>
					</div>
				</div>
				<br>
				<br>
				<br>
				<div class="form-group">
					<label id="userid" for="userid" >첨부파일</label>
					<div>
						<form action="${cp}/board/fileDown?boardNo=${boardVO.boardNo}" method="GET" enctype="multipart/form-data">
								<c:forEach items="${filesList}" var="file">
									<div>
									<button id="profileDownBtn"  type="submit" class="btn btn-default">다운로드 : ${file.realFileNm }</button>
									</div>
								</c:forEach>	
						</form>
					</div>
				</div>
				<div>
					<div class="col-sm-offset-6 col-sm-10">
<%-- 						<form action="${cp }/board/update?boardNo=${boardVO.boardNo}&&cboardNo=${boardVO.CBoardNo}" method="GET" enctype="multipart/form-data" > --%>
							<button name="upBtn" type="submit" class="btn btn-default"><a href="${cp }/board/update?boardNo=${boardVO.boardNo}">수정</a>
							</button>
<!-- 						</form> -->
						
						<button name="delBtn" type="button" class="btn btn-default"><a href="${cp }/board/delete?boardNo=${boardVO.boardNo}&&cboardNo=${boardVO.CBoardNo}">삭제</a>
						</button>
<%-- 						<form action="${cp }/BoardInsertPa?boardNo=${boardVO.boardNo}" method="GET" enctype="multipart/form-data" > --%>
							<button name="ureBtnpBtn" type="submit" class="btn btn-default"><a href="${cp }/board/paRegist?boardNo=${boardVO.boardNo}">답글</a>
							</button>
<!-- 						</form> -->
					</div>
				</div>

				<br><br><br>




				<div class="form-group">
					<div id="replyS" class="col-sm-10">
						<label>댓글 조회</label>
				<div>
					<c:forEach items="${replyList}" var="replyList">
						<label id="ReNm">${replyList.userId }</label>
						<label id="ReDt">${replyList.replyDate }</label>
						<c:if test="${replyList.replyDelCheck == '1'}">
							<label>삭제된 댓글 입니다.</label>
							<hr>
							<br>
						</c:if>
						<c:if test="${replyList.replyDelCheck != '1'}">
							<div class="delDiv">
								<label>${replyList.replyContent }</label>
								<hr>
								<button type="button" type="submit" class="delB">
									<a href="${cp}/reply/delete?boardNo=${boardVO.boardNo}&&replyNo=${replyList.replyNo}">삭제</a>
								</button>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div>
					<form action="${cp }/reply/insert?boardNo=${boardVO.boardNo}"
						method="POST">
						<textarea id="textS" name="replyC"></textarea>
						<button name="addBtnR" type="submit">등록</button>
					</form>
				</div>
			</div>
				</div>
			</div>
		</div>