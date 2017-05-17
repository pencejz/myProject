
//service_add.html 业务账号增加页面

var model = {
		account:{}, //保存账务账号信息
		cost:[]	//保存资费类型信息
};
$(function(){
	
	showCosts(); //查询并显示所有可用资费类型
	
	$('#baocun').click(addBusiness);
	
	$('#searchAccount').click(queryAccountByIdcard);
	
})

//新增业务账户
function addBusiness(){
	
	//密码
	var pwd1 = $('#pwd1').val();
	var pwd2 = $('#pwd2').val();
	if(pwd1 == pwd2){
		
		var data = {
				'accountId':model.account.accountId,
				'costId':$('#costName').val(),
				'unixHost':$('#unixHost').val(),
				'loginPassword':pwd1,
				'osUserName':$('#osUserName').val()
		}
		console.log(data);
		
		//保存
		$.ajax({
			url:host+"business/addBusiness",
			type:"post",
			data:JSON.stringify(data),
			contentType:"application/json",
			dataType:"json",
			success:function(result){ //访问成功
				
				if("success"==result.state){
					console.log("保存成功");
					window.location = "../service/service_list.html";
					
				}else{
					alert(result.message);
				}
				
			},
			error:function(){ //访问失败
				alert("访问失败");
			}
		});
		
	}else{
		alert("两次输入的密码不一致");
		window.location = "../service/service_add.html";
	}
	
	
	
}

//根据身份证查询账务账号信息，并显示账务账号登录名称
function queryAccountByIdcard(){
	
	var idCard = $('#idcard').val();
	
	$.ajax({
		url:host+"account/queryByIdcard/"+idCard,
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				var account = result.data;
				
				if(account==null){	//不存该账务账户
					var r = window.confirm("该账务账户不存在，是否去创建新的账务账户？");
					if(r){
						window.location.href="../account/account_add.html";
					}
					
				}else if(account.status==0){	//符合条件，获得其登录名
					model.account = account;
					$('#accountName').val(account.loginName);
				}else{	//该账务账户存在，但是处于暂停状态
					var r = window.confirm("该账务账户处于暂停状态，是否去启用该账务账户？");
					if(r){
						window.location.href="../account/account_list.html";
					}
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

//查询并显示所有资费类型信息
function showCosts(){
	
	$.ajax({
		url:host+"cost/queryAll",
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				var costs = result.data;
				$('.cost').empty();
				for(var i=0;i<costs.length;i++){
					var cost = costs[i];
					
					//显示到页面
					if(cost.status==0){
						var op = '<option name="cost" value="'+cost.costId+'">'+cost.name+'</option>';
						$('#costName').append(op);
					}
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
















