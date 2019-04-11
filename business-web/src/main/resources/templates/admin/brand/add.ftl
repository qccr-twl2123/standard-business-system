<#assign base=request.contextPath />
<div class="bjui-pageContent">
    <form action="${base}/admin/brand/save" data-toggle="validate" enctype='multipart/form-data' method="post" >
        <div class="form-group">
            <label class="control-label x85">品牌名称：</label>
            <input type="text" name="name" value="" placeholder="品牌名称" size="20" data-rule="required"/>
        </div>
        <div class="form-group">
            <label class="control-label x85">简写字母：</label>
            <input type="text" name="letter" value="" placeholder="简写字母" size="20" data-rule="required"/>
        </div>
        <div class="form-group">
            <label class="control-label x85">logo：</label>
            <input type="file" name="imageFile"   placeholder="logo" size="20" data-rule="required"/>
        </div>
        <div class="form-group">
            <label class="control-label x85">品牌英文：</label>
            <input type="text" name="englishText" value="" placeholder="品牌英文" size="20" data-rule="required"/>
        </div>
        <div class="form-group">
            <label class="control-label x85">品牌中文：</label>
            <input type="text" name="chineseText" value="" placeholder="品牌中文" size="20" data-rule="required"/>
        </div>
        <div class="form-group">
            <label class="control-label x85">入驻时间：</label>
            <input type="text" data-pattern="yyyy-MM-dd HH:mm:ss" data-toggle="datepicker" name="enterTime" value="" placeholder="入驻时间" size="20" data-rule="required"/>
        </div>

    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>
