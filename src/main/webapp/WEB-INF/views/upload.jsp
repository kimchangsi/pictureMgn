<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
	#container{
		width: 960px;
		margin: 0 auto;
	}
	
	.imgs_wrap img{
		width: 300px;
		height: 300px; 
	}
	
	h1{
	text-align: center;
	}
	
	
</style>
</head>
<body>
<div id="container">
	<h1>사진 업로드</h1>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		<input type="file" name="picture" multiple="multiple" id="imgInp" class="bb"><br>
		<div class="imgs_wrap">
		</div>
		<input type="submit" value="등록하기" class="bb">
	</form>
	</div>
</body>
<script type="text/javascript">
	var sel_files = []; // 이미지 정보들을 담을 배열
	                           
	$("#imgInp").on("change", handleImgsFilesSelect);
	                
	function handleImgsFilesSelect(e){
		//이미지 정보들을 초기화
		sel_files = []; 
		$(".imgs_wrap").empty();
		
		
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		var index = 0;
		filesArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}
			
			sel_files.push(f);
			
			var reader = new FileReader();
			reader.onload = function(e){
				var img_html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("+index+")\" id=\"img_id_"+index+"\"><img src=\""+e.target.result+"\" data-file='"+f.name+"' class='selProductFile'/></a>";;
				$(".imgs_wrap").append(img_html);
			}
			reader.readAsDataURL(f);
		})
	}
	
	function deleteImageAction(index){
		sel_files.splice(index,1);
		
		var img_id = "#img_id_"+index;
		$(img_id).remove();
	}

</script>
</html>


