
//account_detail.html  账务账户详情页

$(function(){
	
	showDetail();
	
})

//显示账务账户详情
function showDetail(){
	
	var accountId = GetArgsFromHref(); //从网址中获得参数
	
	$.ajax({
		url:host+"account/queryByAccountId/"+accountId[0],
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			if("success"==result.state){
				
				var account = result.data;
				
				$('#accountId').val(account.accountId);
				$('#realName').val(account.realName);
				$('#idcard').val(account.idCard);
				$('#loginName').val(account.loginName);
				$('#telephone').val(account.telephone);
				//状态、时间
				if(account.status==0){
					$('#status').val("开通");
					//开通时间
					$('#oneTime').val(moment(account.createDate).format('YYYY/MM/DD HH:mm:ss'));
				}else{
					$('#status').val("暂停");
					//暂停时间
					if(account.pauseDate != null){
						$('#oneTime').val(moment(account.pauseDate).format('YYYY/MM/DD HH:mm:ss'));
					}
				}
				
				//最近登录时间
				if(account.lastLoginTime != null){
					$('#lastLoginTime').val(moment(account.lastLoginTime).format('YYYY/MM/DD HH:mm:ss'));
				}else{
					$('#lastLoginTime').val("未登录过");
				}
				
				$('#email').val(account.email);
				$('#occupation').val(account.occupation); //职业
				//性别
				if(account.gender==0){
					$('#gender').val("男");
				}else{
					$('#gender').val("女");
				}
				$('#address').val(account.mailaddress);
				$('#zipcode').val(account.zipCode); //邮编
				$('#qq').val(account.qq);
				
			}else{
				alert(result.message);
			}
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
}
