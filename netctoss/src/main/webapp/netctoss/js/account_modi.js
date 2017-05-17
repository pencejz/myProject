
//account_modi.html  账务账户修改页面

$(function(){
	
	showAccount();
	
	$('#baocun').click(saveAccount);
	
})

//显示需要修改的业务账户信息
function showAccount(){
	
	//获取网址参数
	var accountId = GetArgsFromHref();
	
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
				$('#email').val(account.email);
				$('#occupation').val(account.occupation); //职业
				//类型
				var radios = document.getElementsByName('radSex');	//获得单选框组成的数组
		        if (0 == account.gender) {
		        	radios[1].checked = true;
		        }else{
		        	radios[0].checked = true;
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

//保存修改后的账务账户信息
function saveAccount(){
	
var accountId = GetArgsFromHref();	//获取网址参数

	//类型
	var gender;
	var radios = document.getElementsByName('radSex');	//获得单选框组成的数组
	if (radios[0].checked) {
		gender = 1;
	}else{
		 gender = 0;
	}
	
	var data = {
			'accountId':accountId[0],
			'telephone':$('#telephone').val(),
			'email':$('#email').val(),
			'occupation':$('#occupation').val(),
			'gender':gender,
			'mailaddress':$('#address').val(),
			'zipCode':$('#zipcode').val(),
			'qq':$('#qq').val()
	}
	
	//保存
	$.ajax({
		url:host+"account/updateAccount",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				alert(result.message);
				window.location = "../account/account_list.html";
				
			}else{
				alert(result.message);
			}
			
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
}

