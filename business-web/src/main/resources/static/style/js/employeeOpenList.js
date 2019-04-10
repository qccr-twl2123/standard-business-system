layui.use('table', function () {
    var table = layui.table;
    table.render({
        elem: '#personOpenList',
        height: 600,
        method: 'post',
        page: false //开启分页
            ,
        response: {
            statusName: 'errorCode' //数据状态的字段名称，默认：code
            ,statusCode: 200 //成功的状态码，默认：0
            ,msgName: 'errorMsg' //状态信息的字段名称，默认：msg
            ,countName: 'rowCount' 
            ,dataName: 'data' //数据列表的字段名称，默认：data
        },
        cols: [
            [ //表头
                {
                    field: 'doorName',
                    title: '门禁ID'
                }, {
                    field: 'openTime',
                    title: '刷脸时间'
                }
            ]
        ]
    });
});
function loadData() {
    var table = layui.table;
    table.reload('personOpenList', {
        url: local + '/manage/opendoorlist',
        where: {
            personid: $("#hidPersonID").val(),
            opentime: $("#txtOpenTime").val()
        } //设定异步数据接口的额外参数
    });
}

$(document).ready(function () {
	layui.use('laydate', function () {
		var laydate = layui.laydate;
		laydate.render({
			elem: '#txtOpenTime',
			theme: '#09192a'
			,istoday: true
			,done: function(value, date, endDate){
				loadData();
		  }
		});
	});

    var personID = getUrlParam('personid');
    if (personID != null && personID != "") {
        $("#hidPersonID").val(personID);
    }

    //获取开门列表
    $.post(local + '/manage/personinfo', {
        personid: $("#hidPersonID").val()
    }, function (data) {
        if (data.errorCode === 200) {
            $("#spanOpenName").text(data.personName);
            $("#spanOpenSerial").text(data.personSerial);
            $("#spanOpenDepartment").text(data.departmentFirst + " | " + data.departmentSecond);
        }
    });
    // loadData();
});