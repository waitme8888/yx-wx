$(document).ready(function () {
    getUsers();
});

function getUsers(pageNum, pageSize) {
    var url = '/admin/user/list';
    var searchType = $('#searchType').val();
    var searchValue = $('#searchValue').val();
    $.post(url, {
        'pageNum': pageNum,
        'pageSize': pageSize,
        'searchType': searchType,
        'searchValue': searchValue
    }, function (result) {
        if (result.success) {
            $('tbody').find('tr').remove();
            if (result.pageInfo) {
                var tbody = $('tbody');
                var data = result.pageInfo.list;
                for (var i = 0; i < data.length; i++) {
                    $('tbody').append('     <tr>'
                        + '     <td><input type="checkbox"/></td>'
                        + '     <td class="table-id">' + data[i]['id'] + '</td>'
                        + '     <td><a href="#">' + data[i]['username'] + '</a></td>'
                        + '     <td>' + data[i]['type'] + '</td>'
                        + '     <td>' + data[i]['realname'] + '</td>'
                        + '     <td>' + data[i]['qq'] + '</td>'
                        + '     <td>' + data[i]['email'] + '</td>'
                        + ' <td>' + data[i]['tel'] + '</td>'
                        + ' <td>'
                        + ' <div class="am-btn-toolbar">'
                        + '     <div class="am-btn-group am-btn-group-xs">'
                        + '     <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="editUser(' + data[i]['id'] + ');return false;">'
                        + '     <span class="am-icon-pencil-square-o"></span> 编辑</button>'
                        + '     <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="deleteUser(' + data[i]['id'] + ');return false;">'
                        + '     <span class="am-icon-trash-o"></span> 删除'
                        + '     </button>'
                        + '     </div>'
                        + '     </div>'
                        + '     </td>'
                        + '     </tr>'
                    );
                }

                setPagination(result.pageInfo, $('.am-pagination'), 'getUsers');
            }
        } else {
            alert(result.msg);
        }
    });
}

function deleteUser(id) {
    dialog.confirm({
        title: '信息提示',
        content: '您确认删除该用户信息吗？',
        onConfirm: function () {
            var url = '/admin/user/delete';
            $.post(url, {'id': id}, function (result) {
                if (result.success) {
                    dialog.alert({
                        title: '信息提示', content: "删除用户成功！", onConfirm: function () {
                            getUsers();
                        }
                    });
                } else {
                    dialog.alert({title: '错误提示', content: result.msg});
                }
            });
        },
        onCancel: function () {
            return;
        }
    });
}

function editUser(id) {
    var url = '/admin/user/' + id;
    setIframeSrc(url);
}

function addUser(id) {
    editUser(id);
}
