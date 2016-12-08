<!DOCTYPE html>
<html><#include "/end/include/head.ftl"/>
<body style='overflow:scroll;overflow-x:hidden'>
<div class="container bs-docs-container" style="width:100%;">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">修改</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="dailogForm" action="" method="POST">
					<input type="hidden" id="btn_sub" class="btn_sub" />
					<input type="hidden" value="${user.id}" name="id"/>
					<div class="form-group mno">
						<label for="username" class="col-sm-2 control-label" style="text-align:left;">用户名</label>
						<div class="col-sm-8">
							<input type="text" name="username" id="username" value="${user.username}" class="form-control" readonly="true" />
						</div>
					</div>
					<div class="form-group mno">
						<label for="realName" class="col-sm-2 control-label" style="text-align:left;">真实姓名</label>
						<div class="col-sm-8">
							<input type="text" name="realName" value="${user.realName}"  id="realName" class="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group mno">
						<label for="status" class="col-sm-2 control-label" style="text-align:left;">用户状态</label>
						<div class="col-sm-8">
							<input type="text" name="status" id="status" value="${user.status!}" class="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group mno">
						<label for="email" class="col-sm-2 control-label" style="text-align:left;">邮箱</label>
						<div class="col-sm-8">
							<input type="text" name="email" id="email" value="${user.email!}" class="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group mno">
						<label for="birthday" class="col-sm-2 control-label" style="text-align:left;">生日</label>
						<div class="col-sm-8">
							<input type="text" readonly="true" name="birthday" id="birthday" value="${(user.birthday?string('yy-MMMM-dd'))!''}" class="form-control" readonly="true" style="background: url('${basePath}/plug-in-ui/images/datetime.png') no-repeat scroll right center transparent;" />
						</div>
					</div>
					<div class="form-group mno">
						<label for="sex" class="col-sm-2 control-label" style="text-align:left;">性别</label>
						<div class="col-sm-8">
							<input type="text"  name="sex" id="sex" value="${user.sex!}" class="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group mno">
						<label for="mobilePhone" class="col-sm-2 control-label" style="text-align:left;">手机号码</label>
						<div class="col-sm-8">
							<input type="text" name="mobilePhone" id="mobilePhone" value="${user.mobilePhone!}" class="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group mno">
						<label for="officePhone" class="col-sm-2 control-label" style="text-align:left;">办公室电话</label>
						<div class="col-sm-8">
							<input type="text" name="officePhone" id="officePhone" value="${user.officePhone}" class="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group mno">
						<label for="region" class="col-sm-2 control-label" style="text-align:left;">所属区域</label>
						<div class="col-sm-8">
							<input type="text" name="region" id="region" value="${user.region}" class="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group mno">
						<label for="lastLoginTime" class="col-sm-2 control-label" style="text-align:left;">上次登录时间</label>
						<div class="col-sm-8">
							<input type="text" name="lastLoginTime" id="lastLoginTime" value="${user.lastLoginTime}" class="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group mno">
						<label for="lastLoginIp" class="col-sm-2 control-label" style="text-align:left;">上次登录IP</label>
						<div class="col-sm-8">
							<input type="text" name="lastLoginIp" id="lastLoginIp" value="${user.lastLoginIp}" class="form-control" readonly="true"/>
						</div>
					</div>

					<div class="form-group mno">
						<label for="departId" class="col-sm-2 control-label" style="text-align:left;">部门ID</label>
						<div class="col-sm-8">
							<input type="text" name="departId" id="departId" value="${user.departId}" class="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group mno">
						<div class="col-sm-offset-1 col-sm-6">
							<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('${basePath}/user/list.do');">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${base}/plug-in-ui/project/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${base}/plug-in-ui/project/js/forminit.p3.js"></script>
