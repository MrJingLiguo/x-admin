$(function () {
    let windowHeight = $(window).height();
    let mainHeight = parseInt($('.el-main').css('height').replace("px", "")) + 60;
    $('.el-aside').height(Math.max(windowHeight, mainHeight));


    $.ajaxSettings.beforeSend = function(xhr,request){
        console.log( request.headers )
        // 在这里加上你的 token
        var user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
        xhr.setRequestHeader('token',user.token);
    }
})
