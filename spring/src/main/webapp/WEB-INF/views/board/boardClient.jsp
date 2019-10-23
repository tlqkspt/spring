<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax board</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(function(){
		//목록조회 ajax요청
		getboard();
		insertBoard();
	
	});
	
	function insertBoard(){
		$("#btnIns").on("click", function(){
			//ajax 등록요청
			//var param = $("#frm").serialize();	//자동으로 쿼리문자열만들어줌?	
			param = [ {title:"title1", writer:"w1", content:"c1"},
					  {title:"title2", writer:"w2", content:"c2"},
					  {title:"title3", writer:"w3", content:"c3"} ]
			
			/* $.ajax("ajaxInsertBoard.json", 
					{ data: param, 
					  method:"post", 
					  dataType : "json"})	//data 등록한데이터 datatype 받을타입 */
			  
			$.ajax("ajaxInsertBoard.json", 			
					{ data: JSON.stringfy(param),		//객체를 제이슨구조 스트링으로 바꿔줌 
					  method:"post",
					  contentType : "application/json",	//넘어가는 형태가 제이슨형태다
					  dataType : "json"})		  
				
			 .done(function(data){	//요청끝나면 처리해라	//한건이라서 data씀
				$("<div>").html(data.seq + " : " + data.title)
				 .prependTo($("#boardList"));
			    $("#frm").reset();
				 
			 });
			//응답결과 div에 추가
		})
	}
	
	function getboard(){
		$.ajax("getBoardList.json", { dataType : "json" })	//url
		 .done(function(datas){
			 for(i=0; i<datas.length; i++)	{
			 	$("<div>").html(datas[i].title +"  "+ datas[i].writer)
			 			  .appendTo($("#boardList"));
			 }
			 
		 });	
	}
	
</script>
</head>

<body>
	
	<form  id="frm" action="insertBoard">	<!-- / 넣으면(루트) x  ./해야됨 -->
		제목<input name="title">
		작성자<input name="writer">
		내용<input name="content">
		<button type="button" id="btnIns">등록</button>
	</form>
	<div id="boardList">
		
	</div>
</body>
</html>