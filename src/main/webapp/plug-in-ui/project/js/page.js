//分页相关代码
//当前页码
var pageNo = [[${pageNo}]];
//当前页数
var pages = [[${pages}]];

var visiblePages = pages;
if (pages >= 10) {
    visiblePages = 10;
}
$.jqPaginator('#pagination1', {
    totalPages: pages,
    visiblePages: visiblePages,
    currentPage: pageNo,
    prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
    next: '<li class="next"><a href="javascript:;">下一页</a></li>',
    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
    first: '<li class="next"><a href="javascript:;">首页</a></li>',
    last: '<li class="next"><a href="javascript:;">末页</a></li>',
    onPageChange: function (num, type) {
        console.log("num : " + num);
        console.log("type : " + type);
        if (type != "init") {
            //$('#p1').text(type + '：' + num);
            document.getElementById('pageNo').value = num;
            document.getElementById('formSubmit').submit();
        }
    }
});