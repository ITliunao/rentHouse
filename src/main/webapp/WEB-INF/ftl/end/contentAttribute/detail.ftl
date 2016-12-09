<!DOCTYPE html>
<html>
<#include "/end/include/head.ftl"/>
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">详情</div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" id="dailogForm" action="${basePath}/contentAttribute/doEdit.do" method="POST">
                    <input type="hidden" id="btn_sub" class="btn_sub" />
                    <input type="hidden" value="${contentAttribute.id}" name="id"/>
                    <div class="form-group mno">
                        <label for="source" class="col-sm-2 control-label" style="text-align:left;">内容来源</label>
                        <div class="col-sm-8">
                            <input type="text" value="${contentAttribute.source!}" name="source" id="source" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="sourceUrl" class="col-sm-2 control-label" style="text-align:left;">来源地址</label>
                        <div class="col-sm-8">
                            <input type="text" value="${contentAttribute.sourceUrl!}" name="sourceUrl" id="sourceUrl" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="data" class="col-sm-2 control-label" style="text-align:left;">数据JSON</label>
                        <div class="col-sm-8">
                            <input type="text" value="${contentAttribute.data!}" name="data" id="data" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="text" class="col-sm-2 control-label" style="text-align:left;">内容</label>
                        <div class="col-sm-8">
                            <input type="text" value="${contentAttribute.text!}" name="text" id="text" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="wordCount" class="col-sm-2 control-label" style="text-align:left;">字数</label>
                        <div class="col-sm-8">
                            <input type="text" value="${contentAttribute.wordCount!}" name="wordCount" id="wordCount" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                    <div class="col-sm-offset-1 col-sm-6">
                        <button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('${basePath}/contentAttribute/list.do');">返回</button>
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
