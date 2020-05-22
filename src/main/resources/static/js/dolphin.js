var dolphin = {
    confirmDel:'确认要删除吗？',
    //替换所有的回车换行
    replaceEnter: function (content) {
        var result = '';
        if (content) {
            try {
                result = content.replace(/\r\n/g, "</br>");
                result = result.replace(/\n/g, "</br>");
                result = result.replace(/\n/g, "</br>");
                result = result.replace(/\s/g, "&nbsp;");
            } catch (e) {
                result = '';
            }
        }
        return result;
    },
    getColor: function () {
        var colors = [
            '#2EBAEE',
            '#42a5f5',
            '#00c5dc',
            '#feb38d',
            '#EE6E73',
            '#6B79C4',
            '#EE6E73',
            '#6B79C4',
            '#28bebd',
            '#9e9e9e'
        ];
        var random = Math.floor(Math.random() * colors.length);
        return colors[random];
    },
    upperFirst: function (value) {
        var tempFirst = value.substring(0, 1);
        var tempElse = value.substring(1, value.length);
        return (tempFirst.toUpperCase() + tempElse);
    },
    iframe: function (url, title, width, height, closeBtn, buttons, callbackConfirm, callbackCancel) {
        if (!width) {
            width = '90%';
        }
        if (!height) {
            height = '80%';
        }
        if (isNaN(closeBtn)) {
            closeBtn = 1;
        }

        if (this.isEmpty(title)) {
            title = 'E+';
        }
        var layerOption = {
            type: 2,
            title: title,
            shadeClose: false,
            shade: 0.8,
            anim: 2,
            closeBtn: closeBtn,
            area: [
                width, height
            ],
            content: url
        }
        if (buttons) {
            layerOption["btn"] = buttons
        }
        if (callbackConfirm && typeof callbackConfirm == "function") {
            layerOption["yes"] = callbackConfirm
        }
        if (callbackCancel && typeof callbackCancel == "function") {
            layerOption["btn2"] = callbackCancel
        }
        var index = layer.open(layerOption);
        return index;
    },
    full: function (url, title, width, height, closeBtn) {
        if (!width) {
            width = '100%';
        }
        if (!height) {
            height = '100%';
        }
        if (!closeBtn)
            closeBtn = 1;
        var index = layer.open({
            type: 2,
            title: title,
            anim: 2,
            shadeClose: true,
            shade: 0.8,
            closeBtn: closeBtn,
            maxmin: true, //开启最大化最小化按钮
            area: [
                width, height
            ],
            content: url
        });
        return index;
    },
    open: function (url, title, width, height, closeBtn) {
        if (!width) {
            width = '1000px';
        }
        if (!height) {
            height = '80%';
        }
        if (!closeBtn)
            closeBtn = 1;
        var index = layer.open({
            type: 2,
            title: title,
            anim: 2,
            shadeClose: true,
            closeBtn: closeBtn,
            shade: 0.8,
            maxmin: true, //开启最大化最小化按钮
            area: [
                width, height
            ],
            content: url
        });
        return index;
    },
    layerCheck: function (msg) {
        window.location.href = '/notice1001';
    },
    alert: function (msg) {
        layer.open({ title: '提示', content: msg });
    },
    alertto: function (msg, url, btnName) {
        if (btnName) { } else {
            btnName = '确定';
        }
        layer
            .confirm(msg, {
                btn: [btnName]
            }, function () {
                if (url == '') {
                    location.href = location.href;
                } else {
                    location.href = url;
                }
            })
    },
    call: function (msg, callBack, btnName) {
        if (btnName) { } else {
            btnName = '确定';
        }
        layer
            .confirm(msg, {
                btn: [btnName]
            }, function () {
                callBack();
            })
    },
    msg: function (msg) {
        layer.msg(msg, { offset: 't' });
    },
    layerClose: function (index) {
        if (index) {
            layer.close(index);
        } else {
            index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    },
    layerIndex: function () {
        return parent
            .layer
            .getFrameIndex(window.name);
    },
    confirm: function (content, yesFunc, cancelFuc, yes, cancel, options) {
        if (!options) {
            options = {
                icon: 3,
                title: '提示'
            };
        }
        //      layer.confirm(content, options, yes, cancel);
        if (yes) {
            yes = yes
        } else {
            yes = "确定"
        }
        if (cancel) {
            cancel = cancel
        } else {
            cancel = "取消"
        }

        layer
            .confirm(content, {
                btn: [yes, cancel] //按钮
            }, function () {
                if (yesFunc) {
                    yesFunc();
                    layer.closeAll('dialog');
                }
            }, function () {
                if (cancelFuc) {
                    cancelFuc();
                }
            });
    },
    layerName: function () {
        return 'layui-layer-iframe';
    },
    //内部打开窗口
    openData: function (vfrom, vtype, vkey, vrelate, vmodule, vwheretype, parentIndex, title) {
        if (this.isEmpty(title)) {
            title = '筛选';
        }
        var iframe1 = '';
        if (parentIndex) {
            iframe1 = dolphin.layerName() + parentIndex;
        }
        var url = '/frame/module/select?om=3&vfrom=' + vfrom + '&viewtype=' + vtype + '&vkey=' + vkey + '&vrelate=' + vrelate + '&vmodule=' + vmodule + '&vwheretype=' + vwheretype + '&piframe=' + iframe1;
        dolphin.iframe(url, title, '900px', '');
    },
    openUser: function () {
        dolphin.openData('Common', 'Employee', 'Employee');
    },
    openUsers: function (names, ids, parentIndex) {
        names = encodeURI(names);
        ids = encodeURI(ids);
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = "/frame/org/users?om=3&names=" + names + "&values=" + ids + "&piframe=" + iframe;
        this.iframe(url, '选择用户', '750px', '500px');
    },
    openDept: function (vfrom, vtype, vkey, vrelate, vselectedids, vallowcheck, parentIndex) {
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = '/frame/org/dept?om=3&vfrom=' + vfrom + '&viewtype=' + vtype + '&vkey=' + vkey + '&vrelate=' + vrelate + '&selectedids=' + vselectedids + '&vallowcheck=' + vallowcheck + "&piframe=" + iframe;
        this.iframe(url, '选择部门', '650px', '500px');
    },
    openDepts: function (vfrom, vtype, vkey, vrelate, vselectedids, vallowcheck, parentIndex) {
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = '/frame/org/depts?om=3&vfrom=' + vfrom + '&viewtype=' + vtype + '&vkey=' + vkey + '&vrelate=' + vrelate + '&selectedids=' + vselectedids + '&vallowcheck=' + vallowcheck + "&piframe=" + iframe;
        this.iframe(url, '选择部门', '650px', '500px');
    },
    openRoles: function (vkey, vselecteds, parentIndex) {
        var iframe = dolphin.layerName() + parentIndex;
        var url = '/sm/role/select?vkey=' + vkey + '&vselecteds=' + vselecteds + "&om=3&piframe=" + iframe;
        this.iframe(url, '选择角色');
    },
    openJob: function (vkey, vselectedId, parentIndex) {
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = '/frame/module/selectjob?vkey=' + vkey + '&vselectedId=' + vselectedId + "&om=3&piframe=" + iframe;
        this.iframe(url, '选择职务');
    },
    openJobs: function (vkey, vselecteds, parentIndex) {
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = '/frame/module/selectjobs?vkey=' + vkey + '&vselectedids=' + vselecteds + "&om=3&piframe=" + iframe;
        this.iframe(url, '选择职务');
    },
    openPost: function (vkey, vselectedId, parentIndex) {
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = '/frame/module/SelectPosition?vkey=' + vkey + '&vselectedId=' + vselectedId + "&om=3&piframe=" + iframe;
        this.iframe(url, '选择岗位');
    },
    openPosts: function (vkey, vselecteds, parentIndex) {
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = '/frame/module/SelectPositions?vkey=' + vkey + '&vselectedids=' + vselecteds + "&om=3&piframe=" + iframe;
        this.iframe(url, '选择岗位');
    },
    openSubjectSigle: function (type, parentIndex) {
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = "/agility/subject/single?type=" + type + "&piframe=" + iframe;
        this.iframe(url, '选择看板', '650px', '500px');
    },
    openNewsCategory: function (vkey, vselectedId, parentIndex) {
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = '/frame/module/selectnewscategory?vkey=' + vkey + '&vselectedId=' + vselectedId + "&piframe=" + iframe;
        this.iframe(url, '选择板块', '650px', '500px');
    },
    openFormCorpDiskFile: function () {
        var url = '/netdisk/pages/corpdiskfile_selectconfig_dialog.aspx';
        return this.iframe(url, '选择资源', '650px', '500px');
    },
    openProduct: function (type) {
        var url = '/frame/product/select?type=' + type;
        return this.full(url, '选择产品');
    },
    openDictionary: function (vkey, formsid, parentIndex) {
        var iframe = '';
        if (parentIndex) {
            iframe = dolphin.layerName() + parentIndex;
        }
        var url = '/workflow/dictionary/select?om=3&formsid=' + formsid + '&vkey=' + vkey  + "&piframe=" + iframe;
        this.iframe(url, '选择字典', '650px', '500px');
    },
    getVal: function (obj, ret) {
        if (!this.isEmpty(obj)) {
            return obj;
        } else {
            return ret;
        }
    },
    getVal2: function (obj) {
        if (!this.isEmpty(obj)) {
            return obj;
        } else {
            return '';
        }
    },
    isEmpty: function (obj) {
        if (obj && obj != ''&& obj != ' ' && obj != 'null' && obj != 'undefined') {
            return false;
        } else {
            return true;
        }
    },
    isArray: function (value) {
        var ret = false;
        if (value instanceof Array || (!(value instanceof Object) && (Object.prototype.toString.call((value)) == '[object Array]') || typeof value.length == 'number' && typeof value.splice != 'undefined' && typeof value.propertyIsEnumerable != 'undefined' && !value.propertyIsEnumerable('splice'))) {
            ret = true;
        }
        return ret;
    },
    getPara: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window
            .location
            .search
            .substr(1)
            .match(reg);
        if (r != null)
            return unescape(this.getVal(r[2], ''));
        return '';
    },
    getJson: function (viewData) {
        var data = viewData;
        if (this.isEmpty(data)) {
            return [];
        } else {
            var reg = new RegExp("&quot;", "g");
            data = JSON.parse(data.replace(reg, '"'));
        }
        return data;
    },
    round: function (srcStr, nAfterDot) {
        var srcStr,
            nAfterDot;
        var resultStr,
            nTen;
        srcStr = "" + srcStr + "";
        strLen = srcStr.length;
        dotPos = srcStr.indexOf(".", 0);
        if (dotPos == -1) {
            resultStr = srcStr + ".";
            for (i = 0; i < nAfterDot; i++) {
                resultStr = resultStr + "0";
            }
        } else {
            if ((strLen - dotPos - 1) >= nAfterDot) {
                nAfter = dotPos + nAfterDot + 1;
                nTen = 1;
                for (j = 0; j < nAfterDot; j++) {
                    nTen = nTen * 10;
                }
                resultStr = Math.round(parseFloat(srcStr) * nTen) / nTen;
            } else {
                resultStr = srcStr;
                for (i = 0; i < (nAfterDot - strLen + dotPos + 1); i++) {
                    resultStr = resultStr + "0";
                }
            }
        }

        return resultStr;
    },
    subdate: function (value) {
        if (dolphin.isEmpty(value)) {
            return '';
        } else {
            var date = new Date(value);
            if (!isNaN(date)) {
                return date.toFormat('yyyy-MM-dd');
            } else {
                return '';
            }
        }
    },
    download: function (url, data, method) {
        // 获得url和data
        if (url && data) {
            // data 是 string 或者 array/object
            data = typeof data == 'string'
                ? data
                : $.param(data);
            // 把参数组装成 form的  input
            var inputs = '';
            var _form = $("<form>");
            _form.attr("style", "display:none");
            _form.attr("target", "");
            _form.attr("action", url);
            _form.attr("method", (method || 'post'));
            $.each(data.split('&'), function () {
                var pair = this.split('=');
                var _input = $("<input>");
                _input.attr("type", "hidden");
                _input.attr("name", pair[0]);
                _input.attr("value", pair[1]);
                _form.append(_input);
            });
            _form
                .submit()
                .remove();
        };
    },
    download1: function (url) {
        var form = $("<form>"); //定义一个form表单
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "get");
        form.attr("action", url);
        var input1 = $("<input>");
        input1.attr("type", "hidden");
        input1.attr("name", "exportData");
        input1.attr("value", (new Date()).getMilliseconds());
        $("body").append(form); //将表单放置在web中
    },

    get: function (url, data, callback) {
        if (typeof data == "function") {
            callback = data
            data = {}
        }
        $.ajax({
            url: url, type: "get",
            //contentType: "application/json",
            dataType: "json",
            timeout: 10000,
            data: data,
            success: function (result) {
                if (result.Check) {
                    dolphin.layerCheck(result.Msg);
                } else {
                    callback(result);
                }
            }
        });
    },
    post: function (url, data, callback) {
        $.ajax({
            url: url, type: "post",
            //contentType: "application/json",
            dataType: "json",
            data: data,
            timeout: 60000,
            success: function (result) {
                if (result.Check) {
                    dolphin.layerCheck(result.Msg);
                } else {
                    callback(result);
                }
            },
            error: function (xhr, textstatus, thrown) { }
        });
    },
    //滤掉两边空格
    trim: function (sValue) {
        if (sValue == null) {
            return "";
        }
        sValue = sValue + "";
        return sValue.trim();
    },
    toInt: function (sValue) {
        var iRet = dolphin.trim(sValue);
        if (iRet.charAt(0) == "0")
            iRet = iRet.substring(1, iRet.length);
        iRet = parseInt(iRet);
        if (isNaN(iRet)) {
            iRet = 0;
        }
        return iRet;
    },
    isNullDate: function (value) {
        var result = false;
        if (value == null) {
            return true;
        }
        if (typeof value === 'object') {
            return result;
        }
        if (value && value.indexOf('Date') >= 0) {
            value = this.dateFormat(value, 'yyyy-MM-dd');
        }
        if (this.isEmpty(value) || value.indexOf('1753-01-01') >= 0 || value.indexOf('0000') >= 0 || value.indexOf('0001') >= 0 || value.indexOf('NaN') >= 0) {
            result = true;
        }
        return result;
    },
    dateFormat: function (value, fmt) {
        if (this.isEmpty(value)) {
            return '';
        }
        if (this.isNullDate(value)) {
            return '';
        }

        var date;
        if (value.indexOf && value.indexOf('Date') && value.indexOf('Date') >= 0) {
            date = new Date(parseInt(value.replace("/Date(", "").replace(")/", ""), 10));
        } else {
            if (value.replace) {
                if (value.indexOf('T') > 0) {
                    date = value.replace('T', ' ');
                    date = new Date(date);
                } else {
                    date = new Date(Date.parse(value.replace(/-/g, "/")));
                }
            } else {
                date = new Date(value);
            }
        }

        var month = date.getMonth() + 1 < 10
            ? "0" + (date.getMonth() + 1)
            : date.getMonth() + 1;
        var currentDate = date.getDate() < 10
            ? "0" + date.getDate()
            : date.getDate();
        var Hours = date.getHours() < 10
            ? "0" + date.getHours()
            : date.getHours();
        var Minutes = date.getMinutes() < 10
            ? "0" + date.getMinutes()
            : date.getMinutes();
        var Seconds = date.getSeconds() < 10
            ? "0" + date.getSeconds()
            : date.getSeconds();
        var result = ''
        if (fmt == 'yyyy-MM-dd') {
            result = date.getFullYear() + "-" + month + "-" + currentDate;
        } else if (fmt == 'yyyy-MM-dd HH:mm') {
            if (Hours == '00' && Minutes == '00') {
                result = date.getFullYear() + "-" + month + "-" + currentDate;
            } else {
                result = date.getFullYear() + "-" + month + "-" + currentDate + " " + Hours + ":" + Minutes;
            }
        } else if (fmt == 'yyyy-MM-dd HH:mm:ss') {
            result = date.getFullYear() + "-" + month + "-" + currentDate + " " + Hours + ":" + Minutes + ":" + Seconds;
        }
        return result;
    },
    formatDate: function (value) {
        return this.dateFormat(value, 'yyyy-MM-dd');
    },
    formatTime: function (value) {
        return this.dateFormat(value, 'yyyy-MM-dd HH:mm');
    },
    formatSecond: function (value) {
        return this.dateFormat(value, 'yyyy-MM-dd HH:mm:ss');
    },
    //d1>=d2 返回0，d1<d2 返回1
    dateCompare: function (datetime1, datetime2) {
        if (datetime1 && datetime2) {
            var d1 = new Date(datetime1.replace(/\-/g, "\/"));
            var d2 = new Date(datetime2.replace(/\-/g, "\/"));
            return d1 >= d2
                ? 0
                : 1;
        } else {
            return -1;
        }

    },
    //d1>=d2 返回true
    dateGTCompare: function (datetime1, datetime2) {
        if (datetime1 && datetime2) {
            var d1 = new Date(datetime1.replace(/\-/g, "\/"));
            var d2 = new Date(datetime2.replace(/\-/g, "\/"));
            return d1 >= d2;
        } else {
            return -1;
        }
    },
    //是否在tab中打开的页面
    isTabWay: function () {
        var result = false;
        if (window.frameElement) {
            result = $(window.frameElement).hasClass("eplusIframe");
        }
        return result;
    },
    //截取字符串
    cutStr: function (str, len) {
        var str_length = 0;
        var str_len = 0;
        str_cut = new String();
        str_len = str.length;
        for (var i = 0; i < str_len; i++) {
            a = str.charAt(i);
            str_length++;
            if (escape(a).length > 4) {
                //中文字符的长度经编码之后大于4
                str_length++;
            }
            str_cut = str_cut.concat(a);
            if (str_length >= len) {
                str_cut = str_cut.concat("...");
                return str_cut;
            }
        }
        //如果给定字符串小于指定长度，则返回源字符串；
        if (str_length < len) {
            return str;
        }
    },
    fileSizeConver: function (limit) {
        var size = "";
        if (limit < 0.1 * 1024) { //如果小于0.1KB转化成B
            size = limit.toFixed(2) + "B";
        } else if (limit < 0.1 * 1024 * 1024) { //如果小于0.1MB转化成KB
            size = (limit / 1024).toFixed(2) + "KB";
        } else if (limit < 0.1 * 1024 * 1024 * 1024) { //如果小于0.1GB转化成MB
            size = (limit / (1024 * 1024)).toFixed(2) + "MB";
        } else { //其他转化成GB
            size = (limit / (1024 * 1024 * 1024)).toFixed(2) + "GB";
        }

        var sizestr = size + "";
        var len = sizestr.indexOf("\.");
        var dec = sizestr.substr(len + 1, 2);
        if (dec == "00") { //当小数点后为00时 去掉小数部分
            return sizestr.substring(0, len) + sizestr.substr(len + 3, 2);
        }
        return sizestr;
    },
    isPhone: function (str) {
        var myreg = /^[1][3,4,5,7,8,9][0-9]{9}$/;
        if (!myreg.test(str)) {
            return false;
        } else {
            return true;
        }
    },
    isEmail: function (str) {
        var myreg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
        if (!myreg.test(str)) {
            return false;
        } else {
            return true;
        }
    },
    single_image_upload: function (url, image, params, callback) {
        var formData = new FormData()
        formData.append("file", image[0])
        for (var key in params) {
            formData.append(key, params[key])
        }
        $.ajax({
            url: url,
            type: "POST",
            data: formData,
            xhr: function () {
                var myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) {
                    myXhr.upload.addEventListener("progress", function (response) {
                        if (response.lengthComputable) {
                            //此处渲染进度条
                        }
                    }, false)
                }
                return myXhr;
            },
            success: function(response) {
                response = JSON.parse(response);
                if (typeof callback === 'function') {
                    callback(response);
                }
            },
            contentType: false,
            processData: false
        });
    }
}

String.prototype.format = function () {
    var args = arguments;
    return this.replace(/\{(\d+)\}/g, function (s, i) {
        return args[i];
    });
}
Date.prototype.toFormat = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1)
                ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}
 
String.prototype.formatVal = function (args) {
    var result = this;
    if (arguments.length > 0) {
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if (args[key] != undefined) {
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    //var reg = new RegExp("({[" + i + "]})", "g");//这个在索引大于9时会有问题，谢谢何以笙箫的指出
                    var reg = new RegExp("({)" + i + "(})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}
 