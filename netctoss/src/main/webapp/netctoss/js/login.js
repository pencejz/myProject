
//login.html	登录验证页面

$(function(){
	
	//获取验证码
	var url = host + "adminLogin/code?"+Math.random();
	$('#codeImg').click(function(){
		url = host + "adminLogin/code?"+Math.random();
		$(this).attr('src',url);
	});
	$('#codeImg').attr('src',url);
	
	
	$('#login').click(login);
	
	
})

//登录验证
function login(){
	
	var adminName = $('#adminName').val();
	var adminPwd = $('#adminPwd').val();
	var code = $('#code').val();
	
	var ok = true;
	if(adminName==""){
		ok = false;
		alert("登录名未填写");
	}
	if(adminPwd==""){
		ok = false;
		alert("密码未填写");
	}
	if(code==""){
		ok = false;
		alert("验证码未填写");
	}
	if(ok){
		$.ajax({
			url:host+"adminLogin/login/"+adminName+"/"+adminPwd+"/"+code,
			type:"post",
			dataType:"json",
			success:function(result){ //访问成功
				if("success" == result.state){
					
					window.location = "index.html";
				}else{
					alert(result.message);
				}
			},
			error:function(){ //访问失败
				alert("登录失败");
			}
		});
	
	}else{
		window.location = "login.html";
	}
	
	
}

