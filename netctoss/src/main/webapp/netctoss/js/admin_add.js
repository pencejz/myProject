
//admin_add.html 管理员添加页面

$(function(){
	
	$('#baocun').click(saveAdmin);
	
})

//添加管理员
function saveAdmin(){
	
	var pwd1 = $('#pwd1').val();
	var pwd2 = $('#pwd2').val();
	if(pwd1 == pwd2){
		
		var data = {
				'name':$('#name').val(),
				'loginName':$('#loginName').val(),
				'password':$('#pwd1').val(),
				'telephone':$('#telephone').val(),
				'email':$('#email').val(),
				'roleId':$('#selModules').val(),
		}
		
		//保存
		$.ajax({
			url:host+"admin/addAdmin",
			type:"post",
			data:JSON.stringify(data),
			contentType:"application/json",
			dataType:"json",
			success:function(result){ //访问成功
				if("success"==result.state){
					window.location = "../admin/admin_list.html";
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
		window.location = "../admin/admin_add.html";
	}
	
	
}