<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>员工列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="/layui/laydate/theme/default/laydate.css"/>
    <link rel="stylesheet" href="/style/css/global.css" media="all">
    <link rel="stylesheet" href="/style/css/employee.css" media="all">
</head>

<body>
<div class="wrap">
    <div>
        <div class="title-cont">
            <p class="title-text">员工列表</p>
        </div>
        <div class="employee-cont">
            <div>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="请输入姓名进行检索" id="txtPersonName">
                </div>
                <div class="layui-input-inline attendance-type">
                    <span data-type="-1" class="all active">全部状态</span><i>|</i><span data-type="1" class="normal">正常</span><span data-type="0" class="absence">删除</span>
                </div><form class="layui-form" id="personlist_form" style="display: inline;">
                <div class="layui-input-inline layui-form">
                    <select id="selDepartmentFirst" lay-filter="selDepartmentFirst1">
                        <option value="0">一级部门</option>
                    </select>
                </div>
                <div class="layui-input-inline layui-form">
                    <select id="selDepartmentSecond">
                        <option value="0">二级部门</option>
                    </select>
                </div></form><button class="layui-btn entranceGuard-item-btn entranceGuard-item-btn-add">搜索</button>
            </div>
            <table id="personList" lay-filter="personList">

            </table>
        </div>
    </div>
</div>

</body>

</html>
<script type="text/javascript" src="/layui/laydate/laydate.js"></script>
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/style/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/style/js/ajaxData.js"></script>
<script type="text/javascript" src="/style/js/personlist.js"></script>