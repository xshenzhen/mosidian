<#include "/common/header.ftl">
<title>企业审核</title>
</head>
<body>
<div class="container me-p-t-25">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">企业审核</h5>
            <form id="form">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label me-t-r"><span style="color: red">*</span>审核状态：</label>
                            <div class="col-sm-6">
                                <select class="form-control form-control-sm status" name="status">
                                    <option value="1">审核通过</option>
                                    <option value="2">审核未通过</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label me-t-r"><span style="color: red">*</span>审核回复：</label>
                        <div class="col-sm-9">
                            <input type="text" id="message"  value="" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="me-btn-toolbar text-center">
                    <button type="button" class="btn btn-primary mr-2 save">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
<#include "/common/footer.ftl">
<script type="text/javascript">


    var userid = '${user.id!}';

    $(function () {
        var status= $(".status").val();


        $(".save").click(function () {
            var message = $("#message").val();
            dolphin.post('/user/checkUserStatus?id=' + userid +"&status="+status+"&message="+message,"",
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