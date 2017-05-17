
//service_list.html 业务账号管理页面

$(function(){
	
	showBusiness();
	
	$('#search').click(searchBy);
	
})

//显示业务账号列表
function showBusiness(){
	
	$.ajax({
		url:host+"business/queryAll",
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				businesss = result.data;
				//将数据显示到页面
				$('.business').empty();
				for(var i=0;i<businesss.length;i++){
					var business = businesss[i];
					
					if(business.status == 0){ //开通
						var tr = '<tr class="business">'+
										'<td>'+business.businessId+'</td>'+
										'<td>'+business.accountId+'</td>'+
										'<td>'+business.idCard+'</td>'+
										'<td>'+business.realName+'</td>'+
										'<td><a href="service_detail.html?businessId='+business.businessId+'">'+business.osUserName+'</a></td>'+
										'<td>开通</td>'+
										'<td>'+business.unixHost+'</td>'+
										'<td>'+business.name+'</td>'+
										'<td class="td_modi">'+
											'<input type="button" value="暂停" class="btn_pause" onclick="setState(this);"/>'+
											'<input type="button" value="修改" class="btn_modify" onclick="modiBusiness(this);"/>'+
										'</td>'+
									'</tr>';
					}else{ //暂停
						var tr = '<tr class="business">'+
										'<td>'+business.businessId+'</td>'+
										'<td>'+business.accountId+'</td>'+
										'<td>'+business.idCard+'</td>'+
										'<td>'+business.realName+'</td>'+
										'<td><a href="service_detail.html?businessId='+business.businessId+'">'+business.osUserName+'</a></td>'+
										'<td>暂停</td>'+
										'<td>'+business.unixHost+'</td>'+
										'<td>'+business.name+'</td>'+
										'<td class="td_modi">'+
											'<input type="button" value="开通" class="btn_pause" onclick="setState(this);"/>'+
											'<input type="button" value="修改" class="btn_modify" onclick="modiBusiness(this);"/>'+
											'<input type="button" value="删除" class="btn_delete" onclick="deleteBusiness(this);" />'+
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

//根据条件查询业务账号
function searchBy(){
	
	//从页面获取查询条件
	var data = {
			'osUserName':$('#osUserName').val(),
			'idCard':$('#idcard').val(),
			'status':$('#status').val()
	}
	
	
	$.ajax({
		url:host+"business/queryBy",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				businesss = result.data;
				//将数据显示到页面
				$('.business').empty();
				for(var i=0;i<businesss.length;i++){
					var business = businesss[i];
					
					if(business.status == 0){ //开通
						var tr = '<tr class="business">'+
										'<td>'+business.businessId+'</td>'+
										'<td>'+business.accountId+'</td>'+
										'<td>'+business.idCard+'</td>'+
										'<td>'+business.realName+'</td>'+
										'<td><a href="service_detail.html">'+business.osUserName+'</a></td>'+
										'<td>开通</td>'+
										'<td>'+business.unixHost+'</td>'+
										'<td>'+business.name+'</td>'+
										'<td class="td_modi">'+
											'<input type="button" value="暂停" class="btn_pause" onclick="setState(this);"/>'+
											'<input type="button" value="修改" class="btn_modify" onclick="modiBusiness(this);"/>'+
										'</td>'+
									'</tr>';
					}else{ //暂停
						var tr = '<tr class="business">'+
										'<td>'+business.businessId+'</td>'+
										'<td>'+business.accountId+'</td>'+
										'<td>'+business.idCard+'</td>'+
										'<td>'+business.realName+'</td>'+
										'<td><a href="service_detail.html">'+business.osUserName+'</a></td>'+
										'<td>暂停</td>'+
										'<td>'+business.unixHost+'</td>'+
										'<td>'+business.name+'</td>'+
										'<td class="td_modi">'+
											'<input type="button" value="开通" class="btn_pause" onclick="setState(this);"/>'+
											'<input type="button" value="修改" class="btn_modify" onclick="modiBusiness(this);"/>'+
											'<input type="button" value="删除" class="btn_delete" onclick="deleteBusiness(this);" />'+
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
