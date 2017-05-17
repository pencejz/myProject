/**
 * 笔记编辑界面js脚本
 */
//var SUCCESS = 0;
//var ERROR = 1;
//创建对象model，其属性notebooks的值为空数组
var model = {
		notebooks:[], 		//笔记本列表
		currentNotebook:{}, //当前选点的笔记本
		notebookIndex:0,	//当前选定的笔记本索引号
		notes:[], 			//当前笔记本的笔记列表
		currentNote:{}, 	//当前正在编辑的笔记
		noteIndex:0			//当前选择的笔记索引号
};

//页面加载后执行
$(function(){
	//页面加载后，可以从cookie获取登陆用户的id
	var userId = getCookie("userId");
//	console.log(userId);
	//通过用户ID获取笔记本列表并显示
	listNotebooksAction(userId);
	//绑定保存笔记编辑保存按钮
	$('#save_note').click(saveNoteAction);
});

//保存编辑笔记
function saveNoteAction(){
	//获取当前网页笔记编辑区内容
	var noteBody = um.getContent();
	var noteTitle = $('#input_note_title').val();
	if(noteBody==model.currentNote.noteBody && noteTitle==model.currentNote.noteTitle){
		//如果笔记没有编辑，就不用向服务器发送ajax保存请求
		return;
	}
	//将笔记编辑区内容发送发服务器
	var data={noteId:model.currentNote.noteId,noteTitle:noteTitle,noteBody:noteBody};
	var url="note/updateNote.do";
	$('#save_note').html("保存中...").attr('disabled','disabled');
	$.post(url,data,function(result){
		if(result.state==SUCCESS){
//			console.log(result.data);
			$('#save_note').html("保存笔记").removeAttr('disabled');
			var note = result.data;
			model.currentNote = note;
			model.notes[model.noteIndex].noteTitle=note.noteTitle;
			paintNotes();
			paintCurrentNote();
		}else{
			alert(result.message);
		}
	});
	
}

//通过用户ID获取笔记本列表并显示
function listNotebooksAction(userId){
	//获取用户ID
	var data = {'userId':userId};
	//通过json对象发送并接收数据方式2：
	url = "notebook/list.do?userId="+userId;
	$.getJSON(url,function(result){
		if(result.state==SUCCESS){
			var list = result.data;
//			for(var i=0;i<list.length;i++){
//				console.log(list[i]);
//			}
			//更新数据模型
			model.notebooks=list;
			//刷新显示笔记本列表
			paintNotebooks();
		}else{
			alert(result.message);
		}
	});
	
}

/*
 * 将笔记本列表显示到页面上
 * <li class="online">
 * 		<a  class='checked'>  checked表示选定
 * 			<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>
 *  		默认笔记本
 *  	</a>
 *  </li>
 */
function paintNotebooks(){
	var list = model.notebooks;
	var ul=$('#notebooks');
	ul.empty(); //清空
	for(var i=0;i<list.length;i++){
		var notebook = list[i];
		var li = '<li class="online">'+
		         	'<a>'+
		         		'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+
		         		notebook.notebookName+
		         	'</a>'+
		         '</li>';
		/*
		 * 实现点击笔记本，显示笔记的功能
		 */
		li = $(li).data('index',i); //数据绑定：笔记本序号
		li.click(function(){ //事件绑定
			//删除所有的笔记本的选中效果
			$(this).parent().find('a').removeClass('checked');
			//添加选中的笔记本的选中效果
			$(this).children('a').addClass('checked');
			var index = $(this).data('index'); //取回绑定的数据
//			console.log(index);
			var notebook=model.notebooks[index];
			model.currentNotebook=notebook; //添加属性currentNotebook并赋值为notebook
			//通过笔记本ID找到笔记列表
			listCurrentNotesAction();
		});
		ul.append(li);//插入内容
	}
}

//浏览器的控制器方法：获取数据，更新模型(通过笔记本ID找到笔记列表)
function listCurrentNotesAction(){
	var notebookId= model.currentNotebook.notebookId;
//	console.log(notebookId);
	//发起异步请求，获取notes
	var url = "note/list.do?notebookId="+notebookId;
	$.getJSON(url,function(result){
		if(result.state==SUCCESS){
			var list = result.data;
			//异步请求成功后，更新模型
			model.notes = list;
			//将notes显示到页面
			paintNotes();
		}
	});
}

//将数据模型中的notes显示到页面
function paintNotes(){
	//获取notes模型
	var list = model.notes;
	var ul = $('#notes').empty();
	for(var i=0;i<list.length;i++){
		var note = list[i];
		//显示笔记列表
		var li =
			'<li class="online">'+
				'<a>'+
					'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+
					note.noteTitle +
					'<button disabled="disabled" type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'+
					'<i class="fa fa-chevron-down"></i></button>'+
				'</a>'+
				'<div class="note_menu" tabindex="-1">'+
					'<dl>'+
						'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
						'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
						'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
					'</dl>'+
				'</div>'+
			'</li>';
		li = $(li).data('index',i);
		ul.append(li);
		
		/*
		 * 绑定选中笔记的单击事件
		 * 设置点选笔记的显示效果，并且显示笔记内容和笔记标题
		 */
		li.children('a').click(function(){
			$(this).parent().parent().find('a').removeClass('checked');
			$(this).parent().parent().find('li>a>button').attr('disabled','disabled');
			$(this).find('button').removeAttr('disabled');
			$(this).parent().parent().find('.note_menu').slideUp(10);
			$(this).addClass('checked');
			var index = $(this).parent().data('index');
//			console.log(index);
			var note = model.notes[index];
			model.noteIndex = index;
			//通过笔记ID从服务器端获取笔记信息
			loadNoteAction(note.noteId);
		});
		
		/*
		 * 绑定每一个笔记的菜单事件
		 * 显示一个下拉选，包含3个功能：删除、移动、分享
		 */
		li.children('a').children('button').click(function(){
			$(this).parent().parent().children('.note_menu').slideToggle(200);
			return false;
		});
		
		
//		if(model.currentNote.noteId){
//			if(note.noteId==model.currentNote.noteId){
//				li.children('a').addClass('checked');
//			}
//		}
		
	}
	
}

//通过笔记ID从服务器端获取笔记信息
function loadNoteAction(noteId){
	var url="note/note.do?noteId="+noteId;
	$('#input_note_title').val("笔记加载中...");
	$.getJSON(url,function(result){
		if(result.state==SUCCESS){
			model.currentNote=result.data;
			paintCurrentNote();
		}else{
			alert(result.message);
		}
	});
}

//将当前笔记显示到笔记编辑区
function paintCurrentNote(){
	var note = model.currentNote;
	$('#input_note_title').val(note.noteTitle);
	um.setContent(note.noteBody);
}




