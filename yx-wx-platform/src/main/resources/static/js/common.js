// 设置iframe高度自适应
function setIframeHeight() {
    var iframe;
    if ($(window.parent.document).find('#right-content')) {
        iframe = $(window.parent.document).find('#right-content');
    } else if ($('#right-content')) {
        iframe = $('#right-content');
    }
    if (iframe) {
        iframe.height(iframe.contents().find('body').height() + 30);
    }
}

$.ajaxSetup({
    complete: function (xhr, status) {
        setIframeHeight();
    }
});

function setIframeSrc(url) {
    var iframe = $(window.parent.document).find('#right-content');
    if (url) {
        iframe.attr('src', url);
    }
}

// 计算分页
function setPagination(pageInfo, pagination, methodName) {
    pagination.find('li').remove();
    var firstPage = 1;
    var lastPage = pageInfo.pages;
    var isFirstPage = pageInfo.isFirstPage;
    var isLastPage = pageInfo.isLastPage;
    var navigatePageNums = pageInfo.navigatepageNums;
    var pageNum = pageInfo.pageNum;
    var pageSize = pageInfo.pageSize;

    if (isFirstPage) {
        pagination.append('<li class="am-disabled"><a href="javascript:;">«</a></li>')
    } else {
        pagination.append('<li><a href="javascript:;" onclick="' + methodName + '(1,' + pageSize + ');">«</a></li>')
    }
    if (navigatePageNums) {
        for (var i = 0; i < navigatePageNums.length; i++) {
            if (navigatePageNums[i] == pageNum) {
                pagination.append('<li class="am-active"><a href="javascript:;">' + navigatePageNums[i] + '</a></li>');
            } else {
                pagination.append('<li><a href="javascript:;" onclick="' + methodName + '(' + navigatePageNums[i] + ',' + pageSize + ');">' + navigatePageNums[i] + '</a></li>');
            }
        }
    }
    if (isLastPage) {
        pagination.append('<li class="am-disabled"><a href="javascript:;">»</a></li>')
    } else {
        pagination.append('<li><a href="javascript:;" onclick="' + methodName + '(' + lastPage + ',' + pageSize + ');">»</a></li>')
    }
}