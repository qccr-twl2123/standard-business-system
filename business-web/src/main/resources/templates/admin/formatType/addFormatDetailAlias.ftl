<#assign base=request.contextPath />
<div class="bjui-pageContent">
    <form action="${base}/admin/formatType/saveFormatDetailAlias" data-toggle="validate" method="post">
        <input type="hidden" name="formatTypeId" value="${bean.formatTypeId!}"/>
        <input type="hidden" name="formatTypeDetailId" value="${bean.formatTypeDetailId!}"/>
        <div class="form-group">
            <label class="control-label x85">规格值别名：</label>
            <input type="text" name="aliasName" value="" placeholder="规格值别名" size="20" data-rule="required"/>
        </div>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>
