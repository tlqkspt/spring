<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>		<!-- loader.js받음 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">
   

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});	//다운패키지 지정	, , , ,나열가능

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);	//다 로드되고나면 콜백함수 호출

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        
        data.addColumn('string', '부서');
        data.addColumn('number', '인원수');
        //data.addColumn('number', '계약직');
        
        //여기 ajax들어가야됨
        var rows = [];
        														//비동기식 할려면 차트그리는걸 .done()안으로 넣어야됨(옵션,이벤트는 밖에둬도된다)
        $.ajax("./getEmpCnt", {dataType:"json", async:false})	//async:false 동기식 //화면다그린다음 json가져온다?
        .done(function(result){
        	for(i=0; i<result.length; i++){
        		rows.push( [ result[i].departmentName, result[i].cnt ]);
        	}
        })
        
        /* 
        data.addRows([
          ['개발', 3, 5],
          ['인사', 5, 0],
          ['총무', 5, 2]
        ]);
 		*/
 		data.addRows(rows);
 		
 		
        // Set chart options
        var options = {'title':'부서별인원수',
                       'width':400,
                       'height':300,
                       'legend' : 'top',			//범례? 위치
                       colors: ['#e0440e', 'green', 'blue'],	//색상지정
                       is3D: true,						//3d효과
                       vAxis: { gridlines: { count: 12 }, format : 'percent' },	//
                       bar: {groupWidth: '95%'}		//막대굵기
                       };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));	//PieChart() 파이차트 로	//차트그려질 id값
        chart.draw(data, options)
        // selectHandler() function.
		google.visualization.events.addListener(chart, 'select', selectHandler);
		
		function selectHandler(e) {	//선택한막대의 정보? 보기
				
			var r = chart.getSelection()[0].row;
			var c = chart.getSelection()[0].column;
			var dept = data.getFormattedValue(r,c);
			alert(dept);
			
			
			
		  //alert('A table row was selected');
		};
      }
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  </body>
</html>