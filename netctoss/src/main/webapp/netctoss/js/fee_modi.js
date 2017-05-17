
//fee_modi.html  点击fee_list.html中修改跳转至此页面（资费修改页）

$(function(){
	
	showCost();
	
	$('#baocun').click(saveCost);
	
});

//根据资费ID查询资费信息并显示
function showCost(){
	
	var costId = GetArgsFromHref(); //从网址中获得参数值组成的数组
	
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
				var costType;
				var radios = document.getElementsByName('radFeeType');	//获得单选框组成的数组
				for (var i = 0; i < radios.length; i++) {	//判断被选中单选框
			        if (i == cost.costType) {
			        	radios[i].checked = true;
			        }
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

//保存修改后的cost
function saveCost(){
	
	//从页面获取新的资费对象
//	var costType;
//	//判断被选中单选框
//	var isAutoSend = document.getElementsByName('radFeeType'); //获得单选框列表
//	for (var i = 0; i < isAutoSend.length; i++) {
//        if (isAutoSend[i].checked == true) {
//        	costType = i;
//        }
//    }
	
	var data = {
			'costId':$('#costId').val(),
			'name':$('#costName').val(),			//获取资费名称
			'baseDuration':$('#baseDuration').val(),	//基本时长
			'baseCost':$('#baseCost').val(),		//基本费用
			'unitCost':$('#unitCost').val(),		//单位费用
			'descr':$('#descr').val(),			//资费描述
			'costType':$("input[name='radFeeType']:checked").val()		//资费类型(包年0,包月1,套餐2,计时收费3)
	}
	
	//保存
	$.ajax({
		url:host+"cost/updateCost",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		success:function(result){ //访问成功
			
			if("success"==result.state){
				alert(result.message);
				window.location.href = "fee_list.html?nocache="+new Date().getTime();
				
			}else{
				alert(result.message);
			}
			
		}
	});
	
}



//根据ID查询显示，然后修改，最后保存修改，保存后，跳转到list页面，取消直接跳转到list页面