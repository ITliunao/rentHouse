<!DOCTYPE html>
<html >
<#include "/end/include/head.ftl"/>
<link href="${basePath}/plug-in-ui/treetable/default/treeTable.css" rel="stylesheet" type="text/css" />
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-heading">列表</div>
                <div class="panel-body">
                    <ul id="channelTree" class="ztree" style="margin-top:0; width:200px;"></ul>
                </div>
            </div>
        </div>


        <div  class="col-md-9">
                <div class="panel panel-default">
                    <div class="panel-heading">列表</div>
                    <div class="panel-body">
                        <input name="pageNo" id="pageNo" value="${pageNo}" type="hidden">
                        <input name="pageSize" id="pageSize" th:value="${pageSize}" type="hidden">

                        <div id="legend">
                            <legend  class="le">
                            <@shiro.hasPermission name="cms.channel.add"><button type="button" class="btn btn-primary" onclick="javascript:doUrl('${basePath}/channel/toAdd.do');" >新增</button></@shiro.hasPermission>
                                <button type="button" class="btn btn-primary" onclick="javascript:doUrl('${basePath}/channel/list.do');" >刷新</button>
                            </legend>
                        </div>
                        <table class="table table-striped">
                            <thead>
                                <th>栏目名称</th>
                                <th>类型</th>
                                <th>顺序</th>
                                <th>是否显示</th>
                                <th>操作</th>
                            </thead>
                            <tbody id="channelJson">
                            <tr>

                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${basePath}/plug-in-ui/treetable/jquery.treeTable.min.js"></script>
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