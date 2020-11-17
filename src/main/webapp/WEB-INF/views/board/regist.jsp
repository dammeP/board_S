<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>	

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="/js/summernote-ko-KR.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<link href="${cp }/css/dashboard.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script>
	$(document).ready(function() {
		$('#summernote').summernote({
  		  height: 300,                 // 에디터 높이
  		  minHeight: null,             // 최소 높이
  		  maxHeight: null,             // 최대 높이
  		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
  		  lang: "ko-KR",					// 한글 설정
  		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
            
  		});
// 		$('#regBtn').on('click',function(){
// 			$('#frm').submit();
// 		})
  		
	});

</script>

				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시글 등록</h2>
						<form id="frm" class="form-horizontal" role="form" action="${cp}/board/regist?cboardNo=${cboardNo}" method="POST" enctype="multipart/form-data">
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">글 제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="boardTitle" name="boardTitle" placeholder="글 제목을 입력하세요" />
								</div>
							</div>

							<div class="form-group">
								<label for="userid" class="col-sm-2 control-label">글 내용</label>
								<div class="col-sm-10">
									  <textarea id="summernote" name="boardContent"></textarea> 	
								</div>
							</div>
							
							<div class="form-group">
								<label for="userid" class="col-sm-2 control-label">첨부파일</label>
								<div class="col-sm-10">
									  <input type="file" name="fileName1" />
								</div>
							</div>
							<div class="form-group">
								<label for="userid" class="col-sm-2 control-label">첨부파일</label>
								<div class="col-sm-10">
									  <input type="file" name="fileName2" />
								</div>
							</div>
							<div class="form-group">
								<label for="userid" class="col-sm-2 control-label">첨부파일</label>
								<div class="col-sm-10">
									  <input type="file" name="fileName3" />
								</div>
							</div>
							<div class="form-group">
								<label for="userid" class="col-sm-2 control-label">첨부파일</label>
								<div class="col-sm-10">
									  <input type="file" name="fileName4" />
								</div>
							</div>
							<div class="form-group">
								<label for="userid" class="col-sm-2 control-label">첨부파일</label>
								<div class="col-sm-10">
									  <input type="file" name="fileName5" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" name="regBtn" value="등록">
								</div>
							</div>
						</form>
					</div>
				</div>
