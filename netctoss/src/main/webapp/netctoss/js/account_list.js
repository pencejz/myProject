
//account_list.html 账务账号管理页面

$(function(){
	
	showAccount();
	
	$('#search').click(searchBy);
	
})

//显示账务账号
function showAccount(){
	
	$.ajax({
		url:host+"account/queryAll",
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				accounts = result.data;
				//将数据显示到页面
				$('.account').empty();
				for(var i=0;i<accounts.length;i++){
					var account = accounts[i];
					
					account.createDate = moment(account.createDate).format('YYYY-MM-DD '); //账务账号创建时间
					//上次登录时间
					var lastLoginTime;
					if(account.lastLoginTime != null){
						lastLoginTime = moment(account.lastLoginTime).format('YYYY-MM-DD HH:mm:ss'); 
					}else{
						lastLoginTime = "未登录";
					}
					
					if(account.status == 0){ //开通
						var tr = '<tr class="account">'+
										'<td>'+account.accountId+'</td>'+
										'<td><a href="account_detail.html?accountId='+account.accountId+'">'+account.realName+'</a></td>'+
										'<td>'+account.idCard+'</td>'+
										'<td>'+account.loginName+'</td>'+
										'<td>开通</td>'+
										'<td>'+account.createDate+'</td>'+
										'<td>'+lastLoginTime+'</td>'+
										'<td>'+
											'<input type="button" value="暂停" class="btn_pause" onclick="setState(this);"/>'+
											'<input type="button" value="修改" class="btn_modify" onclick="modiAccount(this);"/>'+
										'</td>'+
									'</tr>';
					}else{ //暂停
						var tr = '<tr class="account">'+
									'<td>'+account.accountId+'</td>'+
									'<td><a href="account_detail.html?accountId='+account.accountId+'">'+account.realName+'</a></td>'+
									'<td>'+account.idCard+'</td>'+
									'<td>'+account.loginName+'</td>'+
									'<td>暂停</td>'+
									'<td>'+account.createDate+'</td>'+
									'<td>'+lastLoginTime+'</td>'+
									'<td>'+
										'<input type="button" value="开通" class="btn_pause" onclick="setState(this);"/>'+
										'<input type="button" value="修改" class="btn_modify" onclick="modiAccount(this);"/>'+
										'<input type="button" value="删除" class="btn_delete" onclick="deleteAccount(this);"/>'+
									'</td>'+
								'</tr>';
					}
					
					$('#datalist').append(tr);
					
				} //for
				
			} //if
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
}

//根据条件查询
function searchBy(){
	
	//从页面获取查询条件
	var data = {
			'realName':$('#name').val(),
			'idCard':$('#idcard').val(),
			'status':$('#status').val()
	}
	
	
	$.ajax({
		url:host+"account/queryBy",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				accounts = result.data;
				//将数据显示到页面
				$('.account').empty();
				for(var i=0;i<accounts.length;i++){
					var account = accounts[i];
					
					account.createDate = moment(account.createDate).format('YYYY-MM-DD '); //账务账号创建时间
					//上次登录时间
					var lastLoginTime;
					if(account.lastLoginTime != null){
						lastLoginTime = moment(account.lastLoginTime).format('YYYY-MM-DD HH:mm:ss'); 
					}else{
						lastLoginTime = "未登录";
					}
					
					if(account.status == 0){ //开通
						var tr = '<tr class="account">'+
										'<td>'+account.accountId+'</td>'+
										'<td><a href="account_detail.html?accountId='+account.accountId+'">'+account.realName+'</a></td>'+
										'<td>'+account.idCard+'</td>'+
										'<td>'+account.loginName+'</td>'+
										'<td>开通</td>'+
										'<td>'+account.createDate+'</td>'+
										'<td>'+lastLoginTime+'</td>'+
										'<td>'+
											'<input type="button" value="暂停" class="btn_pause" onclick="setState(this);"/>'+
											'<input type="button" value="修改" class="btn_modify" onclick="modiAccount(this);"/>'+
										'</td>'+
									'</tr>';
					}else{ //暂停
						var tr = '<tr class="account">'+
									'<td>'+account.accountId+'</td>'+
									'<td><a href="account_detail.html?accountId='+account.accountId+'">'+account.realName+'</a></td>'+
									'<td>'+account.idCard+'</td>'+
									'<td>'+account.loginName+'</td>'+
									'<td>暂停</td>'+
									'<td>'+account.createDate+'</td>'+
									'<td>'+lastLoginTime+'</td>'+
									'<td>'+
										'<input type="button" value="开通" class="btn_pause" onclick="setState(this);"/>'+
										'<input type="button" value="修改" class="btn_modify" onclick="modiAccount(this);"/>'+
										'<input type="button" value="删除" class="btn_delete" onclick="deleteAccount(this);"/>'+
									'</td>'+
								'</tr>';
					}
					
					$('#datalist').append(tr);
					
				} //for
				
			} //if
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
}

