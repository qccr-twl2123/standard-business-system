<#include "/macro/base.ftl" />
<form id="pagerForm" method="post" action="${base}/admin/formatDetailAlias/list">
    <@pagerForm />
</form>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${base}/admin/formatDetailAlias/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>字段KEY：</label>
                    <input type="text" name="fieldKey" value="${(bean.fieldKey)!}"/>
                </li>
            </ul>
            <div class="subBar">
                <ul>
                    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
                    <li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
                </ul>
            </div>
        </div>
    </form>
</div>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li class="line">line</li>
            <li><a class="add" href="${base}/admin/formatDetailAlias/add" target="dialog"><span>添加</span></a></li>
            <li class="line">line</li>
        </ul>
    </div>
    <div id="w_list_print">
        <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="116">
            <thead>
                <tr>
                    <th width="30">序号</th>
                    <th>规格别名名称</th>
                    <th>规格项Id</th>
                    <th>规格项明细Id</th>
                    <th>创建人</th>
                    <th>创建时间</th>
                    <th>更新人</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <#if page??>
                <#list page.list as bean>
                <tr>
                    <td align="center">${bean_index+1}</td>
                    <td>${bean.aliasName}</td>
                    <td>${bean.formatTypeId}</td>
                    <td>${bean.formateTypeDetailId}</td>
                    <td>${bean.createPerson}</td>
                    <td>${bean.createTime}</td>
                    <td>${bean.updatePerson}</td>
                    <td>${bean.updateTime}</td>
                    <td>
                        <a title="查看" target="dialog" href="${base}/admin/formatDetailAlias/view?id=${bean.id}" class="btnView">查看 </a>
                        <a title="编辑" target="dialog" href="${base}/admin/formatDetailAlias/edit?id=${bean.id}" class="btnEdit">修改 </a>
                        <a title="确定要删除吗？" target="ajaxTodo" href="${base}/admin/formatDetailAlias/delete?id=${bean.id}" class="btnDel">删除</a>
                    </td>
                </tr>
                </#list>
                </#if>
            </tbody>
        </table>
    </div>
    <@pages />
</div>
