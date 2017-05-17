
//admin_modi.html  管理员信息修改页面

$(function(){
	
	showAdmin();
	
	$('#baocun').click(updateAdmin);
	
})

//显示管理员信息
function showAdmin(){
	
	//获取网址参数
	var adminId = GetArgsFromHref();
	
	$.ajax({
		url:host+"admin/queryByAdminId/"+adminId[0],
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			if("success"==result.state){
				
				var admin = result.data;
				
				$('#name').val(admin.name);
				$('#loginName').val(admin.loginName);
				$('#telephone').val(admin.telephone);
				$('#email').val(admin.email);
				
				//设置下拉选
				var obj = document.getElementById('selModules');
				$.each(obj .options, function (i, n) {  
				    if (n.value == admin.roleId) {  
				        n.selected = true;  
				    }  
				});
				
			}else{
				alert(result.message);
			}
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
}



//保存管理员信息
function updateAdmin(){
	
	var adminId = GetArgsFromHref();	//获取网址参数
	
	var data = {
			'adminId':adminId[0],
			'name':$('#name').val(),
			'telephone':$('#telephone').val(),
			'email':$('#email').val(),
			'roleId':$('#selModules').val()
	}
	
	//保存
	$.ajax({
		url:host+"admin/updateAdmin",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				alert(result.message);
				window.location = "../admin/admin_list.html";
				
			}else{
				alert(result.message);
			}
			
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
}

