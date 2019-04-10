var personid =  getUrlParam('personid');
var code = [];
var valid ,doorLen;
$(function(){
	//获取用户信息
	$.ajax({
		dataType:'json',
		type:'post',
		url:local+'/manage/personinfo',
		async: false,
		data:{
			personid:personid
		},
		success:function(data){
			if(data.errorCode === 200){
				$('#txtPersonCode').val(data.personSerial);
				$('#txtPersonName').val(data.personName);
				$('#state input[type=radio]').each(function(){
					if($(this).val() == data.valid){
						$(this).attr('checked','checked')
					}
				})
				valid = data.valid;
				code = data.permission.split(',')
				var main = '<div class="reg-img-item main"><div class="reg-img-cont" id="main-img"><img src="'+data.showurl+'"></div><div class="btn-cont"></div></div>';
				var invalid = '';
				var secondary = '';
				for(var i = 0; i < data.faceList.length;i++){
					if(data.faceList[i].valid == 1 && data.faceList[i].imgUrl != data.showurl && data.faceList[i].mirror == 0){
						secondary += '<div class="reg-img-item secondary" data-faceid="'+data.faceList[i].faceID+'"><div class="reg-img-cont"><img src="'+data.faceList[i].imgUrl+'"></div><div class="btn-cont"><button class="layui-btn default-btn left to-main">设为主照</button><button class="layui-btn default-btn middle to-prohibit">禁用</button><button class="layui-btn default-btn right to-del">删除</button></div></div>';
					}else if(data.faceList[i].valid == 0 && data.faceList[i].imgUrl != data.showurl && data.faceList[i].mirror == 0){
						invalid += '<div class="reg-img-item invalid" data-faceid="'+data.faceList[i].faceID+'"><div class="reg-img-cont"><img src="'+data.faceList[i].imgUrl+'"></div><div class="btn-cont"><button class="layui-btn default-btn left to-main">设为主照</button><button class="layui-btn default-btn middle to-active">激活</button><button class="layui-btn default-btn right to-del">删除</button></div><div class="invalid-active"><p>已失效</p></div></div>';
					}
				}
				$('.reg-img-list').prepend(main+secondary+invalid);
			}
			defaultDepartmentFirstList(data.departmentFirstID);
			defaultDepartmentSecondList(data.departmentFirstID,data.departmentSecondID);
			defaultDoorList(data.permission);
			
		}
	})
	
	layui.use(['layer', 'form','upload'], function(){
		var layer = layui.layer	,$ = layui.jquery
		,upload = layui.upload
		,form = layui.form;
		//和一级联动，并增加一级下面的二级部门
		form.on('select(selDepartmentFirst)', function(dataSelDepartmentFirst){
			if(parseInt(dataSelDepartmentFirst.value,10) < 1){
				$("#selDepartmentSecond").html("");
				$("#selDepartmentSecond").append("<option value='0'>全部</option>");
				form.render('select');
				return;
			}
			$.ajax({
				 type: 'POST',
				 url: local+'/manage/departmentlist',
				 data: {parentid:dataSelDepartmentFirst.value},
				 dataType:  'json',
				 success: function(dataDepartmentSecond){
						$("#selDepartmentSecond").html("");
						$("#selDepartmentSecond").append("<option value='0'>全部</option>");
						form.render('select');
						if(dataDepartmentSecond.errorCode === 200){
							for(var i = 0;i < dataDepartmentSecond.data.length;i++){
								$("#selDepartmentSecond").append("<option value='"+dataDepartmentSecond.data[i].departmentID+"'>"+dataDepartmentSecond.data[i].departmentName+"</option>");
								form.render('select');
							}
						}
				 }
			}); 
		});
		//权限选择
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
	});
})
//默认加载第一级部门
function defaultDepartmentFirstList(departmentFirstID){
	$.ajax({
		dataType:'json',
		type:'post',
		url:local+'/manage/departmentlist',
		async: false,
		data:{
			parentid:0
		},
		success:function(dataDepartmentFirst){
			if(dataDepartmentFirst.errorCode === 200){
				var str = [];
				for(var i = 0;i < dataDepartmentFirst.data.length;i++){
					if(dataDepartmentFirst.data[i].departmentID === departmentFirstID){
						$("#selDepartmentFirst").append("<option value='"+dataDepartmentFirst.data[i].departmentID+"' selected>"+dataDepartmentFirst.data[i].departmentName+"</option>");
					}else{
						$("#selDepartmentFirst").append("<option value='"+dataDepartmentFirst.data[i].departmentID+"'>"+dataDepartmentFirst.data[i].departmentName+"</option>");
					}
				}
			}
		}
	})
}
//默认加载第二级部门
function defaultDepartmentSecondList(departmentFirstID,departmentSecondID){
	$.ajax({
		dataType:'json',
		type:'post',
		url:local+'/manage/departmentlist',
		async: false,
		data:{
			parentid:departmentFirstID
		},
		success:function(dataDepartmentSecond){
			if(dataDepartmentSecond.errorCode === 200){
				for(var i = 0;i < dataDepartmentSecond.data.length;i++){
					if(dataDepartmentSecond.data[i].departmentID === departmentSecondID){
						$("#selDepartmentSecond").append("<option value='"+dataDepartmentSecond.data[i].departmentID+"' selected>"+dataDepartmentSecond.data[i].departmentName+"</option>");
					}else{
						$("#selDepartmentSecond").append("<option value='"+dataDepartmentSecond.data[i].departmentID+"'>"+dataDepartmentSecond.data[i].departmentName+"</option>");
					}
				}
			}
		}
	})
}
//获取门列表
function defaultDoorList(permission){
	$.ajax({
		dataType:'json',
		type:'post',
		url:local+'/manage/doorlist',
		async: false,
		data:{
		},
		success:function(dataDoor){
			if(dataDoor.errorCode === 200){
				var str = permission.split(',');
				for(var i = 0;i < dataDoor.data.length;i++){
					if(str.indexOf(dataDoor.data[i].doorCode) != -1){
						$('#doorlist').append('<input type="checkbox" data-code="'+dataDoor.data[i].doorCode+'" title="'+dataDoor.data[i].doorName+'" lay-skin="primary" lay-filter="door" checked>');
					}else{
						$('#doorlist').append('<input type="checkbox" data-code="'+dataDoor.data[i].doorCode+'" title="'+dataDoor.data[i].doorName+'" lay-skin="primary" lay-filter="door">');
					}
				}
				if(dataDoor.data.length == str.length){
					$('.all').attr('checked',true);
				}
			}
			doorLen = dataDoor.data.length
		}
	})
}
//修改员工信息
$('.save').click(function(){
	var msg = {
		personid:personid,
		personserial:$('#txtPersonCode').val(),
		name:$('#txtPersonName').val(),
		doors:code.join(','),
		departmentfirst:$('#selDepartmentFirst').val(),
		departmentsecond:$('#selDepartmentSecond').val(),
		valid:$('#state input[type=radio]:checked').val()
	};
	$.post(local+'/manage/personinfo/update',msg,function(data){
		if(data.errorCode === 200){
			layer.msg('修改成功', {
			  	offset: 'c',
			  	anim: 6
			});
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	})
})
//删除员工
$('.removeEmployeeInfo').click(function(){
	$.post(local+'/manage/personinfo/delete',{personid:personid},function(data){
		if(data.errorCode === 200){
			layer.msg('删除成功', {
			  	offset: 'c',
			  	anim: 6
			});
			history.back();
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	})
})
//删除照片
$('.reg-img-list').on('click','.to-del',function(){
	var _this = this;
	$.post(local+'/manage/deleteimage',{faceid:$(this).parents('.reg-img-item').attr('data-faceid')},function(data){
		if(data.errorCode === 200){
			$(_this).parents('.reg-img-item').remove();
			layer.msg('删除成功', {
			  	offset: 'c',
			  	anim: 6
			});
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	})
})
//禁用照片
$('.reg-img-list').on('click','.to-prohibit',function(){
	var _this = this;
	$.post(local+'/manage/face/change',{faceid:$(this).parents('.reg-img-item').attr('data-faceid'),valid:0},function(data){
		if(data.errorCode === 200){
			layer.msg('禁用成功', {
			  	offset: 'c',
			  	anim: 6
			});
			location.reload();
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	})
})
//激活照片
$('.reg-img-list').on('click','.to-active',function(){
	var _this = this;
	$.post(local+'/manage/face/change',{faceid:$(this).parents('.reg-img-item').attr('data-faceid'),valid:1},function(data){
		if(data.errorCode === 200){
			layer.msg('激活成功', {
			  	offset: 'c',
			  	anim: 6
			});
			location.reload();
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	})
})
//设为主照
$('.reg-img-list').on('click','.to-main',function(){
	var _this = this;
	$.post(local+'/manage/setmainimage',{faceid:$(this).parents('.reg-img-item').attr('data-faceid')},function(data){
		if(data.errorCode === 200){
			/*$(_this).parent().empty();
			$(_this).parents('.reg-img-item').siblings('.main').find('.btn-cont').html('<button class="layui-btn default-btn left to-main">设为主照</button><button class="layui-btn default-btn right to-del">删除</button>')
			$(_this).parents('.reg-img-item').siblings().removeClass('.main');
			$(_this).parents('.reg-img-item').removeClass('.secondary').addClass('.main');*/
			location.reload();
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	})
})
//取消
$('.cancel').click(function(){
	history.back();
})
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
//追加注册照
$("#visitor-img-btn").click(function(){
	$("#visitor-img-btn").next().trigger('click')
})
$("#visitor-img-btn").next().change(function () {
	var file = this.files[0];
	readFile(file);
	$('#visitor-img').load(function(){
		$.post(local+'/manage/register/append',{personid:personid,image64:$('#visitor-img').attr('src')},function(data){
			if(data.errorCode === 200){
				layer.msg('成功', {
				  	offset: 'c',
				  	anim: 6
				});
				location.reload();
			}else{
				layer.msg(data.errorMsg, {
				  	offset: 'c',
				  	anim: 6
				});
			}
		})
	})
	
});
//门禁记录跳转
$('.doorOpenList').click(function(){
	location.href = 'employeeOpenList.html?personid='+personid;
});