layui.use('form', function(){
	var form = layui.form;
});
function doorlist(){
	//获取门列表
	$.post('/admin/doorInfo/list',{},function(data){
		if (data.statusCode === 200) {
			var str = [];
			for(var i = 0; i < data.list.length; i++){
				str.push('<div class="door-item" data-id="'+data.list[i].id+'"><div class="layui-input-inline"><input type="text" name="doorCode" autocomplete="off" class="layui-input" value="'+data.list[i].code+'" disabled></div><div class="layui-input-inline"><input type="text" name="doorName" autocomplete="off" class="layui-input" value="'+data.list[i].name+'"></div><button class="layui-btn entranceGuard-item-btn entranceGuard-item-btn-change">更新</button><button class="layui-btn entranceGuard-item-btn entranceGuard-item-btn-del">删除</button></div>');
			}
			$('.door-list').append(str);
		}}
	)
}
doorlist();
//增加门
$('.entranceGuard-item-btn-add').click(function(){
	var _this = this;		
	var doorCode = $.trim($('#addDoorCode').val());
	var doorName = $.trim($('#addDoorName').val());
	if(checkParam_str(doorCode,"门禁ID") > 0){
		return;
	}
	
	if(checkParam_str(doorName,"门禁描述") > 0){
		return;
	}
	
	$.post('/admin/doorInfo/save',
		{code: doorCode, name: doorName},
		function(data){
		if (data.statusCode === 200) {
			layer.msg('添加成功', {
			  	offset: 'c',
			  	anim: 6
			});
			$(".door-list").html("");
			doorlist();
			$('#addDoorCode').val("");
			$('#addDoorName').val("");
		}else{
			layer.msg(data.message, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	})
});
	//删除门
	$('.door-list').on('click','.entranceGuard-item-btn-del',function(){
		var _this = this;
		$.post('/admin/doorInfo/delete',{id:$(this).parent().attr('data-id')},function(data){
		if (data.statusCode === 200) {
			$(_this).parent().remove();
			layer.msg('删除成功', {
			  	offset: 'c',
			  	anim: 6
			});
			
		}else{
			layer.msg(data.message, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	});
	
	})
//修改门禁
$('.door-list').on('click','.entranceGuard-item-btn-change',function(){
	var _this = this;
	
	var doorName = $.trim($(this).parent().find('input[name=doorName]').val());
	
	if(checkParam_str(doorName,"门禁描述") > 0){
		return;
	}
	
	
	$.post('/admin/doorInfo/update',{id:$(this).parent().attr('data-id'),name:doorName,code:'默认值'},function(data){
		if (data.statusCode === 200) {
			layer.msg('修改成功', {
			  	offset: 'c',
			  	anim: 6
			});
			$(_this).parent().find('#doorCode').val(data.door_code);
			$(_this).parent().attr('data-id',data.door_id);
			$(_this).parent().find('#doorName').val(data.door_name);
		}else{
			layer.msg(data.message, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	})
})