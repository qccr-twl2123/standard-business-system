var departmentID,departmentName;
layui.use('laydate', function(){
		var laydate = layui.laydate;
		laydate.render({
	    elem: '#startTime'
	    ,theme: '#09192a'
	});
	laydate.render({
	    elem: '#endTime'
	    ,theme: '#09192a'
	});
})
	
	
layui.use('form', function(){
	var form = layui.form;

});
function addDepartment(departmentID,departmentName){
	$('.child-page-cont').show();
	if(departmentID != 0){
		$('.add-child-department-one').hide();
		$('.department-two-text').show();
		$('.department-two-input').show();
		$('.add-child-department-two').show();
		$('.department-one-text').find('input').val(departmentName).attr('disabled','disabled');
		$.post('/admin/department/list',{parentId:departmentID,status:1},function(data){
			if(data.statusCode === 200){
				var str = [];
				for(var i = 0;i < data.list.length;i++){
					var input = $('<input type="text" class="layui-input" >');
					input.val(data.list[i].name);
					str.push(input);
				}
				$('.department-two-text').append(str);
			}
		})
	}else{
		$('.add-child-department-one').show();
		$('.department-two-text').hide();
		$('.department-two-input').hide();
		$('.add-child-department-two').hide();
	}
}
//添加一级部门按钮
$('.add-btn-one').click(function(){
	addDepartment(0,0);
	departmentID=0,departmentName=0;
});
//添加二级部门按钮
$('.department-list').on('click','.add-btn-two',function(){
	departmentID = $(this).parents('.department-item').find('.department-one').attr('data-id');
	departmentName = $(this).parents('.department-item').find('.department-one').text();
	addDepartment(departmentID,departmentName);
});
$(function(){
	$.post('/admin/department/list',{parentId:0,status:1},function(data){
		var str = [];
		if(data.statusCode === 200){
			for(var i = 0;i < data.list.length;i++){
				var div = '<div class="department-item"><div><div class="layui-input-inline department-one-width"><span class="level-one department-one" data-id="'+data.list[i].id+'">'+data.list[i].name+'</span><span class="update-department-one"><i class="layui-icon layui-icon-edit"></i></span><span class="del-department-one"><i class="layui-icon layui-icon-close"></i> </span></div></div><div class="department-item-two"><span class="add-department-btn add-btn-two">二级部门+</span></div></div>';
				str.push(div);
			}
			$('.department-list').append(str);	
			$('.department-one').each(function(){
				var _this = this;
				$.post('/admin/department/list',{parentId:$(this).attr('data-id'),status:1},function(data){
					var str = [];
					if(data.statusCode === 200){
						for(var i = 0;i < data.list.length;i++){
							var div = '<div class="layui-input-inline department-width"><span class="department-two" data-id="'+data.list[i].id+'">'+data.list[i].name+'</span><span class="update-department-two"><i class="layui-icon layui-icon-edit"></i></span><span class="del-department-two"><i class="layui-icon layui-icon-close"></i></span></div>';
							str.push(div);
						}
					}else{
						return
					}
					$(_this).parents('.department-item').find('.department-item-two').prepend(str);	
				})
			})
		}
	})
	
});

//弹框添加一级部门
$('.add-child-department-one').click(function(){
	add($('.department-one-text').find('input').val());
});
//弹框添加二级部门
$('.add-child-department-two').click(function(){
	add($('.department-two-input').find('input').val());
});
//添加部门
function add(argument) {
	if(argument.length == 0){
		layer.msg('部门不能为空', {
			icon: 5,
			anim: 6
		});
		return
	}
	$.post('/admin/department/save',{parentId:departmentID,name:argument,depth:(departmentID === 0?1:2)},function(data){
		if(data.statusCode === 200){
			$('.department-two-text').html('<label class="layui-form-label">二级部门</label>');
			$('.child-page-cont').hide(); 
			location.reload();
		}
	
	})
}
//关闭弹框
$('.child-page .title-cont span').click(function(){
	$('.department-two-text').html('<label class="layui-form-label">二级部门</label>');
	$('.child-page-cont').hide();
});
//删除二级部门
$('.department-list').on('click','.del-department-two',function(){
	var _this = this;
	$.post('/admin/department/delete',{id:+$(this).prev().prev().attr('data-id')},function(data){
		if(data.statusCode === 200){
			$(_this).parents('.department-width').remove();
		}else{
			layer.msg(data.message, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	
	})
});
//删除部门
$('.department-list').on('click','.del-department-one',function(){
	var _this = this;
	$.post('/admin/department/delete',{id:+$(this).prev().prev().attr('data-id')},function(data){
		if(data.statusCode === 200){
			$(_this).parents('.department-item').remove();
		}else{
			layer.msg(data.message, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	
	})
});
//修改一级部门按钮
$('.department-list').on('click','.update-department-one',function(){
	var _this = this;
	$(this).prev('.department-one').hide();
	$(this).hide();
	$(this).next().hide();
	$(this).parents('.department-one-width').prepend('<input type="text" value="'+$(this).prev('.department-one').text()+'" /><span class="change-department-one"><i class="layui-icon layui-icon-ok"></i></span><span class="cancel-department-one"><i class="layui-icon layui-icon-return"></i></span>');
	
});
//修改一级部门
$('.department-list').on('click','.change-department-one',function(){
	var _this = this;
	$.post('/admin/department/update',{id:+$(this).siblings('.department-one').attr('data-id'),parentId:0,name:$(this).siblings('input').val()},function(data){
		if(data.statusCode === 200){
			var departmentID =  $(_this).prev().attr('data-id');
			$(_this).parents('.department-one-width').prepend('<input type="text" />');
			location.reload();
		}else{
			layer.msg(data.message, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	
	})
});
//取消修改一级部门
$('.department-list').on('click','.cancel-department-one',function(){
	$(this).siblings('.department-one').show();
	$(this).siblings('.update-department-one').show();
	$(this).siblings('.del-department-one').show();
	$(this).siblings('input').remove();
	$(this).siblings('.change-department-one').remove();
	$(this).remove();
});





//修改二级部门按钮
$('.department-list').on('click','.update-department-two',function(){
	var _this = this;
	$(this).prev('.department-two').hide();
	$(this).hide();
	$(this).next().hide();
	$(this).parents('.department-width').css('width','300');
	$(this).parents('.department-width').prepend('<input type="text" value="'+$(this).prev('.department-two').text()+'" /><span class="change-department-two"><i class="layui-icon layui-icon-ok"></i></span><span class="cancel-department-two"><i class="layui-icon layui-icon-return"></i></span>');
	
})
//修改二级部门
$('.department-list').on('click','.change-department-two',function(){
	var _this = this;
	$.post('/admin/department/update',{id:+$(this).siblings('.department-two').attr('data-id'),parentId:0,name:$(this).siblings('input').val()},function(data){
		if(data.statusCode === 200){
			var departmentID =  $(_this).prev().attr('data-id');
			$(_this).parents('.department-width').prepend('<input type="text" />');
			location.reload();
		}else{
			layer.msg(data.message, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	
	})
})
//取消修改二级部门
$('.department-list').on('click','.cancel-department-two',function(){
	$(this).parents('.department-width').css('width','150');
	$(this).siblings('.department-two').show();
	$(this).siblings('.update-department-two').show();
	$(this).siblings('.del-department-two').show();
	$(this).siblings('input').remove();
	$(this).siblings('.change-department-two').remove();
	$(this).remove();
})