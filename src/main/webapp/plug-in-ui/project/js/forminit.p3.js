
$(function (){
/*			var form = $("#dailogForm").Validform();
        	form.config({tiptype:4});
    		//form.tipmsg.s="非空";
    		//form.tipmsg.r=" ";
        	$("#formSubmit").bind("click",function(){
        		var flag = form.check();
        		if(flag){

        			if(confirm("确认提交吗？")){
						ajaxdoFormSubmit('dailogForm');
            		}
    			}else{
					alert("信息校验没有通过，请检查填写的信息");
				}
        	});*/

	var form = $("#dailogForm").Validform();
	form.config({tiptype:4});
	//form.tipmsg.s="非空";
	//form.tipmsg.r=" ";
	$("#formSubmit").bind("click",function(){
		var flag = form.check();
		if(flag){
			swal({
					title: "确认提交吗？",
					type: "warning",
					showCancelButton: true,
					confirmButtonColor: "#DD6B55",
					confirmButtonText: "确认",
					cancelButtonText: "取消",
					closeOnConfirm: false,
					closeOnCancel: true
				},
				function(isConfirm){
					if (isConfirm) {
						var url = $('#dailogForm').attr("action");
						$('#dailogForm').ajaxSubmit({
							url: url,
							type: 'post',
							dataType: 'json',
							success: function(data, status){
								if(data.status == "401"){
									sweetInfo("没有权限！");
									return;
								}
								if(data.success){
									sweetSuccess(data.msg);
									setTimeout(function () {
										$('#formReturn').click();
									}, 2000);
									//location.reload();
								}else{
									sweetInfo(data.msg);
								}
							},
							error: function(data, status, e){
								if(data.status == "401"){
									sweetInfo("没有权限！");
									return;
								}
							}
						});
					}
				});
		}else{
			sweetInfo("信息校验没有通过，请检查填写的信息")
		}
	});
});