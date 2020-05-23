<#import "/common/mepaging.ftl" as mepaging/>
<#include "/common/header.ftl">
<title>企业列表</title>
</head>
<body>
<div class="container-fluid">
    <div class="card">
        <div class="container-fluid ">
            <form action="/user/listCheck" method="get">
                <div class="btn-toolbar justify-content-between me-btn-toolbar" role="toolbar"
                     aria-label="Toolbar with button groups">
                    <div class="btn-group me-button-group " role="group" aria-label="First group">
                     <#--   <div class="me-button-group">
                            <button type="button" class="btn btn-outline-primary add">添加</button>
                        </div>-->
                    </div>
                    <div class="input-group">
                        <input type="text" name="username" value="${pd.username!}" class="form-control" placeholder="企业名称"
                               aria-label="Input group example"
                               aria-describedby="btnGroupAddon2">
                        &nbsp;&nbsp;
                        <div class="input-group-prepend">
                            <button type="submit" class="btn btn-outline-primary">查询</button>
                        </div>
                    </div>
                </div>
            </form>
            <table class="table table-hover me-m-t-20">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">企业名称</th>
                    <th scope="col">账号</th>
                    <th scope="col">联系方式</th>
                    <th scope="col">状态</th>
                    <th scope="col">审核回复</th>
                    <th scope="col">操作</th>
                </tr>
            <#list userList.records as user>
                <tr>
                    <td>${user_index+1!}</td>
                    <td>${user.memo!}</td>
                    <td>${user.username!}</td>
                    <td>${user.phone!}</td>
                    <#if user.status==0>
                         <td>待审核</td>
                    <#elseif user.status==1>
                         <td>审核通过</td>
                    <#elseif user.status==2>
                        <td>审核未通过</td>
                     <#else>
                        <td>已激活</td>
                    </#if>
                    <td>${user.message!}</td>
                    <td>
                    <#if user.status==1 && user.isLogin==0>
                           <button type="button" class="btn btn-outline-primary btn-sm activation" data-value="${user.id!}">
                               激活
                           </button>
                    </#if>
                    </td>
                </tr>

            </#list>
            </table>
        <@mepaging.paging pagingList=userList url="./list" parameterMap=pd/>

        </div>
    </div>
<#include "/common/footer.ftl">
    <script type="text/javascript">
        $(function () {

            $(".activation").click(function () {
                var userid = $(this).data("value");
                dolphin.post('/user/activation?id=' + userid ,"",
                        function (result) {
                            if (result.status == 1) {
                                layer.msg(result.info, {icon: 1, time: 500}, function () {
                                    parent.location.href = parent.location.href;
                                })
                            } else {
                                dolphin.alert(result.info);
                            }
                        })
            })
        })
    </script>
</body>
</html>
