var imagetype;
var isLoadLogo = false;
var form,laydate,layer;
$(function(){
	layui.use(['form','laydate','layer'],function(){
		form = layui.form
		,laydate = layui.laydate
		,layer = layui.layer;
		laydate.render({
		    elem: '#startTime'
		    ,theme: '#09192a'
		    ,type: 'time'
		    ,format: 'HH:mm'
		});
		laydate.render({
		    elem: '#endTime'
		    ,theme: '#09192a'
		    ,type: 'time'
		    ,format: 'HH:mm'
		});
		//工作时间选择
		form.on('radio()', function(data){
			if(data.value == 3){
				$('#flex').show();
				$('#fixed').hide();
			}else{
				$('#flex').hide();
				$('#fixed').show();
			}
		});
		//默认公司信息
		$.post(local+'/manage/systemsetinfo',{},function(data){
			$('#companyName').val(data.companyName);
			$('#companyInfo-logo').attr('src',data.logoURL);
			if(data.checkType == 3){
				$('#flex').show();
				$('#flex-radio').attr('checked','checked');
				$('#startTime').val(data.startTime);
				$('#endTime').val(data.endTime);
			}else if(data.checkType == 4){
				$('#fixed').show();
				$('#fixed-radio').attr('checked','checked');
				$('#workTime').val(data.checkTimes);
			}
			form.render('radio');
		});
	})	
	
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
		imageType = file.name.toLocaleLowerCase().split(".")[1];
		isLoadLogo = true;
		switch(file.type)
		{
			case 'image/jpg':
			case 'image/png':
			case 'image/jpeg':
			case 'image/gif':
				var imgele = document.getElementById('companyInfo-logo');
				imgele.src = reader.result;
		}
	})
}
// 单张上传照片  删除照片
$("#logo-btn").click(function(){
	$("#logo-btn").next().next().trigger('click')
})
$("#logo-btn").next().next().change(function () {
	var file = this.files[0];
	readFile(file);
});
$('#companyInfo-update').click(function(){
	var data;
	var image64 = $('#companyInfo-logo').attr('src');
	var companyname = $('#companyName').val();
	var checktype = +$('input[type=radio]:checked').val();
	var checktimes = +$('#workTime').val();
	var checkstart = $('#startTime').val();
	var checkend = $('#endTime').val();
	data = checktype == 3 ? {companyname:companyname,checktype:checktype,checkstart:checkstart,checkend:checkend} : {companyname:companyname,checktype:checktype,checktimes:checktimes};
	if(isLoadLogo){
		data.image64 = image64;
		data.imagetype = imagetype;
	}
	$.post(local+'/manage/savesysteminfo',data,function(data){
		
	});
})