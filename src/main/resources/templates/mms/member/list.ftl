<#import "/common/mepaging.ftl" as mepaging/>
<#include "/common/header.ftl">
<title>会员列表</title>
</head>
<body>
<div class="container-fluid">
    <div class="card">
        <div class="container-fluid ">
            <form action="/member/list" method="get">
                <div class="btn-toolbar justify-content-between me-btn-toolbar" role="toolbar"
                     aria-label="Toolbar with button groups">
                    <div class="btn-group me-button-group " role="group" aria-label="First group">
                        <div class="me-button-group">
                            <button type="button" class="btn btn-outline-primary add">添加</button>
                        </div>
                    </div>
                    <div class="input-group">
                        <input type="text" name="name" value="${pd.name!}" class="form-control" placeholder="会员姓名"
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
                    <th scope="col">卡号</th>
                    <th scope="col">会员名</th>
                    <th scope="col">操作</th>

                </tr>
            <#list memberList.records as member>
                <tr>
                    <td>${member_index+1!}</td>
                    <td>${member.no!}</td>
                    <td>${member.name!}</td>
                    <td>
                        <button type="button" class="btn btn-outline-primary btn-sm edit" data-value="${member.id!}">
                            编辑
                        </button>
                        <button type="button" class="btn btn-outline-primary btn-sm delete" data-value="${member.id!}">
                           删除
                        </button>

                    </td>
                </tr>
            </#list>
            </table>
        <@mepaging.paging pagingList=memberList url="./list" parameterMap=pd/>

        </div>
    </div>
<#include "/common/footer.ftl">
    <script type="text/javascript">
        $(function () {

            $(".add").click(function () {
                dolphin.iframe('/member/edit?id=0', '会员信息', '', '');
            })


            $(".delete").click(function () {
                var memberid = $(this).data("value");
                if (dolphin.toInt(memberid) <= 0) {
                    dolphin.msg("请选择要删除的用户！");
                    return;
                }

                dolphin.confirm(dolphin.confirmDel, function () {
                    dolphin.post('/member/delete?id=' + memberid, {}, function (result) {
                        if (result.status == 1) {
                            dolphin.alertto(result.info, location.href);
                        } else {
                            dolphin.alert(result.info);
                        }
                    })
                })
            })


            $(".edit").click(function () {
                var memberid = $(this).data("value");
                dolphin.iframe('/member/edit?id=' + memberid, '会员信息', '', '');
            })
        })
    </script>
</body>
</html>
