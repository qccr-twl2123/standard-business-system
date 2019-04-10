var code = [];
var doorLen;
$(function(){
	//默认加载第一级部门
	$.post('/admin/department/list',{parentId:0,status:1},function(data){
		if(data.statusCode === 200){
			var str = [];
			for(var i = 0;i < data.list.length;i++){
				$("#selDepartmentFirst").append("<option value='"+data.list[i].id+"'>"+data.list[i].name+"</option>");
			}
		}
	});
	//获取门列表
	$.post('/admin/doorInfo/list',{},function(data){
		if(data.statusCode === 200){
			var str = [];
			for(var i = 0;i < data.list.length;i++){
				$('#doorlist').append('<input type="checkbox" data-code="'+data.list[i].code+'" title="'+data.list[i].name+'" lay-skin="primary" lay-filter="door" checked>');
				code.push(data.list[i].code)
			}
		}
		doorLen = data.list.length;
	});
})

  
layui.use(['layer', 'form','upload'], function(){
	var layer = layui.layer	,form = layui.form, $ = layui.jquery
	,upload = layui.upload;
	//和一级联动，并增加一级下面的二级部门
	form.on('select(selDepartmentFirst)', function(data){
		if(parseInt(data.value,10) < 1){
			$("#selDepartmentSecond").html("");
			$("#selDepartmentSecond").append("<option value='0'>请选择</option>");
			form.render('select');
			return;
		}
		$.ajax({
			 type: 'POST',
			 url: '/admin/department/list',
			 data: {parentId:data.value,status:1},
			 dataType:  'json',
			 success: function(data){
					$("#selDepartmentSecond").html("");
					$("#selDepartmentSecond").append("<option value='0'>请选择</option>");
					form.render('select');
					 if(data.statusCode === 200){
						for(var i = 0;i < data.list.length;i++){
							$("#selDepartmentSecond").append("<option value='"+data.list[i].id+"'>"+data.list[i].name+"</option>");
							form.render('select');
						}
					 }
					$("#selDepartmentSecond").get(0).selectedIndex=0;
			 }
		}); 
	});
	form.on('checkbox(door)', function(data){
		if(data.elem.checked){
			code.push(data.elem.getAttribute('data-code'));
		}else{
			if(code.indexOf(data.elem.getAttribute('data-code')) != -1){
				code.splice(code.indexOf(data.elem.getAttribute('data-code')),1)
			}
		}
		if(code.length == doorLen){
			$('.all').prop('checked',true);
		}else{
			$('.all').prop('checked',false);
		}
		form.render('checkbox');
	});
	//全选/全不选
	form.on('checkbox(all)', function(data){
		if(data.elem.checked){
			$('#doorlist input[type=checkbox]').each(function(){
				if(code.indexOf($(this).attr('data-code')) == -1){
					$(this).prop('checked',true);
	  				code.push(this.getAttribute('data-code'));
				}
	  		})
		}else if(!data.elem.checked){
	  		$('#doorlist input[type=checkbox]').each(function(){
	  			$(this).prop('checked',false);
	  			code = [];
	  		})
		}
  		form.render('checkbox');
	});
	//自定义验证规则
	form.verify({
		name: function(value){
			var reg = /[`~!@#$%^&*()+<>?:"{},.\/;'[\]]/im;
		  	if(value.length == 0){
		    	return '姓名不能为空';
		  	}else if(reg.test(value)){
		  		return '姓名含有特殊字符'
		  	}else if(value.length > 20){
		    	return '姓名长度不能超过20';
		  	}
		},
		code: function(value){
		  	if(value.length == 0){
		    	return '编号不能为空';
		  	}else if(value.length > 20){
		    	return '编号长度不能超过20';
		  	}
		},
		visitorImg: function(){
			if($('#visitor-img').attr('src') == '../../images/visitor.jpg'){
				return '没有上传员工图片';
			}
		},
		selDepartmentFirst: function(){
			if($('#selDepartmentFirst').val() == 0){
				return '没有选择一级部门';
			}
		},
		selDepartmentSecond: function(){
			if($('#selDepartmentSecond').val() == 0){
				return '没有选择二级部门';
			}
		}
	});
	//提交
	form.on('submit(visitor-add)', function(data){
		$('#visitor-add').attr('disabled','disabled');
		var loading = layer.load(1);
		var msg = {
			image64:$('#visitor-img').attr('src'),
            code:$('#code').val(),
            name:$('#name').val(),
            doorRights:code.join(','),
			departmentfirst:$('#selDepartmentFirst').val(),
            departmentId:$('#selDepartmentSecond').val(),
			persontype:1
		};
		$.post('/admin/personInfo/save',msg,function(data){
			layer.close(loading);
			if(data.statusCode === 200){
				layer.msg('注册成功', {
				  	offset: 'c',
				  	anim: 6
				});
			}else{
				layer.msg(data.message, {
				  	offset: 'c',
				  	anim: 6
				});
			}
			$('#visitor-add').attr('disabled',false);
		})
		return false;
	});
});
// 需要把阅读的文件传进来file;element是把读取到的内容放入容器
function readFile(file)
{
	//新建阅读器
	var reader = new FileReader();
	// 根据文件类型选择阅读方式
	switch(file.type)
	{
		case 'image/jpg':
		case 'image/png':
		case 'image/jpeg':
		case 'image/gif':
			reader.readAsDataURL(file);
			break;
	}
	// 当文件阅读结束后执行的方法
	reader.addEventListener('load',function(){
		switch(file.type)
		{
			case 'image/jpg':
			case 'image/png':
			case 'image/jpeg':
			case 'image/gif':
				var imgele = document.getElementById('visitor-img');
				imgele.src = reader.result;
		}
	})
}
// 单张上传照片  删除照片
$("#visitor-img-btn").click(function(){
	$("#visitor-img-btn").next().trigger('click')
})
$("#visitor-img-btn").next().change(function () {
	var file = this.files[0];
	readFile(file);
});
