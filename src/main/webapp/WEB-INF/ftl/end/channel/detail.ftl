<!DOCTYPE html>
<html>
<#include "/end/include/head.ftl"/>
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">详情</div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" id="dailogForm" action="${basePath}/channel/doEdit.do" method="POST">
                    <input type="hidden" id="btn_sub" class="btn_sub" />
                    <input type="hidden" value="${channel.id}" name="id"/>
                    <div class="form-group mno">
                        <label for="parentId" class="col-sm-2 control-label" style="text-align:left;">父栏目</label>
                        <div class="col-sm-8">
                            <input type="text" value="${parentName!}" name="parentId" id="parentId" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="name" class="col-sm-2 control-label" style="text-align:left;">栏目名称</label>
                        <div class="col-sm-8">
                            <input type="text" value="${channel.name!}" name="name" id="name" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="type" class="col-sm-2 control-label" style="text-align:left;">类型</label>
                        <div class="col-sm-8">
                            <input type="text" value="${channel.type!}" name="type" id="type" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="sort" class="col-sm-2 control-label" style="text-align:left;">顺序</label>
                        <div class="col-sm-8">
                            <input type="text" value="${channel.sort!}" name="sort" id="sort" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="isDisplay" class="col-sm-2 control-label" style="text-align:left;">是否显示</label>
                        <div class="col-sm-8">
                            <input type="text" value="${channel.isDisplay!}" name="isDisplay" id="isDisplay" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                    <div class="col-sm-offset-1 col-sm-6">
                        <button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('${basePath}/channel/list.do');">返回</button>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="${basePath}/plug-in-ui/project/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${basePath}/plug-in-ui/project/js/forminit.p3.js"></script>
