<%@ page language="java" contentType="text/html; charset==UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计</title>
</head>
<body>
	<div id="main" style="height: 400px"></div>

	<script type="text/javascript"
		src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script type="text/javascript">
var myChart = echarts.init(document.getElementById('main')); 
	window.onloadss=function(){
		$.ajax({
			type:"GET",
			url:"${adminPath}/hotel/tbPriceinfo/toto",
			dataType:"json",
			data:{},
			success:function(result){
				   var option = {
	                	    title : {
	                	        text: '财务信息',
	                	        subtext: '酒店收入'
	                	    },
	                	    tooltip : {
	                	        trigger: 'axis'
	                	    },
	                	    legend: {
	                	        data:['财政收入','降水量']
	                	    },
	                	    toolbox: {
	                	        show : true,
	                	        feature : {
	                	            mark : {show: true},
	                	            dataView : {show: true, readOnly: false},
	                	            magicType : {show: true, type: ['line', 'bar']},
	                	            restore : {show: true},
	                	            saveAsImage : {show: true}
	                	        }
	                	    },
	                	    calculable : true,
	                	    xAxis : [
	                	        {
	                	            type : 'category',
	                	            data : result.createdate
	                	        }
	                	    ],
	                	    yAxis : [
	                	        {
	                	            type : 'value'
	                	        }
	                	    ],
	                	    series : [
	                	        {
	                	            name:'caizheng',
	                	            type:'bar',
	                	            data:result.income,
	                	            markPoint : {
	                	                data : [
	                	                    {type : 'max', name: '最大值'},
	                	                    {type : 'min', name: '最小值'}
	                	                ]
	                	            },
	                	            markLine : {
	                	                data : [
	                	                    {type : 'average', name: '平均值'}
	                	                ]
	                	            }
	                	        },	       
	                	    ]
	                	};
	                	                    
	        
	                // 为echarts对象加载数据 
	                myChart.setOption(option); 
	            }
		})
		};


     
    </script>

</body>
</html>