<!DOCTYPE html>
<html>
<#include "/end/include/head.ftl"/>
<#include "/end/include/ueditor.ftl"/>
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">新增</div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" id="dailogForm" action="${basePath}/content/doAdd.do" method="POST">
                    <input type="hidden" id="btn_sub" class="btn_sub" />
                    <div class="form-group mno">
                        <label for="title" class="col-sm-2 control-label" style="text-align:left;">标题</label>
                        <div class="col-sm-8">
                            <input type="text" name="title" id="title" class="form-control"  datatype="*" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="userId" class="col-sm-2 control-label" style="text-align:left;">发表用户</label>
                        <div class="col-sm-8">
                            <input type="text" name="userId" id="userId" class="form-control"  datatype="*" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="checkUserId" class="col-sm-2 control-label" style="text-align:left;">审核用户</label>
                        <div class="col-sm-8">
                            <input type="text" name="checkUserId" id="checkUserId" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="channelId" class="col-sm-2 control-label" style="text-align:left;">栏目</label>
                        <div class="col-sm-8">
                            <input type="text" name="channelId" id="channelId" class="form-control"  datatype="*" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="copied" class="col-sm-2 control-label" style="text-align:left;">是否转载</label>
                        <div class="col-sm-8">
                            <input type="text" name="copied" id="copied" class="form-control"  datatype="*" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="author" class="col-sm-2 control-label" style="text-align:left;">作者</label>
                        <div class="col-sm-8">
                            <input type="text" name="author" id="author" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="editor" class="col-sm-2 control-label" style="text-align:left;">编辑</label>
                        <div class="col-sm-8">
                            <input type="text" name="editor" id="editor" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="description" class="col-sm-2 control-label" style="text-align:left;">简介</label>
                        <div class="col-sm-8">
                            <input type="text" name="description" id="description" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="clicks" class="col-sm-2 control-label" style="text-align:left;">点击数</label>
                        <div class="col-sm-8">
                            <input type="text" name="clicks" id="clicks" class="form-control"  datatype="*" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="comments" class="col-sm-2 control-label" style="text-align:left;">评论数</label>
                        <div class="col-sm-8">
                            <input type="text" name="comments" id="comments" class="form-control"  datatype="*" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="publishDate" class="col-sm-2 control-label" style="text-align:left;">发布日期</label>
                        <div class="col-sm-8">
                            <input type="text" name="publishDate" id="publishDate" class="form-control"  datatype="*" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="createDate" class="col-sm-2 control-label" style="text-align:left;">创建日期</label>
                        <div class="col-sm-8">
                            <input type="text" name="createDate" id="createDate" class="form-control"  datatype="*" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="text" class="col-sm-2 control-label" style="text-align:left;">创建日期</label>
                        <div class="col-sm-8">
                            <input type="text" name="createDate" id="createDate" class="form-control"  datatype="*" />
                        </div>
                    </div>
                    <div class="form-group mno">
                        <label for="status" class="col-sm-2 control-label" style="text-align:left;">状态：0、草稿 1、已发布 2、待审核</label>
                        <div class="col-sm-8">
                            <input type="text" name="status" id="status" class="form-control"  datatype="*" />
                        </div>
                    </div>

                    <div class="form-group mno">
                        <label for="isComment" class="col-sm-2 control-label" style="text-align:left;">内容</label>
                        <div class="col-sm-8">
                            <script id="container" name="content" type="text/plain" style="height:600px;">这里写你的初始化内容</script>
                        </div>
                    </div>
                    <div class="form-group mno">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('${basePath}/content/list.do');">返回</button>
                            <button type="button" class="btn btn-primary" id="formSubmit">提交</button>
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
<script type="text/javascript">
    var editor = UE.getEditor('container');
</script>