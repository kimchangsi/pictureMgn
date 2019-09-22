<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	html,body{
	width: 100%;
	height: 100%;
	}
	p{
		text-align: right;
	}
	a{
		text-decoration: none;
		color: black;
	}
	
	h1{
		text-align: center;         
	}
	#container{
		width:1500px;
		margin:30px auto;
		overflow: hidden;
	}
	.picture{
		width:300px;
		margin-left:100px;
		margin-bottom:20px;
		float: left;
		position: relative;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		border: 1px solid #4d90fe; s
	}
	.upload{
		overflow: hidden;
		margin-bottom:20px;
	}
	.cancel{
		position: absolute;
		top : 0;
		right: 5px;
	}
	.uploadDate, .uploadName{
		text-align: left;
		margin:10px 0;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		padding-left: 20px;
	}
	.img{
		cursor: pointer;
		margin-left: 46px;
		width: 200px;
		height: 200px;
	}
	#bigImg{
		border:1px solid black;
		position:relative;
		position: absolute;
		top:150px;
		left:600px;
		display: none;
		background: #4d90fe;
		border: 0;
	}
	#bigImg span{
		position: absolute;
		top:0;
		font-size:20px;
		right:5px;
		color:white;
		cursor: pointer;
	}           
	#bigImg img{
		margin:50px;      
		width:600px;
		height:400px;
	}
	.pagination{
		list-style: none;
		overflow: hidden;
		clear: both;
		width:250px;
		margin:20px auto;
		padding:0;
	}
	.pagination li{
		float: left;
		width:20px;
		text-align:center;
		border: 1px solid black;
		background: white;
	}
	.pagination li a{
		font-weight: normal;
		color:black;
	}
	.active{
		background: black !important;
		
	}
	.active a{
		font-weight: bold !important;
		color:white !important;
	}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".cancel").click(function(){
		var a = confirm("삭제하시겠습니까?");
			if(a==false){
				return false;
			}
		})
		
		$(document).on("click",".img",function(){
			$("#bigImg").find("img").remove();
			$("#bigImg").css("display","inline");
			var $img = $("<img>");
			var ori = $(this).attr("data-origin");
			$img.attr("src", "displayFile?filename="+ori);
			$("#bigImg").append($img);
			$("body").css("background-color","rgba(0,0,0,0.5)");
		})
		$(document).on("click","#close",function(){
			$("#bigImg").css("display","none");
			$("body").css("background-color","white");                
		})
		
	})
</script>
</head>
<body>
<div id="all">
	<div id="container">
		<h1>사진 리스트</h1>
		<p>[${AUTH }]님 로그인하셨습니다. <a href="logout">[로그아웃]</a> </p>
		<p><a href="upload">[사진업로드]</a></p>
		
		<c:forEach var="list" items="${list }">
			<div class="picture">
				<div class="upload">
					<p class="uploadDate"><b>업로드 날짜</b> : <fmt:formatDate value="${list.regdate }" pattern="yyyy년 MM월 dd일"/></p>
					<p class="uploadName"><b>파일명</b> : ${list.originFileName }</p>
				</div>
				<img src="displayFile?filename=${list.file }" class="img" data-origin="${list.originFile }">         
				<a href="delete?no=${list.no }&file=${list.file }" class="cancel">X</a>
			</div>
		</c:forEach>
		
		<ul class="pagination">
			<c:if test="${page.prev }">
				<li><a href="List?page=${page.startPage-10 }"> &laquo; </a></li>
			</c:if>
			<c:forEach  var="idx" begin="${page.startPage }" end="${page.endPage }">
				<li ${page.cri.page == idx ? 'class="active"' : '' }><a href="${pageContext.request.contextPath }/List?page=${idx }">${idx }</a></li>
			</c:forEach>
			<c:if test="${page.next }">
				<li><a href="List?page=${page.endPage+1 }"> &raquo; </a></li>
			</c:if>
		</ul>
	</div>
	</div>
	<div id="bigImg">
		<span id="close"><b>X</b></span>
	</div>
</body>
</html>




