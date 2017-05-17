
//role_mosi.html  角色管理修改页面

$(function(){
	
	showRole();
	
	$('#baocun').click(updateRolePermission);
	
	
})

//显示需要修改的角色权限信息
function showRole(){
	
var roleId = GetArgsFromHref(); //从网址中获得参数值组成的数组
	
	$.ajax({
		url:host+"rolePermission/queryRolePermissionByRoleId/"+roleId[0],
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			if("success"==result.state){
				var roles = result.data;
				for(var i=0;i<roles.length;i++){
					
					var role = roles[i];
					console.log(role.rolePermissionId);
					
					//设置角色名称
					$('#roleName').val(role.roleName);  
					
					//设置权限（多选框）
					var boxs = document.getElementsByName('permissionName');	//获得多选框组成的数组
					for (var j = 0; j < boxs.length; j++) {
				        if (boxs[j].value == role.permissionId) {
				        	console.log(boxs[j].value);
				        	boxs[j].checked = true;
				        }
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



//角色权限信息修改（角色ID不变）
function updateRolePermission(){
	
	var roleId = GetArgsFromHref(); //从网址中获得参数值组成的数组
	
	//根据角色ID删除角色权限信息
	$.ajax({
		url:host+"rolePermission/deletePermission/"+roleId[0],
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			if("success"==result.state){
				alert(result.message);
				
				//判断被选复单选框
				var boxs = document.getElementsByName('permissionName'); //获得单选框列表
				for (var i = 0; i < boxs.length; i++) {
			        if (boxs[i].checked == true) { //该权限被选中
			        	
			        	var data = {
			        			'roleId':roleId[0],
			        			'roleName':$('#roleName').val(),
			        			'permissionId':boxs[i].value
			        	}
			        	
			        	//保存
			        	$.ajax({
			        		url:host+"rolePermission/updateRolePermission",
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
			        } //if
			    }//for
				
			}else{
				alert(result.message);
			}
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
	
	
	
	
	
	
	
}


