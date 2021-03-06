<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>    
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>
<%@ include file="/WEB-INF/views/layout/commonLib.jsp"%>

<script>
$(document).ready(function(){
	$('#memberList tr').on("click",function(){
// 		console.log("memberList tr click");
		// data-userid
		var userid = $(this).data("userid");
		console.log("userid : " + userid);

		document.location="/member?userid=" + userid;
		
	})
})

</script>

</head>

<body>

	<tiles:insertAttribute name="header"/>

	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<tiles:insertAttribute name="left"/>
			</div>
			<div class="col-sm-5 col-sm-offset-3 col-md-8 col-md-offset-2 main">
				<tiles:insertAttribute name="content"/>
			</div>
		</div>
	</div>
</body>
</html>
