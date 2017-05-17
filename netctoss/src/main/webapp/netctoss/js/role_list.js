
//role_list.html 角色管理页面

$(function(){
	
	showRole();
	
	
	
	
})

//显示角色
function showRole(){
	
	$.ajax({
		url:host+"rolePermission/queryAll",
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				roles = result.data;
				//将数据显示到页面
				$('.role').empty();
				for(var i=0;i<roles.length;i++){
					var role = roles[i];
					var permissionName = role.permissionName.substring(1) ;
					var tr = '<tr class="role">'+
								'<td>'+role.roleId+'</td>'+
								'<td>'+role.roleName+'</td>'+
								'<td>'+permissionName+'</td>'+
								'<td>'+
									'<input type="button" value="修改" class="btn_modify" onclick="updateRole(this);"/>'+
									'<input type="button" value="删除" class="btn_delete" onclick="deleteRole(this);" />'+
								'</td>'+
							'</tr>';
					$('#datalist').append(tr);
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











