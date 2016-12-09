<!DOCTYPE html>
<html>
<#include "/end/include/head.ftl"/>
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">详情</div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" id="dailogForm" action="${basePath}/role/doEdit.do" method="POST">
                    <input type="hidden" id="btn_sub" class="btn_sub" />
                    <input type="hidden" value="${role.id}" name="id"/>
                    <div class="form-group mno">
                        <label for="roleCode" class="col-sm-2 control-label" style="text-align:left;">角色编码</label>
                        <div class="col-sm-8">
                            <input type="text" value="${role.roleCode!}" name="roleCode" id="roleCode" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="roleName" class="col-sm-2 control-label" style="text-align:left;">角色名称</label>
                        <div class="col-sm-8">
                            <input type="text" value="${role.roleName!}" name="roleName" id="roleName" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="updateName" class="col-sm-2 control-label" style="text-align:left;">修改人</label>
                        <div class="col-sm-8">
                            <input type="text" value="${role.updateName!}" name="updateName" id="updateName" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="updateDate" class="col-sm-2 control-label" style="text-align:left;">修改时间</label>
                        <div class="col-sm-8">
                            <input type="text" value="${role.updateDate!}" name="updateDate" id="updateDate" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="updateBy" class="col-sm-2 control-label" style="text-align:left;">修改人id</label>
                        <div class="col-sm-8">
                            <input type="text" value="${role.updateBy!}" name="updateBy" id="updateBy" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="createName" class="col-sm-2 control-label" style="text-align:left;">创建人</label>
                        <div class="col-sm-8">
                            <input type="text" value="${role.createName!}" name="createName" id="createName" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="createDate" class="col-sm-2 control-label" style="text-align:left;">创建时间</label>
                        <div class="col-sm-8">
                            <input type="text" value="${role.createDate!}" name="createDate" id="createDate" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="createBy" class="col-sm-2 control-label" style="text-align:left;">创建人id</label>
                        <div class="col-sm-8">
                            <input type="text" value="${role.createBy!}" name="createBy" id="createBy" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group mno">
                    <div class="col-sm-offset-1 col-sm-6">
                        <button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('${basePath}/role/list.do');">返回</button>
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
