
//service_modi.html	业务账号修改页面

$(function(){
	
	showCosts(); //显示资费类型
	
	showBusiness();
	
	$('#baocun').click(saveBusiness);
	
})

//显示业务账号
function showBusiness(){
	
	//获取网址参数
	var businessId = GetArgsFromHref();
	
	$.ajax({
		url:host+"business/queryByBusinessId/"+businessId[0],
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			if("success"==result.state){
				
				var business = result.data;
				
				$('#businessId').val(business.businessId);
				$('#osUserName').val(business.osUserName);
				$('#unixHost').val(business.unixHost);
				
				//设置下拉选
				var obj = document.getElementById('costName');
				$.each(obj .options, function (i, n) {  
				    if (n.value == business.costId) {  
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

//保存修改后的业务账号信息
function saveBusiness(){
	
	var businessId = $('#businessId').val();
	var flag = $('#costName').val();
	
	//保存
	$.ajax({
		url:host+"business/updateFlag/"+businessId+"/"+flag,
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				alert("资费类型修改成功，由月底统一触发修改");
				window.location = "../service/service_list.html";
				
			}else{
				alert(result.message);
			}
			
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
}

//显示资费类型
function showCosts(){
	
	$.ajax({
		url:host+"cost/queryAll",
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				console.log(result.data);
				var costs = result.data;
				$('.cost').empty();
				for(var i=0;i<costs.length;i++){
					var cost = costs[i];
					
					//显示到页面
					if(cost.status==0){
						var op = '<option name="cost" value="'+cost.costId+'">'+cost.name+'</option>';
						$('#costName').append(op);
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