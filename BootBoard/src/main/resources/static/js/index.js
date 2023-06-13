
	
	
	/*boardform.html*/
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


/*boardlist.html*/
function goDelete(idx){
		$.ajax({
			url : "board/delete/"+idx,
			type : "get",
			success : loadList
			,
			error : function(){
				alert("안됨")
			}
			
		})
	}
	
	//DB(board) 전체 정보(data만!) 가져오기
	function loadList(){
		$.ajax({
			url : "board/ajax",
			type : "get",
			dataType : "json", //서버에서 반환해주는 데이터 타입
			success : updateTable,
			error : function(){
				alert("loadList 실패!")
			}
		})
	}
	
	function updateTable(data){
		var result = "<table class='table table-bordered table-hover'>";
		   result += "<thead>"
		   result += "<tr>";
		   result += "<th>번호</th>";
		   result += "<th>제목</th>";
		   result += "<th>작성자</th>";
		   result += "<th>작성일</th>";
		   result += "<th>삭제</th>";
		   result += "</tr>";
		   result += "</thead>"
		   //반복문
		   result += "<tbody>";
		   $.each(data, (index,vo)=> {  //vo -< Board , index(0~)
		      result += "<tr>";
		      result += "<td>"+vo.idx+"</td>";
		      result += "<td>"+vo.title+"</td>";
		      result += "<td>"+vo.writer+"</td>";
		      result += "<td>"+vo.indate+"</td>";
		      result += "<td><button class='btn btn-sm btn-success' onClick='goDelete("+vo.idx+")'>삭제</button></td>"
		      result += "</tr>";
		   })
		   result += "</tbody>";
		   result += "<tr>";
		   result += "<td colspan='6'>";
		   result += "<button onClick=\"location.href='board/writeform '\" class='btn btn-primary btn-sm'>글작성</button>";
		   result += "</td>";
		   result += "</tr>";
		   result += "</table>";
		   
		   $("#list").html(result)
	}