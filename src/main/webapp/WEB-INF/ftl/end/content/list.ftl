<!DOCTYPE html>
<html >
<#include "/end/include/head.ftl"/>
<link href="${basePath}/plug-in-ui/treetable/default/treeTable.css" rel="stylesheet" type="text/css" />
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">列表</div>
                <div class="panel-body">
                    <ul id="channelTree" class="ztree" style="margin-top:0; width:200px;"></ul>
                </div>
            </div>
        </div>
        <form role="form" class="form-inline" action="${basePath}/content/list.do}" method="post"  id="formSubmit">
            <div  class="col-md-10">
                <div class="panel panel-default">
                    <div class="panel-heading">新闻列表</div>
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
                                <label for="status" class="control-label col-sm-3 line34">状态</label>
                                <div class="col-sm-8">
                                    <input type="text" name="status" id="status" value="${query.status!}" class="form-control">
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
                                <th>状态</th>
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
                                <@shiro.hasPermission name="cms.content.detail">  <a onclick="javascript:doUrl('${basePath}/content/toDetail.do?id=${info.id}')">详情</a></@shiro.hasPermission>
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
<script type="text/javascript" src="${basePath}/plug-in-ui/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${basePath}/plug-in-ui/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${basePath}/plug-in-ui/zTree/jquery.ztree.exedit.js"></script>
<script type="text/javascript">

    function zTreeOnClick(event, treeId, treeNode){
        console.log(treeNode.isParent);
        if (treeNode.isParent) {
            getChannel(treeNode.id);
        }
    }

    function getChannel(id) {
        $.getJSON("${basePath}/channel/channelList.do",{id:id},function(data){
            console.log(data);
            var sum ="";
            $.each(data.channelList,function (i,info) {
                if (data.edit) {
                    var addTd = "<a href=\"javascript:void(0)\" onclick=\"javascript:doUrl('${basePath}/channel/toEdit.do?id=" + info.id + "')\" >编辑</a>";
                }
                if (data.detail) {
                    var detailTd = "<a href=\"javascript:void(0)\" onclick=\"javascript:doUrl('${basePath}/channel/toDetail.do?id=" + info.id + "')\" >详情</a>";
                }
                if (data.delete) {
                    var deleteTd = "<a href=\"javascript:void(0)\" onclick=\"javascript:doUrl('${basePath}/channel/toDekete.do?id=" + info.id + "')\" >删除</a>";
                }
                var html = "<tr>"
                        + "<td>" + info.name + "</td>"
                        + "<td>" + info.type + "</td>"
                        + "<td>" + info.sort + "</td>"
                        + "<td>" + info.isDisplay + "</td>"
                        + "<td class=\"last\">" + addTd + detailTd + deleteTd + "</td>"
                        + "</tr>";
                sum = sum +　html;
            });
            $("#channelJson").empty().append(sum);
        })
    }
    var setting = {

        view: {
            //addHoverDom: addHoverDom,
            //removeHoverDom: removeHoverDom,
            dblClickExpand: false
        },

        data: {
            key: {
                name: "name"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: null
            }
        },callback: {
            onClick: zTreeOnClick
        }
    };



    var zNodes;
    $(document).ready(function() {
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            dataType: "json",
            url: '${basePath}/channel/tree.do',//请求的action路径
            error: function () {//请求失败处理函数
                alert('请求失败');
            },
            success: function (data) { //请求成功后处理函数。
                zNodes = data;   //把后台封装好的简单Json格式赋给zNodes
                //console.log(zNodes);
            }
        });
        zTree.init($("#channelTree"), setting, zNodes);
        getChannel(null);
    });
</script>

</html>