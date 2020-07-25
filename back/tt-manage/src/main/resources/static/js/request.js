/**
 * ajax post提交
 * @param url
 * @param param
 * @param datat 为html,json,text
 * @param async 是否异步加载 true是 false否
 * @param isLoading 是否加载Loding等待效果 true是 false否
 * @param successCallback 成功回调函数
 * @param errorCallback 失败回调函数
 * @param loadingTarget 加载Loding等待效果目标位置，默认为id为main的div
 * @return
 */
function sendAjax(url, param, datat, async, isLoading, successCallback, errorCallback, loadingTarget) {
    loadingTarget = $.trim(loadingTarget) ? loadingTarget : "main";
    let errorFn = $.trim(errorCallback) ? errorCallback : todoError;
    let scysAjax = $.ajax({
        type: "post",
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        //headers: {"Content-Type": "application/json;charset=UTF-8","apikey":"wz9DdFJ1Csi7ed906by1vtpMsQdB8rRH"},
        url: url,
        data: JSON.stringify(param),
        timeout: 60000, //超时时间 1min
        dataType: datat,
        async: async,
        success: function (reponse) {
            if (reponse.code === 200) {
                successCallback(reponse);
            } else {
                // TODO 其他返回码的处理
            }
        },
        beforeSend: function (XMLHttpRequest) {
            if (isLoading) {
                if (loadingTarget === "main") {
                    $("#loading").show();
                } else {
                    easyUILoad(loadingTarget);
                }
            }
        },
        complete:
            function (XMLHttpRequest, textStatus) {
                if (loadingTarget !== "main" && $("#" + loadingTarget)) {
                    dispalyEasyUILoad(loadingTarget);
                }
                if ($("#loading")) {
                    $("#loading").hide();
                }
                if (textStatus === 'timeout') {//超时,status还有success,error等值的情况
                    console.log("请求"+url+"超时！");
                    scysAjax.abort();
                    errorFn;
                }
            },
        error: errorFn
    });
}

function todoError() {
}