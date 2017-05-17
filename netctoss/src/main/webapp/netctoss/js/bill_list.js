
//bill_list.html 账单管理页面，以账务账户为单位显示账单

$(function(){
	
	showBill();
	
	$('#search').click(searchBy);
	
})

//显示所有账单管理信息
function showBill(){
	
	$.ajax({
		url:host+"bill/queryAll",
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				bills = result.data;
				//将数据显示到页面
				$('.bill').empty();
				for(var i=0;i<bills.length;i++){
					var bill = bills[i];
					
					var year = moment(bill.date).format('YYYY');
					var month = moment(bill.date).format('MM');
					
					var tr ='<tr class="bill">'+
				        		'<td>'+bill.billId+'</td>'+
				        		'<td>'+bill.name+'</td>'+
				        		'<td>'+bill.idcard+'</td>'+
				        		'<td>'+bill.accountName+'</td>'+
				        		'<td>'+bill.monthCharge+'</td>'+
				        		'<td>'+bill.yearMonth+'</td>'+
				        		'<td></td>'+
				        		'<td></td>'+
				        		'<td><a href="bill_item.html?accountName='+bill.accountName+'&year='+year+'&month='+month+'&totalCharge='+bill.monthCharge+'" title="账单明细">明细</a></td>'+
				        	'</tr>';
					
					$('#datalist').append(tr);
					
					
				} //for
				
			}else{
				alert(result.message);
			}
			
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
}

//按条件查询账单管理
function searchBy(){
	
	var yearMonth = $('#selYears').val() + "年" + $('#selMonths').val() + "月";
	if($('#selMonths').val()=="全部"){
		yearMonth = "";
	}
	
	var data = {
			'yearMonth':yearMonth,
			'idcard':$('#idcard').val(),
			'name': $('#name').val()
	}
	console.log(data);
	
	//查询并显示
	$.ajax({
		url:host+"bill/queryBy",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				bills = result.data;
				//将数据显示到页面
				$('.bill').empty();
				for(var i=0;i<bills.length;i++){
					var bill = bills[i];
					
					var tr ='<tr class="bill">'+
				        		'<td>'+bill.billId+'</td>'+
				        		'<td>'+bill.name+'</td>'+
				        		'<td>'+bill.idcard+'</td>'+
				        		'<td>'+bill.accountName+'</td>'+
				        		'<td>'+bill.monthCharge+'</td>'+
				        		'<td>'+bill.yearMonth+'</td>'+
				        		'<td></td>'+
				        		'<td></td>'+
				        		'<td><a href="bill_item.html?accountName='+bill.accountName+'&yearMonth='+bill.yearMonth+'" title="账单明细">明细</a></td>'+
				        	'</tr>';
					
					$('#datalist').append(tr);
					
					
				} //for
				
			}else{
				alert(result.message);
			}
			
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
}







