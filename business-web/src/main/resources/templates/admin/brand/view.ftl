<#assign base=request.contextPath /> 
<div class="pageContent">
    <div class="pageFormContent" layoutH="58">
        <p>
            <label>创建人：</label>${bean.createPerson}
        </p>
        <p>
            <label>创建时间：</label>${bean.createTime}
        </p>
        <p>
            <label>更新人：</label>${bean.updatePerson}
        </p>
        <p>
            <label>修改时间：</label>${bean.updateTime}
        </p>
    </div>
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
        </ul>
    </div>
</div>
