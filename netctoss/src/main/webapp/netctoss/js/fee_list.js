
//资费管理页面fee_list.html

var model = {
		costs:[]	//资费列表
};



//页面加载执行
$(function(){
	
	showCost();
	
});

//启用资费信息和删除资费信息在fee_list.html中显示

//显示资费信息
function showCost(){
	
	$.ajax({
		url:host+"cost/queryAll",
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
//				console.log(result.data);
				costs = result.data;
				//将数据显示到页面
				$('.cost').empty();
				for(var i=0;i<costs.length;i++){
					var cost = costs[i];
					
					//将时间毫秒值转化为日期格式
					if(cost.createTime != null){
						cost.createTime = moment(cost.createTime).format('YYYY-MM-DD HH:mm:ss');
					}
					if(cost.startTime != null){ //已开通
						cost.startTime = moment(cost.startTime).format('YYYY-MM-DD HH:mm:ss');
					}
					
					//状态为0则显示开通，状态为1则显示暂停
					var status = "";
					if(0 == cost.status){
						status = "开通";
						var tr ='<tr class="cost">'+
			            			'<td>'+cost.costId+'</td>'+
			            			'<td><a href="fee_detail.html?costId='+cost.costId+'">'+costs[i].name+'</a></td>'+
			            			'<td>'+cost.baseDuration+'</td>'+
			            			'<td>'+cost.baseCost+'</td>'+
			            			'<td>'+cost.unitCost+'</td>'+
			            			'<td>'+cost.createTime+'</td>'+
			            			'<td>'+cost.startTime+'</td>'+
			            			'<td>'+status+'</td>'+
			            			'<td></td>'+
			            		'</tr>';
					}else{
						status = "暂停";
						var tr ='<tr class="cost">'+
			            			'<td>'+cost.costId+'</td>'+
			            			'<td><a href="fee_detail.html?costId='+cost.costId+'">'+costs[i].name+'</a></td>'+
			            			'<td>'+cost.baseDuration+'</td>'+
			            			'<td>'+cost.baseCost+'</td>'+
			            			'<td>'+cost.unitCost+'</td>'+
			            			'<td>'+cost.createTime+'</td>'+
			            			'<td></td>'+
			            			'<td>'+status+'</td>'+
			            			'<td>'+
			            				'<input type="button" value="启用" class="btn_start" onclick="updateCost(this);" />'+
			            				'<input type="button" value="修改" class="btn_modify" onclick="modiCost(this);" />'+
			            				'<input type="button" value="删除" class="btn_delete" onclick="deleteCost(this);" />'+
			            			'</td>'+
			            		'</tr>';
					}
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







