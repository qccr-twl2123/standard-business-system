<div class="bjui-pageContent">
    <div class="form-group">
        <div class="col-lg-12">
            <label class="control-label">规格名称：</label> ${bean.name}
        </div>
    </div>
    <br/>
    <br/>
    <div class="form-group">
        <div class="col-lg-12">
            <label class="control-label">操作${bean.id!}：</label>
            <a href="${base}/admin/formatType/addFormatTypeDetail?formatTypeId=${bean.id}" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="formatType-add" data-options="{title:'添加规格值', height:350}">添加规格值 </a>
        </div>
    </div>
    <br/>
    <br/>
</div>

