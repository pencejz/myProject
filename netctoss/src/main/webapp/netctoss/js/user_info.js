
//user_info.html	修改个人信息页面

$(function(){
	
	showAdmin();
	
	$('#baocun').click(saveAdmin);
	
	$('#quxiao').click(function(){
		//返回上一页
		window.history.go(-1); 
	});
	
})

//显示当前登录用户信息
function showAdmin(){
	
	$.ajax({
		url:host+"admin/queryByLoginName",
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				admin = result.data;
				
				$('#adminName').val(admin.loginName);
				$('#roleName').val(admin.roleName);
				$('#name').val(admin.name);
				$('#telephone').val(admin.telephone);
				$('#email').val(admin.email);
				$('#enrollDate').val(moment(admin.enrollDate).format('YYYY-MM-DD hh:mm:ss'));
				
			}	
		}
			
	});
}

//保存修改后的个人信息
function saveAdmin(){
	
	var data = {
			'name':$('#name').val(),
			'telephone':$('#telephone').val(),
			'email': $('#email').val()
	}
	
	//保存
	$.ajax({
		url:host+"admin/updateMessage",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				alert(result.message);
				window.location = "user_info.html";
				
			}else{
				alert(result.message);
			}
			
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
}

