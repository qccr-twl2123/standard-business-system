<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>注册新员工</title>
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
            <p class="title-text">注册新员工</p>
        </div>
        <div class="employee-cont layui-form">
            <div>
                <div class="layui-input-inline">
                    <label class="layui-form-label">编号</label>
                    <input type="text" name="code" lay-verify="code" autocomplete="off" class="layui-input" id="code">
                </div>
                <div class="layui-input-inline">
                    <label class="layui-form-label">姓名</label>
                    <input type="text" name="name" lay-verify="name" autocomplete="off" class="layui-input" id="name">
                </div>
                <div class="layui-input-inline two">
                    <label class="layui-form-label">部门</label>
                    <div class="layui-form">
                        <div class="layui-input-inline small-select">
                            <select id="selDepartmentFirst" lay-filter="selDepartmentFirst" lay-verify="selDepartmentFirst">
                                <option value="0">请选择</option>
                            </select>
                        </div>
                        <div class="layui-input-inline small-select">
                            <select id="selDepartmentSecond" lay-filter="selDepartmentSecond" lay-verify="selDepartmentSecond">
                                <option value="0">请选择</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form item">
                <div class="layui-input-inline">
                    <label class="layui-form-label float-none">权限：</label>
                    <input type="checkbox" name="all" lay-skin="primary" lay-filter="all" class="all" checked lay-verify="all">
                </div>
                <div id="doorlist"></div>
            </div>
            <div class="reg-cont item">
                <label class="layui-form-label">注册照</label>
                <div class="employee-img-upload">
                    <div class="img">
                        <img src="/style/images/visitor.jpg" id="visitor-img" lay-verify="visitorImg">
                    </div>
                    <button type="button" class="layui-btn" id="visitor-img-btn">请上传图片</button>
                    <input class="layui-upload-file" type="file" accept="undefined" name="file" multiple="">
                </div>
            </div>
            <div class="submit-cont">
                <button class="layui-btn cancel">取消</button>
                <button class="layui-btn save" lay-submit lay-filter="visitor-add">保存</button>
            </div>
        </div>
    </div>
</div>
</body>

</html>
<script type="text/javascript" src="/layui/laydate/laydate.js"></script>
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/style/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/style/js/ajaxData.js"></script>
<script type="text/javascript" src="/style/js/personadd.js"></script>