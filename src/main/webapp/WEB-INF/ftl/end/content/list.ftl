<!DOCTYPE html>
<html >
<#include "/end/include/head.ftl"/>
<link href="${basePath}/plug-in-ui/treetable/default/treeTable.css" rel="stylesheet" type="text/css" />
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <form role="form" class="form-inline" action="${basePath}/content/list.do}" method="post"  id="formSubmit">
            <div  class="col-md-10" style="width:100%">
                <div class="panel panel-default">
                    <div class="panel-heading">列表</div>
                    <div class="panel-body">
                        <input name="pageNo" id="pageNo" value="${pageNo}" type="hidden">
                        <input name="pageSize" id="pageSize" th:value="${pageSize}" type="hidden">
                        <div class="search">
                            <div class="form-group col-sm-3">
                                <label for="title" class="control-label col-sm-3 line34">标题</label>
                                <div class="col-sm-8">
                                    <input type="text" name="title" id="title" value="${query.title!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="userId" class="control-label col-sm-3 line34">发表用户</label>
                                <div class="col-sm-8">
                                    <input type="text" name="userId" id="userId" value="${query.userId!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="checkUserId" class="control-label col-sm-3 line34">审核用户</label>
                                <div class="col-sm-8">
                                    <input type="text" name="checkUserId" id="checkUserId" value="${query.checkUserId!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="channelId" class="control-label col-sm-3 line34">栏目</label>
                                <div class="col-sm-8">
                                    <input type="text" name="channelId" id="channelId" value="${query.channelId!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="copied" class="control-label col-sm-3 line34">是否转载</label>
                                <div class="col-sm-8">
                                    <input type="text" name="copied" id="copied" value="${query.copied!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="author" class="control-label col-sm-3 line34">作者</label>
                                <div class="col-sm-8">
                                    <input type="text" name="author" id="author" value="${query.author!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="editor" class="control-label col-sm-3 line34">编辑</label>
                                <div class="col-sm-8">
                                    <input type="text" name="editor" id="editor" value="${query.editor!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="description" class="control-label col-sm-3 line34">简介</label>
                                <div class="col-sm-8">
                                    <input type="text" name="description" id="description" value="${query.description!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="clicks" class="control-label col-sm-3 line34">点击数</label>
                                <div class="col-sm-8">
                                    <input type="text" name="clicks" id="clicks" value="${query.clicks!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="comments" class="control-label col-sm-3 line34">评论数</label>
                                <div class="col-sm-8">
                                    <input type="text" name="comments" id="comments" value="${query.comments!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="publishDate" class="control-label col-sm-3 line34">发布日期</label>
                                <div class="col-sm-8">
                                    <input type="text" name="publishDate" id="publishDate" value="${query.publishDate!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="createDate" class="control-label col-sm-3 line34">创建日期</label>
                                <div class="col-sm-8">
                                    <input type="text" name="createDate" id="createDate" value="${query.createDate!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="status" class="control-label col-sm-3 line34">状态：0、草稿 1、已发布 2、待审核</label>
                                <div class="col-sm-8">
                                    <input type="text" name="status" id="status" value="${query.status!}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="isComment" class="control-label col-sm-3 line34">是否可以评论</label>
                                <div class="col-sm-8">
                                    <input type="text" name="isComment" id="isComment" value="${query.isComment!}" class="form-control">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">搜  索</button>
                            <div class="clearfix"></div>
                        </div>

                        <div id="legend">
                            <legend  class="le">
                            <@shiro.hasPermission name="cms.content.add"><button type="button" class="btn btn-primary" onclick="javascript:doUrl('${basePath}/content/toAdd.do');" >新增</button></@shiro.hasPermission>
                            </legend>
                        </div>
                        <table class="table table-striped">
                            <thead>
                                <th>标题</th>
                                <th>发表用户</th>
                                <th>审核用户</th>
                                <th>栏目</th>
                                <th>是否转载</th>
                                <th>作者</th>
                                <th>编辑</th>
                                <th>简介</th>
                                <th>点击数</th>
                                <th>评论数</th>
                                <th>发布日期</th>
                                <th>创建日期</th>
                                <th>状态：0、草稿 1、已发布 2、待审核</th>
                                <th>是否可以评论</th>
                                <th>操作</th>
                            </thead>
                            <tbody>
                            <#list list as info>
                            <tr>
                                <td>${info.title!}</td>
                                <td>${info.userId!}</td>
                                <td>${info.checkUserId!}</td>
                                <td>${info.channelId!}</td>
                                <td>${info.copied!}</td>
                                <td>${info.author!}</td>
                                <td>${info.editor!}</td>
                                <td>${info.description!}</td>
                                <td>${info.clicks!}</td>
                                <td>${info.comments!}</td>
                                <td>${info.publishDate!}</td>
                                <td>${info.createDate!}</td>
                                <td>${info.status!}</td>
                                <td>${info.isComment!}</td>
                                <td class="last">
                                <@shiro.hasPermission name="cms.content.edit"><a href="javascript:void(0)" onclick="javascript:doUrl('${basePath}/content/toEdit.do?id=${info.id}')" >编辑</a></@shiro.hasPermission>
                                <@shiro.hasPermission name="cms.content.delete">  <a onclick="javascript:delData('${basePath}/content/doDelete.do?id=${info.id}')">删除</a></@shiro.hasPermission>
                                <@shiro.hasPermission name="cms.content.detail">	<a onclick="javascript:doUrl('${basePath}/content/toDetail.do?id=${info.id}')">详情</a></@shiro.hasPermission>
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