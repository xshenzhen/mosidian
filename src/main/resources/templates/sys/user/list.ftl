<#import "/common/mepaging.ftl" as mepaging/>
<#include "/common/header.ftl">
<title>用户列表</title>
</head>
<body>
<div class="container-fluid">
    <div class="card">
        <div class="container-fluid ">
            <form action="/user/list" method="get">
                <div class="btn-toolbar justify-content-between me-btn-toolbar" role="toolbar"
                     aria-label="Toolbar with button groups">
                    <div class="btn-group me-button-group " role="group" aria-label="First group">
                        <div class="me-button-group">
                            <button type="button" class="btn btn-outline-primary add">添加</button>
                        </div>
                    </div>
                    <div class="input-group">
                        <input type="text" name="name" value="${pd.username!}" class="form-control" placeholder="用户姓名"
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
                    <th scope="col">用户名称</th>
                    <th scope="col">手机号</th>
                    <th scope="col">操作</th>

                </tr>
            <#list userList.records as user>
                <tr>
                    <td>${user_index+1!}</td>
                    <td>${user.username!}</td>
                    <td>${user.phone!}</td>
                    <td>
                        <button type="button" class="btn btn-outline-primary btn-sm edit" data-value="${user.id!}">
                            编辑
                        </button>
                        <button type="button" class="btn btn-outline-primary btn-sm delete" data-value="${user.id!}">
                           删除
                        </button>

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

            $(".add").click(function () {
                dolphin.iframe('/user/edit?id=0', '用户信息', '', '');
            })


            $(".delete").click(function () {
                var userid = $(this).data("value");
                if (dolphin.toInt(userid) <= 0) {
                    dolphin.msg("请选择要删除的用户！");
                    return;
                }

                dolphin.confirm(dolphin.confirmDel, function () {
                    dolphin.post('/user/delete?id=' + userid, {}, function (result) {
                        if (result.status == 1) {
                            dolphin.alertto(result.info, location.href);
                        } else {
                            dolphin.alert(result.info);
                        }
                    })
                })
            })


            $(".edit").click(function () {
                var userid = $(this).data("value");
                dolphin.iframe('/user/edit?id=' + userid, '用户信息', '', '');
            })
        })
    </script>
</body>
</html>
