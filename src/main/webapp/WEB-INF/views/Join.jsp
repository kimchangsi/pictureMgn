
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
		width: 960px;
		margin: 0 auto; 
	}
	
	h1{
		text-align: center;
	}

	label{
		width:120px;
		float: left;
	}
	span{
		color:red;
	}
	#check, #id, #name, #pass, #dup, #useable{
		display: none;
	}
	
	input[type='button'] {
	background: #4d90fe;
	color: white;
	border: 0;
}
	.bb {
	background: #4d90fe; 
	color: white;
	border: 0;
	width: 180px;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#button").click(function(){
			$("#dup").css("display","none");
			$("#useable").css("display","none");
			$.ajax({
				url : "check",
				type : "get",
				data : {"id" : $("input[name='id']").val()},
				dataType : "text",
				success : function(data){
					console.log(data);
					$("#id").css("display","none");
					var id = $("input[name='id']").val();
					var reg = /^[a-z0-9]{6,15}$/i;
					var result = reg.test(id);
					if(result==false){
						$("#id").css("display","inline");
						return false;
					}
					if(result==true && data!="success"){
					$("#dup").css("display","inline");
					}else{
					$("#useable").css("display","inline");	
					}
					
				}
			})
			
		})
		
		$("form").submit(function(){
			$("#id").css("display","none");
			$("#name").css("display","none");
			$("#pass").css("display","none");
			
			if( $("input[name='pass']").val() != $("input[name='password2']").val()){
				$("#check").css("display","inline");
				return false;
			}
			var id = $("input[name='id']").val();
			var reg = /^[a-z0-9]{6,15}$/i;
			var result = reg.test(id);
			
			var name = $("input[name='name']").val();
			var reg1 = /^[가-힣]{2,5}$/;
			var result1 = reg1.test(name);
			
			var pass = $("input[name='pass']").val();
			var reg2 = /^[a-z0-9~!@#$%^&*]{8,20}$/i;
			var result2 = reg2.test(pass);
			
			if(result==false){
				$("#id").css("display","inline");
				return false;
			}
			if(result1==false){
				$("#name").css("display","inline");
				return false;
			}
			if(result2==false){
				$("#pass").css("display","inline");
				return false;
			}
			
			
		})
		
	})

</script>
</head>
<body>
	<div id="container">
	<h1> 회원가입 화면</h1>
	<form action="join" method="post">
		<fieldset>
			<legend>회원가입</legend>
			
			
			<label>아이디</label>
			<input type="text" name="id">
			<input type="button" value="중복체크" id="button">
			<span id="dup">아이디가 중복되었습니다.</span>
			<span id="useable">사용할 수 있는 아이디입니다.</span>
			<span id="id">아이디를 조건에 맞게 입력하세요.</span>
			<br><br>
			
			
			<label>이름</label>
			<input type="text" name="name">
			<span id="name">이름을 조건에 맞게 입력하세요.</span>
			<br><br>
			<label>email</label>
			<input type="text" name="email"><br><br>
			
			
			<label>전화번호</label>
			<input type="text" name="tel"><br><br>
			
			
			<label>비밀번호</label>
			<input type="password" name="pass">
			<span id="pass">비밀번호를 조건에 맞게 입력하세요.</span>
			<br><br>
			
			
			<label>비밀번호 확인</label>
			<input type="password" name="password2">
			<span id="check">비밀번호가 다릅니다.</span>
			<br><br>
			<input type="submit" value="회원가입" class="bb"> <input type="reset" class="bb" value="취소">
		</fieldset>
	</form>
	</div>
</body>
</html>