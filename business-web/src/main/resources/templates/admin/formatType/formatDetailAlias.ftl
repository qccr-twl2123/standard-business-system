<div class="bjui-pageContent">
    <div class="form-group">
        <div class="col-lg-12">
            <label class="control-label">规格名称：</label> ${bean.name!}
        </div>
    </div>
    <br/>
    <br/>
    <div class="form-group">
        <div class="col-lg-12">
            <label class="control-label">规格值：${formatTypeDetailList?size}  </label>
                <select data-toggle="selectpicker" data-width="200" class="show-tick" style="display: none;">
                    <#--<#if formatTypeDetailList??>-->
                        <#--<#list formatTypeDetail as formatTypeDetailList>-->
                            <#--<option value="${formatTypeDetail.id!}">${formatTypeDetail.detailName!}</option>-->
                        <#--</#list>-->
                    <#--</#if>-->
                </select>
                <#--<#list model as formatTypeDetailList>-->
                    <#--<input type="radio" id="statusId${model.id!}" name="statusId" value="${model.detailName!}" data-toggle="icheck"  size="20"/>-->
                <#--</#list>-->
            <a href="${base}/admin/formatType/addFormatTypeDetail?formatTypeId=${bean.id}" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="formatType-add" data-options="{title:'添加规格值', height:350}">新增别名</a>
        </div>
    </div>
    <br/>
    <br/>
</div>

