
//bill_service_item.html	账单详单页面，以业务账户一次完整登陆为单位显示页面

$(function(){
	
	showAccount();
	
	showBillDetail();
	
	
})

//显示账务账户相关信息
function showAccount(){
	
	//获取网址参数(accountName,businessName, year, month, charge)
	var list = GetArgsFromHref();
	var accountName = list[0];	//账务账号名称
	var businessName = list[1];	//业务账号名称
	var year = list[2];
	var month = list[3];
	var charge = list[4];		//一个业务账号一个月的费用
	var billItemId = list[5];	//账单明细ID
	
	var yearMonth = year+"年"+month+"月";
	
	$('#accountName').html(accountName);
	$('#businessName').html(businessName);
	$('#yearMonth').html(yearMonth);
	$('#charge').html(charge);
	
}

//显示一个业务账户一个月每次登陆信息
function showBillDetail(){
	
	//获取网址参数(accountName,businessName, year, month, charge)
	var list = GetArgsFromHref();
	var accountName = list[0];	//账务账号名称
	var businessName = list[1];	//业务账号名称
	var year = list[2];
	var month = list[3];
	var charge = list[4];	//一个业务账号一个月的费用
	var billItemId = list[5];	//账单明细ID
	
	$.ajax({
		url:host+"billDetail/queryByUnixHost/"+billItemId,
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				
				var billDetails = result.data;
				console.log(billDetails);
				//将数据显示到页面
				$('.detail').empty();
				for(var i=0;i<billDetails.length;i++){
					
					var billDetail = billDetails[i];
					
					//登入登出时间
					inTime = moment(billDetail.inTime).format('YYYY/MM/DD HH:mm:ss')
					outTime = moment(billDetail.outTime).format('YYYY/MM/DD HH:mm:ss')
					
					var tr ='<tr class="detail">'+
				        		'<td>'+billDetail.unixHost+'</td>'+
				        		'<td>'+inTime+'</td>'+
				        		'<td>'+outTime+'</td>'+
				        		'<td>'+billDetail.duration+'</td>'+
				        		'<td>'+billDetail.charge+'</td>'+
				        		'<td>'+billDetail.costName+'</td>'+
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




