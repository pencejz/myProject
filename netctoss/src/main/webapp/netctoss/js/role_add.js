
//role_list.html  角色增加页面

$(function(){
	
	$('#baocun').click(saveRole);
	
	
})

//保存角色
function saveRole(){
	
	//获取页面信息
	var roleName = $('#roleName').val();
	
	//判断被选中复选框
	var isAutoSend = document.getElementsByName('permission'); //获取所有复选框
	var permissionIds = [];
	for (var i = 0; i < isAutoSend.length; i++) {
        if (isAutoSend[i].checked) {
        	permissionIds.push(isAutoSend[i].value);
        }
    }
	
	//向角色表中添加角色
	var data = { 'roleName':roleName }
	$.ajax({
		url:host+"rolePermission/addRole",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		success:function(result){ //访问成功
			if("success"==result.state){
				alert(result.message);
				
				//添加权限
				for(var i=0;i<permissionIds.length;i++){
					var data = {
							'roleName':roleName,
							'permissionId':permissionIds[i]
					}
					
					$.ajax({
						url:host+"rolePermission/addPermission",
						type:"post",
						data:JSON.stringify(data),
						contentType:"application/json",
						dataType:"json",
						success:function(result){ //访问成功
							if("success"==result.state){
								alert(result.message);
								window.location = "../role/role_list.html";
							}else{
								alert(result.message);
							}
						},
						error:function(){ //访问失败
							alert("访问失败");
						}
					});
				} //for
				
			}else{
				alert(result.message);
			}
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
	
	
	
}





