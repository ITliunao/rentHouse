<!DOCTYPE html>
<html >
<#include "/end/include/head.ftl"/>
<link href="${basePath}/plug-in-ui/treetable/default/treeTable.css" rel="stylesheet" type="text/css" />
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <form role="form" class="form-inline" action="${basePath}/role/list.do}" method="post"  id="formSubmit">
            <div  class="col-md-10" style="width:100%">
                <div class="panel panel-default">
                    <div class="panel-heading">列表</div>
                    <div class="panel-body">
                        <input name="pageNo" id="pageNo" value="${pageNo}" type="hidden">
                        <input name="pageSize" id="pageSize" th:value="${pageSize}" type="hidden">
                        <div class="search">
                            <div class="form-group col-sm-3">
                                <label for="roleCode" class="control-label col-sm-3 line34">角色编码</label>
                                <div class="col-sm-8">
                                    <input type="text" name="roleCode" id="roleCode" value="${query.roleCode!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="roleName" class="control-label col-sm-3 line34">角色名称</label>
                                <div class="col-sm-8">
                                    <input type="text" name="roleName" id="roleName" value="${query.roleName!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="updateName" class="control-label col-sm-3 line34">修改人</label>
                                <div class="col-sm-8">
                                    <input type="text" name="updateName" id="updateName" value="${query.updateName!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="updateDate" class="control-label col-sm-3 line34">修改时间</label>
                                <div class="col-sm-8">
                                    <input type="text" name="updateDate" id="updateDate" value="${query.updateDate!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="updateBy" class="control-label col-sm-3 line34">修改人id</label>
                                <div class="col-sm-8">
                                    <input type="text" name="updateBy" id="updateBy" value="${query.updateBy!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="createName" class="control-label col-sm-3 line34">创建人</label>
                                <div class="col-sm-8">
                                    <input type="text" name="createName" id="createName" value="${query.createName!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="createDate" class="control-label col-sm-3 line34">创建时间</label>
                                <div class="col-sm-8">
                                    <input type="text" name="createDate" id="createDate" value="${query.createDate!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="createBy" class="control-label col-sm-3 line34">创建人id</label>
                                <div class="col-sm-8">
                                    <input type="text" name="createBy" id="createBy" value="${query.createBy!}" class="form-control">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">搜  索</button>
                            <div class="clearfix"></div>
                        </div>

                        <div id="legend">
                            <legend  class="le">
                            <@shiro.hasPermission name="system.role.add"><button type="button" class="btn btn-primary" onclick="javascript:doUrl('${basePath}/role/toAdd.do');" >新增</button></@shiro.hasPermission>
                            </legend>
                        </div>
                        <table class="table table-striped">
                            <thead>
                                <th>角色编码</th>
                                <th>角色名称</th>
                                <th>修改人</th>
                                <th>修改时间</th>
                                <th>修改人id</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>创建人id</th>
                                <th>操作</th>
                            </thead>
                            <tbody>
                            <#list list as info>
                            <tr>
                                <td>${info.roleCode!}</td>
                                <td>${info.roleName!}</td>
                                <td>${info.updateName!}</td>
                                <td>${info.updateDate!}</td>
                                <td>${info.updateBy!}</td>
                                <td>${info.createName!}</td>
                                <td>${info.createDate!}</td>
                                <td>${info.createBy!}</td>
                                <td class="last">
                                <@shiro.hasPermission name="system.role.edit"><a href="javascript:void(0)" onclick="javascript:doUrl('${basePath}/role/toEdit.do?id=${info.id}')" >编辑</a></@shiro.hasPermission>
                                <@shiro.hasPermission name="system.role.delete">  <a onclick="javascript:delData('${basePath}/role/doDelete.do?id=${info.id}')">删除</a></@shiro.hasPermission>
                                <@shiro.hasPermission name="system.role.detail">	<a onclick="javascript:doUrl('${basePath}/role/toDetail.do?id=${info.id}')">详情</a></@shiro.hasPermission>
                                <@shiro.hasPermission name="system.role.assign">	<a onclick="javascript:doUrl('${basePath}/role/toAssign.do?id=${info.id}')">分配权限</a></@shiro.hasPermission>
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                        <div class="text-right">
                            <ul class="pagination" id="pagination1"></ul>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="${basePath}/plug-in-ui/treetable/jquery.treeTable.min.js"></script>

<script type="text/javascript">
    //当前页码
    var pageNo = ${pageNo};
    //当前页数
    var pages = ${pages};

    var visiblePages = pages;
    if (pages >= 10) {
        visiblePages = 10;
    }

    $.jqPaginator('#pagination1', {
        totalPages: pages,
        visiblePages: visiblePages,
        currentPage: pageNo,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        first: '<li class="next"><a href="javascript:;">首页</a></li>',
        last: '<li class="next"><a href="javascript:;">末页</a></li>',
        onPageChange: function (num, type) {
            console.log("num : " + num);
            console.log("type : " + type);
            if (type != "init") {
                //$('#p1').text(type + '：' + num);
                document.getElementById('pageNo').value = num;
                document.getElementById('formSubmit').submit();
            }
        }
    });
    $(function(){
        var option = {
            theme:'default',
            expandLevel : 3,
            beforeExpand : function($treeTable, id) {
            },
            onSelect : function($treeTable, id) {
                window.console && console.log('onSelect:' + id);
            }

        };
        $('#treeTable1').treeTable(option);
    });
</script>
</html>