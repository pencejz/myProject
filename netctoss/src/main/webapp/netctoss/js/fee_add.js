
//资费类型添加页面

$(function(){
	
	$('#baocun').click(addCost);
	
});


//保存
function addCost(){
	
	//从页面获取新的资费对象
	var costType;
	//判断被选中单选框
	var isAutoSend = document.getElementsByName('radFeeType'); //获得单选框列表
	for (var i = 0; i < isAutoSend.length; i++) {
        if (isAutoSend[i].checked == true) {
        	costType = i;
        }
    }
	
	var data = {
			'name':$('#costName').val(),			//获取资费名称
			'baseDuration':$('#baseDuration').val(),	//基本时长
			'baseCost':$('#baseCost').val(),		//基本费用
			'unitCost':$('#unitCost').val(),		//单位费用
			'descr':$('#descr').val(),			//资费描述
			'costType':costType		//资费类型(包年0,包月1,套餐2,计时收费3)
	}
	
	//保存
	$.ajax({
		url:host+"cost/addCost",
		type:"post",
		data:JSON.stringify(data),
		contentType:"application/json",
		dataType:"json",
		success:function(result){ //访问成功
			if("success"==result.state){
				window.location = "../fee/fee_list.html";
			}else{
				alert(result.message);
			}
		},
		error:function(){ //访问失败
			alert("访问失败");
		}
	});
	
	
	
}



