<!DOCTYPE html>
<html>
<#include "/end/include/head.ftl"/>
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">修改</div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" id="dailogForm" action="${basePath}/channel/doEdit.do" method="POST">
                    <input type="hidden" id="btn_sub" class="btn_sub" />
                    <input type="hidden" value="${channel.id}" name="id"/>
                    <div class="form-group mno">
                                <label for="parentId" class="col-sm-2 control-label" style="text-align:left;">父栏目ID</label>
                                <div class="col-sm-8">
                                    <input type="hidden" value="${channel.parentId!}" name="parentId" id="pId" class="form-control"/>
                                    <input type="text" value="" id="parentId" readonly class="form-control" onclick="showMenu();"/>

                                </div>
                            </div>
                    <div class="form-group mno">
                                <label for="name" class="col-sm-2 control-label" style="text-align:left;">栏目名称</label>
                                <div class="col-sm-8">
                                    <input type="text" value="${channel.name!}" name="name" id="name" class="form-control"/>
                                </div>
                            </div>
                    <div class="form-group mno">
                                <label for="type" class="col-sm-2 control-label" style="text-align:left;">类型</label>
                                <div class="col-sm-8">
                                    <input type="text" value="${channel.type!}" name="type" id="type" class="form-control"/>
                                </div>
                            </div>
                    <div class="form-group mno">
                                <label for="sort" class="col-sm-2 control-label" style="text-align:left;">顺序</label>
                                <div class="col-sm-8">
                                    <input type="text" value="${channel.sort!}" name="sort" id="sort" class="form-control"/>
                                </div>
                            </div>
                    <div class="form-group mno">
                                <label for="isDisplay" class="col-sm-2 control-label" style="text-align:left;">是否显示</label>
                                <div class="col-sm-8">
                                    <input type="text" value="${channel.isDisplay!}" name="isDisplay" id="isDisplay" class="form-control"/>
                                </div>
                            </div>
                    <div class="form-group mno">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('${basePath}/channel/list.do');">返回</button>
                            <button type="button" class="btn btn-primary" id="formSubmit">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>
</body>
</html>
<script type="text/javascript" src="${basePath}/plug-in-ui/project/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${basePath}/plug-in-ui/project/js/forminit.p3.js"></script>
<script type="text/javascript" src="${basePath}/plug-in-ui/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${basePath}/plug-in-ui/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${basePath}/plug-in-ui/zTree/jquery.ztree.exedit.js"></script>
<script type="text/javascript">
    var IDMark_A = "_a";

    function showMenu() {
        var cityObj = $("#parentId");
        var cityOffset = $("#parentId").offset();
        if($("#menuContent").is(':hidden')){
            $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        }else{
            $("#menuContent").fadeOut("fast");
        }
        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "parentId" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

    var setting = {
        check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
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
        },
        callback: {
            onClick: onClick,
            onCheck: onCheck
        }
    };
    function onClick(e, treeId, treeNode) {
        var tree = zTree.getZTreeObj("treeDemo");
        tree.checkNode(treeNode, !treeNode.checked, null, true);
        return false;
    }
    function onCheck(e, treeId, treeNode) {
        var tree = zTree.getZTreeObj("treeDemo"),
                nodes = tree.getCheckedNodes(true),
                v = "",vid = "";
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
            vid += nodes[i].id + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        if (vid.length > 0 ) vid = vid.substring(0, vid.length-1);
        $("#parentId").val(v);
        $("#pId").val(vid);
    }
    function addHoverDom(treeId, treeNode) {
        if (treeNode.parentNode && treeNode.parentNode.id!=1) return;
        var aObj = $("#" + treeNode.tId + IDMark_A);
        if ($("#diyBtn_"+treeNode.id).length>0) return;
        var editStr = "<span id='diyBtn_" +treeNode.id+ "'>编码:"+treeNode.id+"</span>";
        aObj.after(editStr);
    }
    function removeHoverDom(treeId, treeNode) {
        $("#diyBtn_"+treeNode.id).unbind().remove();
        $("#diyBtn_space_" +treeNode.id).unbind().remove();
    }

    var zNodes;
    $(document).ready(function(){
        $.ajax({
            async : false,
            cache:false,
            type: 'POST',
            dataType : "json",
            url: '${basePath}/channel/tree.do',//请求的action路径
            error: function () {//请求失败处理函数
                alert('请求失败');
            },
            success:function(data){ //请求成功后处理函数。
                zNodes = data;   //把后台封装好的简单Json格式赋给zNodes
                console.log(zNodes);
                var id = $("#pId").val();
                $.each(data,function (i,n) {
                    if (n.id == id ) {
                        $("#parentId").val(n.name);
                    }
                })
            }
        });
        zTree.init($("#treeDemo"), setting, zNodes);
    });
</script>