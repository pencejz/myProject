
//user_modi_pwd.html	密码修改页面

$(function(){
	
	$('#baocun').click(savePwd);
	
	$('#quxiao').click(function(){
		//返回上一页
		window.history.go(-1); 
	});
	
})

//保存修改后的密码
function savePwd(){
	
	var oldPwd = $('#oldPwd').val();
	var pwd1 = $('#pwd1').val();
	var pwd2 = $('#pwd2').val();
	
	if(pwd1 == pwd2){
		
		//保存
		$.ajax({
			url:host+"admin/updatePwd/"+oldPwd+"/"+pwd1,
			type:"post",
			dataType:"json",
			success:function(result){ //访问成功
				
				if("success"==result.state){
					alert(result.message);
					
					window.location = "../login.html";
					
				}else{
					alert(result.message);
				}
				
			},
			error:function(){ //访问失败
				alert("访问失败");
			}
		});
		
	}else{
		alert("两次输入的新密码不相同");
		window.location = "../user/user_modi_pwd.html";
	}
	
	
}

