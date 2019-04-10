var layer,table;

$(function(){
	layui.use(['layer','table'], function(){
		layer = layui.layer; 
		table = layui.table; 
		//获取管理员列表
		table.render({
			elem: '#adminList'
			,height: 400
			,method: 'post'
			,url: local+'/manage/adminlist' //数据接口
			,page: true //开启分页
			,request: {
				pageName: 'page' //页码的参数名称，默认：page
				,limitName: 'pageSize' //每页数据量的参数名，默认：limit
			}
			,response: {
				statusName: 'errorCode' //数据状态的字段名称，默认：code
				,statusCode: 200 //成功的状态码，默认：0
				,msgName: 'errorMsg' //状态信息的字段名称，默认：msg
				,countName: 'rowCount' //数据总数的字段名称，默认：count
				,dataName: 'data' //数据列表的字段名称，默认：data
			}
			,cols: [[ //表头
				{field: 'personID', title: 'ID'}
				,{field: 'personName', title: '姓名'}
			  	,{field: 'personSerial', title: '编号'}
			  	,{field: 'adminType', title: '角色',templet:function(data){
			  		if(data.adminType){
			  			return data.adminType == 1?'管理员':'超级管理员'
			  		}
			  		 
			  	}}
			 	,{field: 'experience', title: '操作',templet:function(data){
			 		return "<span class='admin-cancel' lay-event='admin-cancel'>取消管理员</span><span class='admin-update' lay-event='admin-update'>修改管理员密码</span>";
			 	}}
			]]
		});
		table.on('tool(adminList)', function(obj){
		    var data = obj.data;
		    if(obj.event === 'admin-update'){
		      	$('.admin-cont').show();
				$('.admin-cont .name').text(data.personName);
				$('.admin-cont .name').attr('data-id',data.personID);
		    } else if(obj.event === 'admin-cancel'){
		      	$.post(local+'/manage/changeadminstatus',{personid:data.personID,adminstatus:0},function(data){
					if(data.errorCode === 200){
						layer.msg('取消管理员成功', {
						  	offset: 'c',
						  	anim: 6
						});
						table.reload('adminList', {
							url: local+'/manage/adminlist'
							,where: {page:1,pageSize:10} //设定异步数据接口的额外参数
						});
					}else{
						layer.msg(data.errorMsg, {
						  	offset: 'c',
						  	anim: 6
						});
					}
				});
		    } 
		});

	})
});

//搜索
$('.search').click(function(){
	$.post(local+'/manage/personlist',{personname:$('#search-name').val(),page:1,pageSize:10,departmentfirst: 0,departmentsecond: 0,isvalid:1},function(data){
		if(data.errorCode === 200){
			$('.employee-list').empty();
			var arr =[];
			for(var i = 0;i < data.data.length;i++){
				data.data[i].adminType = data.data[i].adminType == 0?'普通员工':'管理员'
				arr.push('<div class="employee-item" data-id="'+data.data[i].personID+'"><span>姓名</span><div class="layui-input-inline"><input type="text" name="personName" lay-verify="name" autocomplete="off" class="layui-input" value="'+data.data[i].personName+'"></div><span>编号</span><div class="layui-input-inline"><input type="text" name="personSerial" lay-verify="name" autocomplete="off" class="layui-input" value="'+data.data[i].personSerial+'"></div><span>角色</span><div class="layui-input-inline"><input type="text" name="personSerial" lay-verify="name" autocomplete="off" class="layui-input" value="'+data.data[i].adminType+'"></div><button class="layui-btn for-admin">设为管理员</button></div>');
			}
			$('.employee-list').append(arr);
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	});
})
//关闭弹框
$('.title-cont span').click(function(){
	$(this).parents('.child-page-cont').hide();
});

//修改个人密码
$('.personal-update').click(function(){
	$('.personal-cont').show();
	$('.personal-cont .name').text('xxxx');
});
$('.personal-btn').click(function(){
	$.post(local+'/manage/updatepass',{oldpass:$('.oldPassword').val(),newpassword:$('.newPassword').val(),personid:1},function(data){
		if(data.errorCode === 200){
			layer.msg('修改成功', {
			  	offset: 'c',
			  	anim: 6
			},function(){
				$('.personal-cont').hide();
			});
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	});
});
//修改子管理员密码
$('.admin-btn').click(function(){
	$.post(local+'/manage/resetpass',{newpassword:$('.password').val(),personid:$('.admin-cont .name').attr('data-id')},function(data){
		if(data.errorCode === 200){
			layer.msg('修改成功', {
			  	offset: 'c',
			  	anim: 6
			},function(){
				$('.admin-cont').hide();
			});
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	});
});
//取消管理员
$('#adminList').on('click','.admin-cancel',function(){
	$.post(local+'/manage/changeadminstatus',{personid:$(this).parents('.admin-item').attr('data-id'),adminstatus:0},function(data){
		if(data.errorCode === 200){
			layer.msg('取消管理员成功', {
			  	offset: 'c',
			  	anim: 6
			});
			table.reload('adminList', {
				url: local+'/manage/adminlist'
				,where: {page:1,pageSize:10} //设定异步数据接口的额外参数
			});
		}else{
			layer.msg(data.errorMsg, {
			  	offset: 'c',
			  	anim: 6
			});
		}
	});
});