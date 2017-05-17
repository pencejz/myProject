
//全局通用
$(function(){
	
	//添加导航栏
	addNavi();
	
});



var host = "http://localhost:8080/netctoss/";

//获得网址中的参数值数组
function GetArgsFromHref(){
	var arr = new Array(4); //保存接受的参数的值
	var sHref = window.location.href;  // 如：http://localhost:8080/wines/html/08tijiaodingdan.html?arg1=100&arg2=25
	console.log(sHref);
	var args = sHref.split("?");  
	var retval = "";
	if(args[0] == sHref){ /*参数为空*/
		return retval; /*无需做任何处理*/
	}
	var str = args[1]; //arg1=100&arg2=25
	args = str.split("&"); //arg1=100,arg2=25
	for(var i = 0; i < args.length; i ++){
		str = args[i];
		var arg = str.split("=");
		if(arg.length <= 1) continue;
		retval = arg[1];
		arr[i] = retval;
	}
	return arr;
}

//添加页面导航栏
function addNavi(){
	
	$('#menu').empty();
	
	var li =	
		'<li><a href="../index.html" class="index_off"></a></li>'+
	    '<li><a href="../role/role_list.html" class="role_off"></a></li>'+
	    '<li><a href="../admin/admin_list.html" class="admin_off"></a></li>'+
	    '<li><a href="../fee/fee_list.html" class="fee_off"></a></li>'+
	    '<li><a href="../account/account_list.html" class="account_off"></a></li>'+
	    '<li><a href="../service/service_list.html" class="service_off"></a></li>'+
	    '<li><a href="../bill/bill_list.html" class="bill_off"></a></li>'+
	    '<li><a href="../user/user_info.html" class="information_off"></a></li>'+
	    '<li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>';
	$('#menu').append(li);
	
}



