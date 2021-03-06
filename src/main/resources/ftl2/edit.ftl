<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head th:replace="end/include/head">
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">修改</div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" id="dailogForm" action="role/add.html" th:action="@{/${lowerName}/doEdit.do}" method="POST">
                    <input type="hidden" id="btn_sub" class="btn_sub" />
                    <input type="hidden" th:value="${${lowerName}.id}" name="id"/>

                    <#list columnDatas as item>
                        <#if item.domainPropertyName != 'id'>
                            <div class="form-group mno">
                                <label for="${item.domainPropertyName}" class="col-sm-2 control-label" style="text-align:left;">${item.columnComment}</label>
                                <div class="col-sm-8">
                                    <input type="text" th:value="${${lowerName}.${item.domainPropertyName}}" name="${item.domainPropertyName}" id="${item.domainPropertyName}" class="form-control"/>
                                </div>
                            </div>
                        </#if>
                    </#list>
                    <div class="form-group mno">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" th:onclick="'doUrl(\''+ @{/${lowerName}/list.do} +'\');'">返回</button>
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
<script type="text/javascript" th:src="@{/plug-in-ui/project/js/Validform_v5.3.2.js}"></script>
<script type="text/javascript" th:src="@{/plug-in-ui/project/js/forminit.p3.js}"></script>
