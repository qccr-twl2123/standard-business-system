<#assign base=request.contextPath />
<div class="pageContent">
	<form action="${base}/admin/formatDetailAlias/save" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="58">
    		<p>
                <label>规格别名名称：</label>
                <input type="text" name="aliasName" value="" placeholder="规格别名名称" size="20" />
            </p>
    		<p>
                <label>规格项Id：</label>
                <input type="text" name="formatTypeId" value="" placeholder="规格项Id" size="20" />
            </p>
    		<p>
                <label>规格项明细Id：</label>
                <input type="text" name="formateTypeDetailId" value="" placeholder="规格项明细Id" size="20" />
            </p>
    		<p>
                <label>创建人：</label>
                <input type="text" name="createPerson" value="" placeholder="创建人" size="20" />
            </p>
    		<p>
                <label>创建时间：</label>
                <input type="text" name="createTime" value="" placeholder="创建时间" size="20" />
            </p>
    		<p>
                <label>更新人：</label>
                <input type="text" name="updatePerson" value="" placeholder="更新人" size="20" />
            </p>
    		<p>
                <label>修改时间：</label>
                <input type="text" name="updateTime" value="" placeholder="修改时间" size="20" />
            </p>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
	</form>
</div>
