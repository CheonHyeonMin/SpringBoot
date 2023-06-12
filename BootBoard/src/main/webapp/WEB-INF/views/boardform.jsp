<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
  <h2>풀스택반 게시판😎😎</h2>
  <div class="panel panel-default">
    <div class="panel-heading">게시판</div>
    <div class="panel-body">
    	  <!-- enctype(encoding-type) : 기본값 - application/x-www-form-urlcencoded -->
    	  <form class="form-horizontal" action="/fullstack/board/write" method="post" enctype="multipart/form-data">
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="title">제목:</label>
		      <div class="col-sm-10">
		      
		        <input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
		        
		      </div>
		    </div>
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="content">내용:</label>
		      <div class="col-sm-10">          
		        <textarea rows="10" class="form-control" name="content"></textarea>
		      </div>
		    </div>
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="writer">작성자:</label>
		      <div class="col-sm-10">          
		        <input type="text" class="form-control" name="writer">
		      </div>
		    </div>
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="photo">Photo:</label>
		      <div class="col-sm-10">          
		         <input type="file" class="form-control" id="photo" name="photo">
		      	 <img style="width: 500px;" id="preview-image" src="https://dummyimage.com/500x500/ffffff/000000.png&text=preview+image">
		      </div>
		     
		    </div>
		    <div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-success btn-default">작성</button>
		        <button type="reset" class="btn btn-warning btn-default">리셋</button>
		      </div>
		    </div>
		  </form>
    
    </div>
    <div class="panel-footer">FullStack SW융합 실무부트캠프 (담임 : 강예진)</div>
  </div>
</div>

<script>
//input(type=file) 파일이 추가되었다! -> 이벤트가 발생되었다!
	let photo = document.getElementById("photo")
	photo.addEventListener("change", e=>{
		readImage(e.target)
	})
	
	
	function readImage(input){
		//Input 태그에 파일이 있는 경우
		if(input.files && input.files[0]){
			//FileReader 생성
			const reader = new FileReader()
			reader.onload = e =>{  //FileReader 가 생성 되면
				//previewImage 공간에 추가한 이미지 출력
				const previewImage = document.getElementById("preview-image")
				console.log(e.target.result) //다운로드가 용이하게 만들어줌 
				previewImage.src = e.target.result
			}
			
			reader.readAsDataURL(input.files[0])
		}
}
</script>

</body>
</html>
