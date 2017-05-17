
//admin_list.js  //管理员页面

$(function(){
	
	showAdmin(); //显示管理员列表
	
	$('#search').click(searchBy);
})

//显示管理员列表
function showAdmin(){
	
	$.ajax({
		url:host+"admin/queryAll",
		type:"post",
		dataType:"json",
		async: false,
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				admins = result.data;
				//将数据显示到页面
				$('.admin').empty();
				for(var i=0;i<admins.length;i++){
					var admin = admins[i];
					
					admin.enrollDate = moment(admin.enrollDate).format('YYYY-MM-DD ');
					
					//根据roleID获得角色名
					$.ajax({
						url:host+"rolePermission/queryRoleByRoleId/"+admin.roleId,
						type:"post",
						dataType:"json",
						async: false,
						success:function(result){ //访问成功
							if("success"==result.state){
								var role = result.data;
								var roleName = role.roleName;
								var tr ='<tr class="admin">'+
							        		'<td>'+admin.adminId+'</td>'+
							        		'<td>'+admin.name+'</td>'+
							        		'<td>'+admin.loginName+'</td>'+
							        		'<td>'+admin.telephone+'</td>'+
							        		'<td>'+admin.email+'</td>'+
							        		'<td>'+admin.enrollDate+'</td>'+
							        		'<td>'+roleName+'</td>'+
							        		'<td class="td_modi">'+
							        			'<input type="button" value="修改" class="btn_modify" onclick="updateDamin(this)" />'+
							        			'<input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(this);" />'+
							        		'</td>'+
							        	'</tr>';
								
								$('#datalist').append(tr);
								
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


//根据条件搜索管理员信息
function searchBy(){
	
	var data = {
			'name':$('#name').val(),
			'roleId':$('#selModules').val()
	}
	
	//查询并显示
	$.ajax({
		url:host+"admin/queryBy",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		async: false,
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				admins = result.data;
				//将数据显示到页面
				$('.admin').empty();
				for(var i=0;i<admins.length;i++){
					var admin = admins[i];
					
					admin.enrollDate = moment(admin.enrollDate).format('YYYY-MM-DD ');
					
					//根据roleID获得角色名
					$.ajax({
						url:host+"rolePermission/queryRoleByRoleId/"+admin.roleId,
						type:"post",
						dataType:"json",
						async: false,
						success:function(result){ //访问成功
							if("success"==result.state){
								var role = result.data;
								var roleName = role.roleName;
								var tr ='<tr class="admin">'+
							        		'<td>'+admin.adminId+'</td>'+
							        		'<td>'+admin.name+'</td>'+
							        		'<td>'+admin.loginName+'</td>'+
							        		'<td>'+admin.telephone+'</td>'+
							        		'<td>'+admin.email+'</td>'+
							        		'<td>'+admin.enrollDate+'</td>'+
							        		'<td>'+roleName+'</td>'+
							        		'<td class="td_modi">'+
							        			'<input type="button" value="修改" class="btn_modify" onclick="updateDamin(this)" />'+
							        			'<input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(this);" />'+
							        		'</td>'+
							        	'</tr>';
								
								$('#datalist').append(tr);
								
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
