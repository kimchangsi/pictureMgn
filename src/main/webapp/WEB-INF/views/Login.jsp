<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#container{
		width:510px;
		margin:50px auto;
	
	}
	span{
		color:red;
		font-weight: bold;
		font-size: 12px;
		display: none;
	}
	input{
		width:500px;
		height:40px;
	}
	button{
		width:250px;
		height:40px;
		background: #4d90fe;
		border: 0;
		color:white; 
	}
	
	#button{
		border-right: 1px solid white;
	}
	
	h1{
	text-align: center;	
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#button").click(function(){
			$.ajax({
				url : "check2",
				type : "get",
				data : {"id" : $("input[name='id']").val(), "pass" : $("input[name='password']").val()},
				dataType : "text",
				success : function(data){
					console.log(data);
					if(data=="fail"){
						$("span").css("display","inline");
					}else if(data=="success"){
						$("form").submit();
					}
					
				}
			})
		})
		
		$("#join").click(function(){
			location.href = "join";
		})
		
		
		<c:if test="${result=='success' }">
			alert("가입성공. 로그인페이로 이동");
		</c:if> 
		
	})

</script>
</head>
<body>
<div id="container">
	<h1>로그인</h1>
	<form action="login" method="post">
		<input type="text" name="id" placeholder="아이디"><br><br>
		<input type="password" name="password" placeholder="비밀번호"><br>
		<span>아이디 또는 비밀번호를 다시 확인하세요.<br>등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하였습니다.</span>
		<br>
	
		<button type="button" id="button">로그인</button><button type="button" id="join">회원가입</button>
	</form>
</div>
</body>
</html>