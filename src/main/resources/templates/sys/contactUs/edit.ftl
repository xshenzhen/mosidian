<#include "/common/header.ftl">
<title>消息列表</title>
</head>
<body>
<div class="container me-p-t-25">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">消息信息</h5>
            <form id="form">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label me-t-r"><span style="color: red">*</span>用户名称：</label>
                            <div class="col-sm-9">
                                <input type="text" name="contactUsname" id="name" value="${contactUs.name!}"
                                       class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label me-t-r"><span style="color: red">*</span>手机号：</label>
                            <div class="col-sm-9">
                                <input type="text" name="phone" id="phone" value="${contactUs.phone!}"
                                       data-value="no"   class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
                
              
                
               <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label me-t-r">个人描述：</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" rows="4"
                                          name="description">${contactUs.feedbackMessage!}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <#--<div class="me-btn-toolbar text-center">
                    <button type="button" class="btn btn-primary mr-2 save">保存</button>
                </div>-->
            </form>
        </div>
    </div>
</div>
<#include "/common/footer.ftl">
<script type="text/javascript">


    var contactUsid = '${contactUs.id!}';
    $(function () {
        function isPhoneNo(phone) {
            var pattern = /^1[6345789]\d{9}$/;
            return pattern.test(phone);
        }

        $(".save").click(function () {
           /// var phone = $("#phone").val();

           /* if (dolphin.isEmpty($("#name").val())) {
                dolphin.msg('请输入姓名！');
                return;
            }
            if (dolphin.isEmpty($("#memberscore").val())) {
                dolphin.msg('请输入信誉分！');
                return;
            }
            if (dolphin.isEmpty($("#sid").val())) {
                dolphin.msg('请输入企业微信账号！');
                return;
            }

           if ($("#grade").val()=="请选择") {
                dolphin.msg('请选择等级！');
                return;
            }

            if ($("#team").val()=="请选择") {
                dolphin.msg('请选择团队！');
                return;
            }

            if ($("#rank").val()=="请选择") {
                dolphin.msg('请选择头衔！');
                return;
            }

            if (phone == null || phone == "") {
                dolphin.msg("请输入手机号！");
                return;
            }


            if (!isPhoneNo(phone)) {
                dolphin.msg("请输入正确手机号！");
                return;
            }

            if (dolphin.isEmpty($("#no").val())) {
                dolphin.msg('请输入工号！');
                return;
            }*/

            dolphin.post('/contactUs/update?id=' + contactUsid,
                    $('#form').serialize(),
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