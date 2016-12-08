<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>登录</title>
    <meta name="keywords" content="北京蓝太平洋blueApp支付和短信中心">
    <meta name="description" content="北京蓝太平洋blueApp支付和短信中心">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${basePath}/plug-in-ui/hplus/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/plug-in-ui/hplus/css/font-awesome.css" rel="stylesheet">

    <link href="${basePath}/plug-in-ui/hplus/css/animate.css" rel="stylesheet">
    <link href="${basePath}/plug-in-ui/hplus/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
    <style>
        .middle-box h1{
            font-size:80px;
        }
    </style>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">后台</h1>

            </div>
            <h3>java后台管理系统</h3>

            <form class="m-t" role="form" action="${basePath}/login.do" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" required="" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" required="" name="password">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


            </form>
        </div>
    </div>

    <!-- 全局js -->
    <script type="text/javascript" src="${base}/plug-in-ui/project/js/Validform_v5.3.2.js"></script>
    <script type="text/javascript" src="${base}/plug-in-ui/project/js/forminit.p3.js"></script>

</body>

</html>
