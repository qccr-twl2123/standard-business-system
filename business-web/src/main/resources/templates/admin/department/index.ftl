<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>添加部门</title>
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
            <p class="title-text">公司信息配置·添加部门</p>
        </div>
        <div class="system-cont">
            <div>
                <div class="text-item">
                    <label class="layui-form-label company-structure">公司结构</label>
                    <span class="add-department-btn level-one add-btn-one">一级部门+</span>
                    <div class="department-list"></div>

                </div>
            </div>


        </div>
    </div>
</div>
<div class="child-page-cont">
    <div class="child-page">
        <div class="title-cont">
            <p>添加部门</p>
            <span>×</span>
        </div>
        <div class="input-cont">
            <div class="layui-form-item department-one-text">
                <label class="layui-form-label">一级部门</label>
                <input type="text" class="layui-input">
            </div>
            <div class="layui-form-item department-two-text">
                <label class="layui-form-label">二级部门</label>
            </div>
            <div class="layui-form-item department-two-input">
                <input type="text" class="layui-input">
            </div>
            <div class="add-btn-cont">
                <button class="layui-btn add-child-department-one">确认</button>
                <button class="layui-btn add-child-department-two">确认</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="/layui/laydate/laydate.js"></script>
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/style/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/style/js/common.js"></script>
<script type="text/javascript" src="/style/js/ajaxData.js"></script>
<script type="text/javascript" src="/style/js/department.js"></script>
