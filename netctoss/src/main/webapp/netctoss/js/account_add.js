
//account_add.html  添加账务账户页面

$(function(){
	
	$('#baocun').click(saveAccount);
	
})

//添加账务账号
function saveAccount(){
	
	//性别
	var gender; //0为男，1为女
	var isAutoSend = document.getElementsByName('radSex'); //获得单选框列表
	if (isAutoSend[0].checked == true) {
		gender = 1; 
	}else{
		gender = 0;
	}
	//密码
	var pwd1 = $('#pwd1').val();
	var pwd2 = $('#pwd2').val();
	if(pwd1 == pwd2){
		
		var data = {
				'realName':$('#realName').val(),
				'idCard':$('#idcard').val(),
				'loginName':$('#loginName').val(),
				'loginPassword':pwd1,
				'telephone':$('#telephone').val(),
				'email':$('#email').val(),
				'occupation':$('#occupation').val(),
				'gender':gender,
				'mailaddress':$('#mailaddress').val(),
				'zipCode':$('#zipCode').val(),
				'qq':$('#qq').val()
		}
		console.log(data);
		
		//保存
		$.ajax({
			url:host+"account/addAccount",
			type:"post",
			data:JSON.stringify(data),
			contentType:"application/json",
			dataType:"json",
			success:function(result){ //访问成功
				
				if("success"==result.state){
					alert("账务账号信息添加成功");
					console.log(result.data);
					window.location = "../account/account_list.html";
					
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
		window.location = "../account/account_add.html";
	}
	
}


