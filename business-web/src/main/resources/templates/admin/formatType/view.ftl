<#assign base=request.contextPath />
<div class="bjui-pageContent">
        <div style="margin:15px auto 0; width:98%;">
            <fieldset>
                <legend>商品录入</legend>
                <!-- Tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#home" role="tab" data-toggle="tab">基础信息</a></li>
                    <li><a href="#valueTab" role="tab" data-toggle="tab">规格值管理</a></li>
                    <li><a href="#aliasTab" role="tab" data-toggle="tab">别名管理</a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade active in" id="home">
                        <#include "/admin/formateType/info.ftl" />
                    </div>
                    <div class="tab-pane fade" id="valueTab">
                        规格值页面
                    </div>
                    <div class="tab-pane fade" id="aliasTab">
                        别名管理
                        <#--<#include "/admin/goods/goodsSpec.ftl" />-->
                    </div>
                </div>
            </fieldset>
        </div>
</div>

