<div class="bjui-pageContent">
    <div class="form-group">
        <div class="col-lg-12">
            <label class="control-label">规格名称: </label> ${bean.name!}
        </div>
    </div>
    <br/>
    <br/>
    <div class="form-group">
        <div class="col-lg-12">
            <label class="control-label">规格值:  </label>
            <select data-toggle="selectpicker" id="formatTypeDetailId" name="formatTypeDetailId" data-width="130" class="show-tick" style="display: none;">
                <#if formatTypeDetailList??>
                    <#list formatTypeDetailList as formatTypeDetail>
                        <option value="${formatTypeDetail.id!}">${formatTypeDetail.detailName!}</option>
                    </#list>
                </#if>
            </select>
            <#--<a href="${base}/admin/formatType/addFormatDetailAlias?formatTypeId=${bean.id}" onclick="addFormatDetailAlias()" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="formatType-add" data-options="{title:'新增别名', height:350}">新增别名</a>-->
        </div>
    </div>
    <br/>
    <br/>
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
            <th data-options="{name:'id',render:'global_operation',align:'center',quicksort:false,width:150}">操作</th>
        </tr>
        </thead>
    </table>
</div>
<script>
    function addFormatDetailAlias(formatTypeId) {
        var formatDetailAliasId = $("#formatDetailAliasId").val();
        alert(formatDetailAliasId);
        self.location.href="${base}/admin/formatType/addFormatDetailAlias?formatTypeId='"+formatTypeId+"'&formatDetailAliasId='"+formatDetailAliasId+"'";
    }

</script>

