<#assign base=request.contextPath />
<div class="pageContent">
	<form action="${base}/admin/personImage/save" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="58">
    		<p>
                <label>是否反向: 0否 1是：</label>
                <input type="text" name="isMirror" value="" placeholder="是否反向: 0否 1是" size="20" />
            </p>
    		<p>
                <label>所属用户Id：</label>
                <input type="text" name="personId" value="" placeholder="所属用户Id" size="20" />
            </p>
    		<p>
                <label>：</label>
                <input type="text" name="imageUrl" value="" placeholder="" size="20" />
            </p>
    		<p>
                <label>人脸在图片中位置：</label>
                <input type="text" name="faceRect" value="" placeholder="人脸在图片中位置" size="20" />
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
