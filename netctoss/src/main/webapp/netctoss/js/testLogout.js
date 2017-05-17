//模拟用户登录

//页面加载完就调用
$(function(){
	
	//显示欢迎页面
	$("#userName").html("你好&nbsp;"+getUserName()+"&nbsp;欢迎登录！");
	//点击登出
	$("#logout").click(logout);
	
});

//获取地址参数userName
function getUserName(){
	var userName = GetArgsFromHref();
	return userName[0];
}

//登出方法
function logout(){
	
	$.ajax({
		url:host+"testLogin/save",
		type:"post",
		dataType:"json",
		success:function(result){
			if("success" == result.state){
				alert(result.message);
			}else{
				alert(result.message);
			}
		},
		error:function(){
			alert("退出失败");

		}
	});
	
}