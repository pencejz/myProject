
//购物车

var model = {
		currentGoods:{}, //当前选中的商品
		carts:[], //购物车中商品列表
		goodsIds:[], //选中的商品的goodsIds
		goodsId:{}, //当前选中的商品ID
		number:{}, 
		goodsImages:[]
};

//页面加载时调用
$(function(){
	//显示购物车
	showCart();
	//绑定全选按钮
	$('#check_all').click(queryAll);
	//绑定批量删除按钮
	$('#batchRemove').click(batchDelete);
	//从地址中获取goodsId
//	GetArgsFromHref();
	
});

//全选购物车中商品（根据用户ID找到购物车中商品）
function queryAll(){
	url = "http://localhost:8080/wines/cart/queryGoodsByUserId";
	$.getJSON(url,function(result){
		var list = result.data;
		//更新数据模型
		model.carts = list;
		console.log(model.carts);
	});
}
var cart;
//显示购物车
function showCart(){
	//根据用户ID找到购物车中商品
	url = "http://localhost:8080/wines/cart/queryGoodsByUserId";
	$.ajax({
	  url: url,
	  dataType: "json",
	  async:false,
	  success: function(result){
		var list = result.data;
		
		var ldiv = $('.product_list').html('');
		for(var i=0;i<list.length;i++){
			var cart = list[i];
//			console.log(cart);
			
			//根据商品ID查询商品和图片
			url = "http://localhost:8080/wines/goods/findByGoodsId/"+list[i].goodsId;
			$.ajax({
				  url: url,
				  dataType: "json",
				  async:false,
				  success: function(result){
				var goodsImages = result.data;
//				console.log(cart);
//				console.log(goodsImages);
				model.goodsId = cart.goodsId;
				
				console.log(cart.goodsId);
				console.log(model.goodsId);
				
				var mdiv = 
					'<div class="product">'+
						'<input type="checkbox" name="ch1" cartid="'+cart.id+'" class="checkone"/>'+
						'<div class="product_d1">'+
							'<img src="./../../upload/'+goodsImages[0].fileName+'" alt=""/>'+
							'<p>'+cart.name+'</p>'+
							'<span class="capacity">规格：大瓶500ml</span>'+
							'<div class="counter">'+
								'<p class="reduce" cartid="'+cart.id+'">-</p>'+
								'<span class="number">'+cart.number+'</span>'+
								'<p class="add" cartid="'+cart.id+'">+</p>'+
							'</div>'+
							'<span class="money">'+cart.discountPrice+'</span>'+
							'<s>'+cart.price+'</s>'+
							'<span class="delete_s1" ><img src='+"../images/zenbenli/shopping_cart_03.png"+' alt=""/></sapn>'+
						'</div>'+
					'<div class="sf_delete">'+
		            '是否要删除？'+
		            	'<a href="#" class="delete_yes">是</a>'+
		            	'<a href="#" class="delete_no">否</a>'+
		            '</div>'+
		          '</div>';
				ldiv.append(mdiv);
				
			}}); //内
			
		}
		
		//自增自减
		$(".counter>p").click(function(){
	       var content=$(this).html();
	       var nbr=parseInt( $(this).parent().find('span').html());
	       if(content=="+"){
	            nbr++;
	           $(this).prev().html(nbr);
	       }
	       if(content=="-"){
	           nbr--;
	           if(nbr<1){
	               nbr=1
	           }
	           $(this).next().html(nbr);
	       }
	   })
	   
	   $(".delete_s1").click(function(){
	       $(this).parent().parent().children().last().fadeIn();
	       $(".sf_delete>a").click(function(){
	           if( $(this).html()=="是"){
	               $(this).parent().parent().remove();
	           }else if($(this).html()=="否"){
	               $(this).parent().fadeOut();
	           }
	       })
	   })
	   $("#check_all").click(function(){
	       if($("#check_all").prop('checked')){
	           $("input[name='ch1']").prop("checked",true);
	           $(".main_header>p").click(function(){
	               var ipt=$(".product>input");
	               for(var i=0;i<ipt.length;i++){
	                   if(ipt.eq(i).prop("checked")==true){
	                       ipt.eq(i).parent().remove();
	                   }
	               }
	           })
	       }else{
	           $("input[name='ch1']").prop("checked",false);
	       }
	   })
		
		//绑定单选按钮
		$('.checkone').click(queryById);
		//绑定单个删除按钮
		$('.delete_yes').click(remove);
		//绑定修改数量按钮
		$('.add').click(updateNumber);
		$('.reduce').click(updateNumber);
//		
		  }});//外
	
}

//单选购物车中商品
function queryById(a){
	var id = "";
	if(a != undefined){
		id = $(a).attr("cartid");
	}
	else{
		id = $(this).attr("cartid");
	}
	url = "http://localhost:8080/wines/cart/findById/"+id;
	$.ajax({
		url: url,
		dataType: "json",
		async:false,
		success: function(result){
		var cart = result.data;
//		console.log(cart);
		//更新数据模型
		model.currentGoods = cart; //当前选中的商品
		console.log(model.currentGoods);
	}
});
}
//单个删除
function remove(){
	var goodsId = model.currentGoods.goodsId;
	url = "http://localhost:8080/wines/cart/delete/"+model.goodsId;
	$.getJSON(url,function(result){
		var message = result.message;
		console.log(message);
	});
}

//删除用户购物车中所有商品
function batchDelete(){
	//找到所有商品
	url = "http://localhost:8080/wines/cart/queryGoodsByUserId";
	$.getJSON(url,function(result){
		var list = result.data;
		for(var i=0;i<list.length;i++){
			var goodsId = list[i].goodsId;
			//删除
			url = "http://localhost:8080/wines/cart/delete/"+goodsId;
			$.getJSON(url,function(result){
				var message = result.message;
				console.log(message);
			});
		}
		
	});
}

//修改商品数量
function updateNumber(){
	queryById($(this));
	var goodsId = model.currentGoods.goodsId;
	console.log(goodsId);
	var number = parseInt($('.number').html());
	console.log(number);
	url = "http://localhost:8080/wines/cart/updateNum/"+goodsId+"/"+number;
	$.getJSON(url,function(result){
		var cart = result.data;
		console.log(cart);
		price = cart.price;
	});
}

//保存单个商品到订单表中，并将数据网址传参
function addOrder(){
	var data= model.currentGoods;
	console.log(data);
	var order = {
		"userId":data.userId,
		"goodsId":data.goodsId,
		"number":data.number,
		"price":data.price,
		"type":1,
		"payType":1,
		"payUserId":data.userId
	};
	console.log(order);
	$.ajax({
		url:"http://localhost:8080/wines/order/saveOrder",
		type:"post",
		data:JSON.stringify(order),
		contentType:"application/json",
		dataType:"json",
		async:false,
		success:function(result){
			if(result.success==true){
				var newOrder = result.data;
				console.log(newOrder);
				var orderId = newOrder.id;
				window.location.href=
					"http://localhost:8080/wines/html/08tijiaodingdan.html?arge1="+order.price+"&arge2="+order.number+"&arge3="+order.goodsId+"&arge4="+orderId;
			}
			removeCart(newOrder.goodsId);
		}
	})
}

//订单提交成功后，从购物车中移除商品
function removeCart(goodsId){
	$.ajax({
		url:"http://localhost:8080/wines/cart/delete/"+goodsId,
		type:"post",
		dataType:"json",
		contentType:"application/json",
		success:function(result){
			var message = result.message;
			console.log(message);
		}
	});
	
}








