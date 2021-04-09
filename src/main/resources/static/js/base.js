$(function () {
    let windowHeight = $(window).height();
    let mainHeight = parseInt($('.el-main').css('height').replace("px", "")) + 60;
    $('.el-aside').height(Math.max(windowHeight, mainHeight));
})

var ROOT_URL = "http://localhost:9999"
