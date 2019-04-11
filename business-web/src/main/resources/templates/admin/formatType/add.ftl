<#assign base=request.contextPath />
<div class="bjui-pageContent">
    <form action="${base}/admin/formatType/save" data-toggle="validate" method="post">
        <div class="form-group">
            <label class="control-label x85">规格名称：</label>
            <input type="text" name="name" value="" placeholder="规格名称" size="20" data-rule="required">
        </div>
        <div class="form-group">
            <label for="userType" class="control-label x85">图片模式：</label>
            <input type="radio" name="showImg"  value="1" data-toggle="icheck"  data-label="是" size="20"/>
            <input type="radio" name="showImg"  value="0" data-toggle="icheck"  data-label="否"  size="20"/>
        </div>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>



