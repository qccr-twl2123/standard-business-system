<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>门禁管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="/layui/laydate/theme/default/laydate.css"/>
    <link rel="stylesheet" href="/style/css/global.css" media="all">
    <link rel="stylesheet" href="/style/css/system.css" media="all">
</head>

<body>
<div class="wrap">
    <div>
        <div class="title-cont">
            <p class="title-text">门禁管理</p>
        </div>
        <div class="system-cont">
            <div class="door-cont">
                <div>
                    <div class="layui-input-inline">
                        <label class="layui-form-label">门禁ID</label>
                    </div>
                    <div class="layui-input-inline">
                        <label class="layui-form-label">描述</label>
                    </div>
                </div>
                <div class="door-list"></div>
                <div class="item">
                    <div class="layui-input-inline">
                        <input type="text" id="addDoorCode" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <input type="text" id="addDoorName" autocomplete="off" class="layui-input">
                    </div>
                    <button class="layui-btn entranceGuard-item-btn entranceGuard-item-btn-add">添加</button>
                </div>
            </div>
            <!--  	<div>
                    <p class="title">门禁权限</p>
                    <div class="layui-form">
                        <label class="layui-form-label">员工权限</label>
                        <input type="checkbox" name="" title="20_Door" lay-skin="primary">
                        <input type="checkbox" name="" title="21_Door" lay-skin="primary">
                        <input type="checkbox" name="" title="22_Door" lay-skin="primary">
                        <input type="checkbox" name="" title="23_Door" lay-skin="primary">
                    </div>
                </div> -->
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/style/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/style/js/common.js"></script>
<script type="text/javascript" src="/style/js/ajaxData.js"></script>
<script type="text/javascript" src="/style/js/doorinfo.js"></script>