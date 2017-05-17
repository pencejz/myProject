
//fee_detail.html	点击fee_list.html中资费名称跳转至此页面（资费详情页）

$(function(){
	
	showCost();
	
	
	
})

//根据资费ID查询资费信息并显示
function showCost(){
	
	var costId = GetArgsFromHref(); //从网址中获得参数
	
	$.ajax({
		url:host+"cost/queryByCostId/"+costId[0],
		type:"post",
		dataType:"json",
		success:function(result){ //访问成功
			if("success"==result.state){
				
				var cost = result.data;
				//时间
				if(cost.createTime != null){
					cost.createTime = moment(cost.createTime).format('YYYY-MM-DD HH:mm:ss');
				}
				if(cost.startTime != null){ //已开通
					cost.startTime = moment(cost.startTime).format('YYYY-MM-DD HH:mm:ss');
				}
				//状态
				if(cost.status==0){
					$('#costStatus').val("开通");
				}else{
					$('#costStatus').val("暂停");
				}
				//类型
				if(cost.costType==0){
					$('#costType').val("包年");
				}else if(cost.costType==1){
					$('#costType').val("包月");
				}else if(cost.costType==2){
					$('#costType').val("套餐");
				}else{
					$('#costType').val("计时");
				}
				
				$('#costId').val(cost.costId);
				$('#costName').val(cost.name);
				$('#baseDuration').val(cost.baseDuration);
				$('#baseCost').val(cost.baseCost);
				$('#unitCost').val(cost.unitCost);
				$('#createTime').val(cost.createTime);
				$('#startTime').val(cost.startTime);
				$('#descr').val(cost.descr);
				
			}else{
				alert(result.message);
			}
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
	
}


