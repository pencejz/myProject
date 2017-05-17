
//bill_item.html 账单细节页面 以业务账户为单位显示账单

$(function(){
	
	showAccount();
	
	showBillItem();
	
})

//显示账务账户信息
function showAccount(){
	
	//获取网址参数(accountName,year,month,totalCharge)
	var list = GetArgsFromHref();
	var loginName = list[0];	//账务账号名称
	var year = list[1];
	var month = list[2];
	var totalCharge = list[3];	//一个账务账户一个月的费用（即就是一个账务账户下所有业务账户一个月的费用）
	
	var yearMonth = year+"年"+month+"月";
	
	$.ajax({
		url:host+"account/queryByLoginName/"+loginName,
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				account = result.data;
				//将数据显示到页面
				$('#accountName').html(account.loginName);
				$('#idcard').html(account.idCard);
				$('#name').html(account.realName);
				$('#yearMonth').html(yearMonth);
				$('#totalCharge').html(totalCharge);
				
			}else{
				alert(result.message);
			}
			
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
	
}

//显示一个账务账户下的所有业务账户每个月的账单信息
function showBillItem(){
	
	//获取网址参数(accountName,yearMonth,totalCharge)
	var list = GetArgsFromHref();
	var accountName = list[0];
	var year = list[1];
	var month = list[2];
	var totalCharge = list[3];
	
	$.ajax({
		url:host+"billItem/queryByAccountName/"+accountName+"/"+year+"/"+month,
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				
				var billItems = result.data;
				console.log(billItems);
				//将数据显示到页面
				$('.billItem').empty();
				for(var i=0;i<billItems.length;i++){
					
					var billItem = billItems[i];
					
					var tr ='<tr class="billItem">'+
				        		'<td>'+billItem.billItemId+'</td>'+
				        		'<td>'+billItem.osUsername+'</td>'+
				        		'<td>'+billItem.unixHost+'</td>'+
				        		'<td>'+billItem.accountId+'</td>'+
				        		'<td>'+billItem.duration+'</td>'+
				        		'<td>'+billItem.businessCharge+'</td>'+
				        		'<td>'+billItem.costName+'</td>'+
				        		'<td><a href="bill_service_detail.html?accountName='+accountName+
				        											'&businessName='+billItem.osUsername+
				        											'&year='+year+
				        											'&month='+month+
				        											'&charge='+billItem.businessCharge+
				        											'&billItemId='+billItem.billItemId+
				        											'" title="业务详单">详单</a></td>'+
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

