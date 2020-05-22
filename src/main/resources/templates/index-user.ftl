<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>会员管理系统-C端</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"/script>
    <script src="/js/macroease.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/jquery-3.4.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/layui/lay/modules/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/dolphin.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/css/macroease.css"/>
    <link rel="stylesheet" type="text/css" href="/layui/css/modules/layer/default/layer.css"/>
    <link rel="stylesheet" type="text/css" href="/layui/css/modules/laydate/default/laydate.css"/>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/iconfont/iconfont.css"/>
    <style>
        body {
            overflow: hidden;
            width: 100%;
            height: 100%;
        }

        .main-container {
            width: 100%;
            height: 100%;
        }

    </style>
</head>
<body>
<!--导航栏-->
<div class="main-container">
    <div class="me-header main-header">
        <div class="me-navbar">
            <div class="container">
                <nav class="navbar  navbar-expand-lg navbar-light">
                    <div class="navbar-brand" href="#"><img src="img/logo.png" width="100px"/><span style="color: white"> 会员管理系统-C端</span>
                    </div>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <!--ul保留-->
                        </ul>
                        <form class="form-inline my-2 my-lg-0">
                            <ul class="navbar-nav navbar-nav-right mr-0 ml-auto">
                                <li class="nav-item nav-profile dropdown">
                                    <a class="nav-link dropdown-toggle" style="color: #FFFFFF;" href="#"
                                       data-toggle="dropdown" id="profileDropdown" aria-expanded="false">
                                        <img class="me-avatar" src="img/face1.jpg" alt="profile">

                                            <span class="nav-profile-name">${username!}
                                           </span>

                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right navbar-dropdown"
                                         aria-labelledby="profileDropdown">
                                        <a class="dropdown-item index">
                                            <i class="icon-settings text-primary mr-2"></i>首页
                                        </a>
                                        <a class="dropdown-item editpass">
                                            <i class="icon-settings text-primary mr-2"></i>修改密码
                                        </a>
                                        <div class="dropdown-divider"></div>
                                        <a href="/loginOut" class="dropdown-item">
                                            <i class="icon-logout text-primary mr-2" onclick="clearCookie('Authorization')"> 退出</i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </form>
                    </div>
                </nav>
            </div>
        </div>
        <div class="me-menu">
            <div class="container">
                <ul class="me-menu-item">
                    <li>
                        <a href="/memberConsumer/showAllConsumer" target="modulecontent"><i class="iconfont icongongzuotai1"></i>&nbsp工作台</a>
                    </li>
                    <li>
                        <a href="#"><i class="iconfont iconyuyue"></i>&nbsp公司<i class="iconfont iconicon-2"></i> </a>
                        <ul>
                            <li>
                                <a href="/memberPlaceReserve/placeReserveSchedule" target="modulecontent"><i
                                            class="iconfont iconinsta360logo10"></i>公司管理</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="iconfont iconhuiyuan1" style="font-size:18px"></i>&nbsp用户<i
                                    class="iconfont iconicon-2"></i></a>
                        <ul>
                            <li>
                                <a href="/user/list" target="modulecontent"><i
                                            class="iconfont iconinsta360logo10"></i>用户管理</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="iconfont iconhuiyuan1" style="font-size:18px"></i>&nbsp物流<i
                                class="iconfont iconicon-2"></i></a>
                        <ul>
                            <li>
                                <a href="/member/list" target="modulecontent"><i
                                        class="iconfont iconinsta360logo10"></i>物流管理</a>
                            </li>
                        </ul>
                    </li>

                   <li>
                        <a href="javascript:void(0)"><i class="iconfont iconicon-test" style="font-size:18px"></i>&nbsp反馈<i
                                    class="iconfont iconicon-2"></i></a>
                        <ul>

                            <li>
                                <a href="/contactUs/list" target="modulecontent"><i
                                            class="iconfont iconinsta360logo10"></i>反馈列表</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="page-content" id="content">
        <iframe id="modulecontent" name="modulecontent" class="me-iframe main-content" <#--src="dashboard"-->
                src="/memberConsumer/showAllConsumer" width="100%"
                height="100%" frameborder="0"
                scrolling="auto"></iframe>
    </div>
</div>
<script type="text/javascript">
    var headerheight = 120;
    var winHeight = $(window).height();
    $(function () {

        $(".editpass").click(function () {
            dolphin.iframe('/editpassword', '修改密码', '', '');
        })

        $(".index").click(function () {
            dolphin.full('/',"首页");
        })

        $content = $("#content");
        $nav_wraper = $("#me-header");
        $content.height(winHeight - headerheight - 40);

        $(window).resize(function () {
            $content.height(winHeight - headerheight - 40);
        });
    })
    // 清除cookie
    function clearCookie(name) {
        setCookie(name, "", -1);
    }
</script>
</body>
</html>