
//service_detail.html  业务账号详情页

$(function(){
	
	showBusiness();
	
})

//显示业务账单详情
function showBusiness(){
	
	var businessId = GetArgsFromHref(); //从网址中获得参数
	
	$.ajax({
		url:host+"business/queryByBusinessId/"+businessId[0],
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			if("success"==result.state){
				
				var business = result.data;
				
				$('#businessId').val(business.businessId);
				$('#accountId').val(business.accountId);
				$('#realName').val(business.realName);
				$('#idcard').val(business.idCard);
				$('#unixHost').val(business.unixHost);
				$('#osUserName').val(business.osUserName);
				$('#createDate').val(moment(business.createDate).format('YYYY/MM/DD HH:mm:ss'));
				$('#costName').val(business.name);
				$('#unixHost').val(business.unixHost);
				
				//状态、时间
				if(business.status==0){
					$('#status').val("开通");
					$('#pauseDate').val("使用中");
				}else{
					$('#status').val("暂停");
					//暂停时间
					$('#pauseDate').val(moment(business.pauseDate).format('YYYY/MM/DD HH:mm:ss'));
				}
				
			}else{
				alert(result.message);
			}
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
	
	
	
	
	
	
}


