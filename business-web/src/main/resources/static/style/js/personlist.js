var isValid = -1;
var table;
layui.use('table', function(){
	table = layui.table;
	table.render({
		elem: '#personList'
		,height: 600
		,method: 'post'
		,url: '/admin/personInfo/queryCondition' //数据接口
		,page: true //开启分页
		,request: {
			pageName: 'page' //页码的参数名称，默认：page
			,limitName: 'pageSize' //每页数据量的参数名，默认：limit
		}
		,where: {name:'',departmentFirst:0,departmentSecond:0,isValid:isValid} //设定异步数据接口的额外参数
		,response: {
			statusName: 'statusCode' //数据状态的字段名称，默认：code
			,statusCode: 200 //成功的状态码，默认：0
			,msgName: 'message' //状态信息的字段名称，默认：msg
			,countName: 'rowCount' //数据总数的字段名称，默认：count
			,dataName: 'data' //数据列表的字段名称，默认：data
		}
		,cols: [[ //表头
			{field: 'personSerial', title: '编号'}
			,{field: 'personName', title: '姓名',templet: function(data){
				return "<a href='employeeInformation.html?personid="+data.personID+"' class='name-link'>"+data.personName+"</a>";
			}}
			,{field: 'departmentFirst', title: '一级部门'} 
			,{field: 'departmentSecond', title: '二级部门'} 
			,{field: 'addTime', title: '注册时间'}
			,{field: 'valid', title: '状态',templet: function(data){
				return (data.valid == 1?"启用":"禁用")
			}}
		]]
	});
});

//搜索
$('.entranceGuard-item-btn-add').click(function(){
	loadData(isValid);
});
	
function loadData(isValid){
	var personName = $.trim($("#txtPersonName").val());
	var departmentFirst = parseInt($("#selDepartmentFirst").val(),10);
	var departmentSecond = parseInt($("#selDepartmentSecond").val(),10);
	table.reload('personList', {
		url: '/admin/personInfo/queryByCondition'
		,where: {personname:personName,departmentfirst:departmentFirst,departmentsecond:departmentSecond,isvalid:isValid} //设定异步数据接口的额外参数
	});
}
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

	//和一级联动，并增加一级下面的二级部门
	layui.use(['layer', 'form'], function(){
		var layer = layui.layer	,form = layui.form;
		
		form.on('submit(*)', function(data){
			return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});
		
		form.on('select(selDepartmentFirst1)', function(data){
			if(parseInt(data.value,10) < 1){
				$("#selDepartmentSecond").html("");
				$("#selDepartmentSecond").append("<option value='0'>全部</option>");
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
						$("#selDepartmentSecond").append("<option value='0'>全部</option>");
						form.render('select');
						 if(data.statusCode === 200){
								var str = [];
								for(var i = 0;i < data.list.length;i++){
									$("#selDepartmentSecond").append("<option value='"+data.list[i].id+"'>"+data.list[i].name+"</option>");
									form.render('select');
								}
						 }
						$("#selDepartmentSecond").get(0).selectedIndex=0;
				 }
			}); 
		});
		
	});
});
$('.attendance-type span').click(function(){
	$(this).addClass('active').siblings('span').removeClass('active');
	switch(+$(this).attr('data-type'))
	{
	case -1:
	  isValid = -1;
	  break;
	case 1:
	  isValid = 1;
	  break;
	default:
	  isValid = 0;
	}
	
})