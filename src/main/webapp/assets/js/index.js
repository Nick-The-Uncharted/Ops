$(document).ready(function () {

    var cache = {
        userList: []
    };

    $.ajax({
        type: 'GET',
        url: $('#prefixUrl').val() + '/api/user/getAllUsers',
        success: function(ret) {
            if (ret.code == 0) {
                if (ret.data) {
                    for (var i = 0, one; one = ret.data[i]; i++) {
                        cache.userList.push({
                            id: one.id,
                            text: one.username
                        });
                    }
                    $('#js-select-user').select2({
                        language:"zh-CN",
                        data: cache.userList
                    });
                }
            } else {
                toaster(ret.msg || '系统繁忙', 'error');
            }
        },
        error: function() {
            toaster('系统繁忙', "error");
        }
    });

    $('#js-modal-project').modal({
        show: false,
        backdrop: 'static'
    });

    $('#js-btn-add').on('click', function () {
        $('#js-input-name').val('');
        $('#js-textarea-description').val('');
        $('#js-select-user').val(null).trigger('change');
        $('#js-modal-project').modal('show');
    });

    $('#js-btn-add-submit').on('click', function () {
        var data = {
            name: $('#js-input-name').val(),
            description: $('#js-textarea-description').val(),
            users: $('#js-select-user').val() || []
        };
        $.ajax({
            type: 'POST',
            url: $('#prefixUrl').val() + '/api/project/add',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('创建成功', 'success');
                    setTimeout(function () {
                        window.location.href = $('#prefixUrl').val() + '/project/' + ret.data.id;
                    }, 500);
                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    });

    getMyProjects();

    var projectTmpl = [
        '<div class="row">',
        '<%for(var i = 0, one; one = data[i]; i++){%>',
        '<div class="col-md-3">',
        '<div class="thumbnail" data-id="<%=one.id%>"><%=one.name%></div>',
        '</div>',
        '<%}%>',
        '</div>'
    ].join('');

    function getMyProjects() {
        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/project/getMyProjects',
            success: function(ret) {
                if (ret.code == 0) {

                    var list1 = ret.data.create;

                    if (list1.length == 0) {
                        $('#js-create').html('无');
                    } else {
                        $('#js-create').html(window.tmpl(projectTmpl, {
                            data : list1
                        }));
                    }

                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    }
    
    $('body').delegate('.thumbnail', 'click', function () {
        window.location.href = $('#prefixUrl').val() + '/project/' + $(this).attr('data-id');
    });
});