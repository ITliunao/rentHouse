<!DOCTYPE html>
<html >
<#include "/end/include/head.ftl"/>
<link href="${basePath}/plug-in-ui/treetable/default/treeTable.css" rel="stylesheet" type="text/css" />
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <form role="form" class="form-inline" action="${basePath}/contentAttribute/list.do}" method="post"  id="formSubmit">
            <div  class="col-md-10" style="width:100%">
                <div class="panel panel-default">
                    <div class="panel-heading">列表</div>
                    <div class="panel-body">
                        <input name="pageNo" id="pageNo" value="${pageNo}" type="hidden">
                        <input name="pageSize" id="pageSize" th:value="${pageSize}" type="hidden">
                        <div class="search">
                            <div class="form-group col-sm-3">
                                <label for="source" class="control-label col-sm-3 line34">内容来源</label>
                                <div class="col-sm-8">
                                    <input type="text" name="source" id="source" value="${query.source!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="sourceUrl" class="control-label col-sm-3 line34">来源地址</label>
                                <div class="col-sm-8">
                                    <input type="text" name="sourceUrl" id="sourceUrl" value="${query.sourceUrl!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="data" class="control-label col-sm-3 line34">数据JSON</label>
                                <div class="col-sm-8">
                                    <input type="text" name="data" id="data" value="${query.data!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="text" class="control-label col-sm-3 line34">内容</label>
                                <div class="col-sm-8">
                                    <input type="text" name="text" id="text" value="${query.text!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="wordCount" class="control-label col-sm-3 line34">字数</label>
                                <div class="col-sm-8">
                                    <input type="text" name="wordCount" id="wordCount" value="${query.wordCount!}" class="form-control">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">搜  索</button>
                            <div class="clearfix"></div>
                        </div>

                        <div id="legend">
                            <legend  class="le">
                            <@shiro.hasPermission name="cms.contentAttribute.add"><button type="button" class="btn btn-primary" onclick="javascript:doUrl('${basePath}/contentAttribute/toAdd.do');" >新增</button></@shiro.hasPermission>
                            </legend>
                        </div>
                        <table class="table table-striped">
                            <thead>
                                <th>内容来源</th>
                                <th>来源地址</th>
                                <th>数据JSON</th>
                                <th>内容</th>
                                <th>字数</th>
                                <th>操作</th>
                            </thead>
                            <tbody>
                            <#list list as info>
                            <tr>
                                <td>${info.source!}</td>
                                <td>${info.sourceUrl!}</td>
                                <td>${info.data!}</td>
                                <td>${info.text!}</td>
                                <td>${info.wordCount!}</td>
                                <td class="last">
                                <@shiro.hasPermission name="cms.contentAttribute.edit"><a href="javascript:void(0)" onclick="javascript:doUrl('${basePath}/contentAttribute/toEdit.do?id=${info.id}')" >编辑</a></@shiro.hasPermission>
                                <@shiro.hasPermission name="cms.contentAttribute.delete">  <a onclick="javascript:delData('${basePath}/contentAttribute/doDelete.do?id=${info.id}')">删除</a></@shiro.hasPermission>
                                <@shiro.hasPermission name="cms.contentAttribute.detail">	<a onclick="javascript:doUrl('${basePath}/contentAttribute/toDetail.do?id=${info.id}')">详情</a></@shiro.hasPermission>
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