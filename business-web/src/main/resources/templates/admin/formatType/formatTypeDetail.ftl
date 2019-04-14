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
            <label class="control-label">操作：</label>
            <a href="${base}/admin/formatType/addFormatTypeDetail?formatTypeId=${bean.id}" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="formatType-add" data-options="{title:'添加规格值', height:350}">添加规格值 </a>
        </div>
    </div>
    <br/>
    <br/>
    <div class="form-group">
        <div class="col-lg-6">
            <label class="control-label">规格值：</label>
        </div>
        <div class="col-lg-6" align="left">
            <table class="table table-bordered" data-toggle="datagrid" data-options="{
                width: '90%',
                height: '100%',
                gridTitle : '规格值',
                filterThead:false,
                local: 'remote',
                dataUrl: '${base}/admin/formatType/getFormatTypeDetail?id=${bean.id!}',
                paging: {pageSize:20, pageCurrent:1}}">
                <thead>
                <tr>
                    <th data-options="{name:'id', width:50}">ID</th>
                    <th data-options="{name:'detailName',width:150}">规格值</th>
                    <#--<th data-options="{name:'markContent'}">标注内容</th>-->
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <br/>
</div>

