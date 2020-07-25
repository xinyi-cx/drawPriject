$(function () {
    setTimeout(function () {
        setInterval(setRoll, 8000);
    }, 8000);

    $("#butten").click(function () {
        login();
    })

    //回车登陆
    $("body").bind('keydown', function (event) {
        if (event.keyCode === 13) {
            login();
        }
    })
});

function login() {
    let msg = $('#msgSpan');
    //获取登录账号(去空格)
    let account = $('#userName');
    let userId2 = $('#userId');
    let userName = $.trim(account.val());
    let userId = $.trim(userId2.val());

    if (isNullOrEmpty(userName)) {
        msg.text("账号不能为空");
        msg.show();
        return false;
    }
    if (isNullOrEmpty(passWord)) {
        msg.text("密码不能为空");
        msg.show();
        return false;
    }

    $("#loading").show();
    // 遮罩层180s还未隐藏则自动隐藏
    setTimeout(function () {
        $('#loading').hide();
    }, 180000);
    //进行登录操作
    $.ajax({
        url: "/tt-manage/userInfo/login",
        data: {userName: userName, userId: userId},
        dataType: "json",
        type: "post",
        success: function (re) {
            let data = re.data;
            if (data["flag"] === "success") {
                // data["ckjgid"];
            }
            if (data["flag"] === "fail") {
                $("#loading").hide();
                msg.text(data["msg"]);
                msg.show();
                return false;
            }
        }
    });
}

function isNullOrEmpty(data) {
    if (data == "" || data == undefined || data == 'undefined' || data == null || data == "null") {
        return true;
    } else {
        return false;
    }
}
