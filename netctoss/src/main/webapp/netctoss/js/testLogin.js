//模拟用户登录

//页面加载完就调用
$(function(){
	$("#login").click(login);
});

//获取登录名和密码
function login(){
	
	//获取页面参数
	var userName = $("#userName").val().trim();
	var userPwd = $("#userPwd").val().trim();
	var ok = true;
	
	if(userName==""){
		ok = false;
		alert("用户名未填写");
	}
	if(userPwd==""){
		ok = false;
		alert("用户密码未填写");
	}
	if(ok){
		$.ajax({
			url:host+"testLogin/login/"+userName+"/"+userPwd,
			type:"post",
			dataType:"json",
			success:function(result){ //访问成功
				if("success" == result.state){
//					alert(result.message);
					//跳转到登出页面
					window.location = "testLogout.html?userName="+userName;
				}else{
					alert(result.message);
				}
			},
			error:function(){ //访问失败
				alert("登录失败");
			}
		});
	}
	
}