<!DOCTYPE html>
<html>
<#include "/end/include/head.ftl"/>
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<form role="form" class="form-inline"  action="${basePath}/user/list.do" method="post"  id="formSubmit">
				<div  class="col-md-10" style="width:100%">
					<div class="panel panel-default">
						<div class="panel-heading">列表</div>
						<div class="panel-body">
							<input name="pageNo" id="pageNo" value="1" value="${pageNo}" type="hidden">
							<input name="pageSize" id="pageSize" value="10" value="${pageSize}" type="hidden">
							<div class="search">
										 <div class="form-group col-sm-3">
											<label for="username" class="control-label col-sm-3 line34">用户名</label>
											<div class="col-sm-8">
												<input type="text" name="username" id="username" value="${query.username!}" class="form-control">
											</div>
										 </div>

										 <div class="form-group col-sm-3">
											<label for="realName" class="control-label col-sm-3 line34">真实姓名</label>
											<div class="col-sm-8">
												<input type="text" name="realName" id="realName" value="${query.realName!}" class="form-control">
											</div>
										 </div>
								<button type="submit" class="btn btn-primary">搜  索</button>
								<div class="clearfix"></div>
							</div>

							<div id="legend">
								<legend  class="le">
									<@shiro.hasPermission name="system.user.add"><button type="button" class="btn btn-primary" onclick="javascript:doUrl(${basePath}/user/toAdd.do);" >新增</button></@shiro.hasPermission>
								</legend>
							</div>
							<table class="table table-striped">
								<thead>
											<th>用户名</th>
											<th>真实姓名</th>
											<th>用户状态</th>
											<th>手机号码</th>
											<th>上次登录时间</th>
											<th>上次登录IP</th>
										    <th>操作</th>
								</thead>
								<tbody>
								<#list list as info>
								<tr>
									<td>${info.username!}</td>
									<td>${info.realName!}</td>
									<td>${info.status!}</td>
									<td>${info.mobilePhone!}</td>
									<td>${info.lastLoginTime!}</td>
									<td>${info.lastLoginIp!}</td>
									<td class="last">
									<@shiro.hasPermission name="system.user.edit"><a href="javascript:void(0)" onclick="javascript:doUrl('${basePath}/user/toEdit.do?id=${info.id}')" >编辑</a></@shiro.hasPermission>
									<@shiro.hasPermission name="system.user.delete">  <a onclick="javascript:delData('${basePath}/user/doDelete.do?id=${info.id}')">删除</a></@shiro.hasPermission>
									<@shiro.hasPermission name="system.user.detail">	<a onclick="javascript:doUrl('${basePath}/user/toDetail.do?id=${info.id}')">详情</a></@shiro.hasPermission>
									<@shiro.hasPermission name="system.user.password">	<a onclick="javascript:doUrl('${basePath}/user/toPassword.do?id=${info.id}')">修改密码</a></@shiro.hasPermission>

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
</script>
</html>