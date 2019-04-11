<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${base}/admin/formatType/formatTypeDetail" method="post">
        <@pageHeadr />
        <div class="bjui-searchBar">
            <label>规格明细名称：</label>
            <input type="text" name="detailName" value="${bean.detailName!}" class="form-control" size="12">&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <br />
            <a href="${base}/admin/formatType/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="formatType-add" data-options="{title:'添加', height:350}">添加 </a>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
        <thead>
        <tr>
            <th width="30">序号</th>
            <th>规格明细名称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#if page??>
            <#list page.list as bean>
                <tr>
                    <td align="center">${bean_index+1}</td>
                    <td>${bean.detailName!}</td>
                    <td>${bean.email!}</td>
                    <td>
                        <a href="${base}/admin/formatType/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="formatType-delete" data-confirm-msg="确定要删除吗？">删除</a>
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
<@pageFooter />
