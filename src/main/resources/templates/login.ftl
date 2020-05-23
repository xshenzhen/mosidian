
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>会员管理系统</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/macroease.css" />
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="/js/dolphin.js" type="text/javascript" charset="utf-8"></script>
</head>

<body class="me-login-background" onkeydown="on_return();">
<div class="container">
    <div class="me-login">
        <div class="text-center me-login-title">
            <span class="text-custom">MOSIDIAN</span><span class="text-inverse"></span>
            <h5 class="me-login-subtitle">会员管理系统</h5>
        </div>
        <div class="card">
            <div class="container-fluid me-p-30">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item me-login-underline">
                        <label for="me-login-click">用户名</label>
                        <div class="input-group mb-3 me-login-input">
                            <input type="text" id="username" class="form-control" placeholder="用户名" aria-describedby="basic-addon2" value="">
                            <div class="input-group-append">
                                <button class="btn" type="button"><img src="img/login.svg"/></button>
                            </div>
                        </div>
                    </li>
                    <li class="list-group-item me-login-underline">
                        <label for="me-login-click-password">密码</label>
                        <div class="input-group mb-2 me-login-input">
                            <input id="userpwd" type="password" class="form-control " placeholder="密码" aria-describedby="basic-addon2" value="">
                            <div class="input-group-append">
                                <button class="btn" type="button"><img src="img/pass.png"/></button>
                            </div>
                        </div>
                    </li>
                    <li class="list-group-item me-login-button">
                        <button id="login" type="button" class="btn btn-primary btn-lg btn-block" onclick="check()">登录</button>
                    </li>
                    <li class="list-group-item me-login-member">
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 text-center">
            <p class="text-muted">MOSIDIAN 版权所有</p>
        </div>
    </div>

</div>
<div class="text-center me-login-clause">
    <p class="me-login-low"></p>
</div>

</body>



<script type="text/javascript">
    $("#login").click(function () {
        var username = $("#username").val();
        var userpwd = $("#userpwd").val();
        dolphin.post('login',{username:username,password:userpwd},function(result) {
            if(result.status == '1'){
                if (result.data.isLogin==0){
                    location.href='index-check'
                } else {
                    if (result.data.roleId=="1"){
                        location.href='index-member'
                    }else if (result.data.roleId=="2") {
                        location.href='index-user'
                    }else if (result.data.roleId=="3") {
                        location.href='index-express'
                    }else if (result.data.roleId=="4") {
                        location.href='index-admin'
                    }
                }
            }
            else{
                dolphin.msg(result.message);
            }
        })

    })

    function check() {
        var formname=document.loginForm;
        formname.submit();
    }
    //回车时，默认是登陆
    function on_return(){
        if(window.event.keyCode == 13){
            if (document.all('login')!=null){
                document.all('login').click();
            }
        }
    }


</script>


</html>