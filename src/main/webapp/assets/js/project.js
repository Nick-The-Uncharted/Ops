var myRisks = [];

$(document).ready(function () {

    var cache = {
        project: {},
        userList: [],
        projectUserList: [],
        options: {
            flag: 0
        },
        myTypeMap: {
            1: {text: '范围风险',},
            2: {text: '进度风险'},
            3: {text: '成本风险'},
            4: {text: '质量风险'},
            5: {text: '技术风险'},
            6: {text: '管理风险'},
            7: {text: '社会环境风险'},
        },
        typeMap: {
            1: {
                text: '低',
                type: 'default'
            },
            2: {
                text: '中',
                type: 'warning'
            },
            3: {
                text: '高',
                type: 'danger'
            },
        },
        typeList: [{val: 1, text: '低'}, {val: 2, text: '中'}, {val: 3, text: '高'}]
    };

    $('#js-modal-project').modal({
        show: false,
        backdrop: 'static'
    });

    $('#js-modal-risk').modal({
        show: false,
        backdrop: 'static'
    });

    loadProject();
    function loadProject() {
        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/project/getById?id=' + $('#js-pid').val(),
            success: function(ret) {
                if (ret.code == 0) {
                    if (ret.data) {
                        cache.project = ret.data;
                        $('title').text('项目[' + ret.data.name + '] - 软件项目风险管理系统');
                        $('#js-panel-intro *[data-item]').each(function () {
                            var item = $(this).attr('data-item');
                            var text = ret.data[item];
                            if (item == 'creator') {
                                var creator = ret.data.creator;
                                $(this).text(creator.username);
                            } else if (item == 'users') {
                                var users = ret.data.users;
                                var userText = '';
                                for (var i = 0, one; one = users[i]; i++) {
                                    userText += one.username + " ";
                                }
                                $(this).text(userText);
                            } else if (item == 'createTime') {
                                $(this).text(formatDateTime(text/1000));
                            } else {
                                $(this).text(text);
                            }
                        });
                        if (ret.data.isCreator == 1) {
                            $('#js-btn-creator').show();
                        }
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

    $.ajax({
        type: 'GET',
        url: $('#prefixUrl').val() + '/api/project/getUsersByPid?id=' + $('#js-pid').val(),
        success: function(ret) {
            if (ret.code == 0) {
                if (ret.data) {
                    for (var i = 0, one; one = ret.data[i]; i++) {
                        cache.projectUserList.push({
                            id: one.id,
                            text: one.username
                        });
                    }
                    $('#js-select-follower').select2({
                        language:"zh-CN",
                        data: cache.projectUserList
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

    $('#js-btn-delete').on('click', function () {
        showConfirm('真的要删除这个项目吗？（删除后不可恢复）', '删除项目', function() {
            ajaxDelete();
        });
        var ajaxDelete = function() {
            $.ajax({
                type: 'GET',
                url: $('#prefixUrl').val() + '/api/project/delete?id=' + $('#js-pid').val(),
                success: function(ret) {
                    if (ret.code == 0) {
                        toaster('删除成功', 'success');
                        setTimeout(function () {
                            window.location.href = $('#prefixUrl').val() + '/';
                        }, 500);
                    } else {
                        toaster(ret.msg || '系统繁忙', 'error');
                    }
                },
                error: function() {
                    toaster('系统繁忙', "error");
                }
            });
        }
    });

    $('#js-btn-edit').on('click', function () {
        $('#js-input-name').val(cache.project.name);
        $('#js-textarea-description').val(cache.project.description);
        var users = [];
        for (var i = 0, one; one = cache.project.users[i]; i++) {
            users.push(one.id);
        }
        $('#js-select-user').val(users).trigger("change");
        $('#js-modal-project').modal('show');
    });

    $('#js-btn-add-submit').on('click', function () {
        var data = {
            id: $('#js-pid').val(),
            name: $('#js-input-name').val(),
            description: $('#js-textarea-description').val(),
            users: $('#js-select-user').val() || []
        };
        $.ajax({
            type: 'POST',
            url: $('#prefixUrl').val() + '/api/project/modify',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('编辑成功', 'success');
                    $('#js-modal-project').modal('hide');
                    setTimeout(function () {
                        window.location.reload();
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

    $('#js-btn-add').on('click', function () {
        $('#js-modal-risk [name="content"]').val('');
        $('#js-modal-risk [name="possibility"]').val('2');
        $('#js-modal-risk [name="impact"]').val('2');
        $('#js-modal-risk [name="trigger"]').val('');
        $('#js-select-follower').val(null).trigger('change');
        $('#js-modal-risk').modal('show');
    });

    $('#js-btn-add-risk-submit').on('click', function () {
        var data = {
            pid: $('#js-pid').val()
        };
        $('#js-modal-risk [name]').each(function () {
            data[$(this).attr('name')] = $(this).val();
        });
        data.followers = $('#js-select-follower').val() || [];
        $.ajax({
            type: 'POST',
            url: $('#prefixUrl').val() + '/api/risk/add',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('创建成功', 'success');
                    $('#js-modal-risk').modal('hide');
                    $('#js-select-risk-flag').val(1);
                    cache.options.flag = 1;
                    loadData();
                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    });

    initDataTable();

    loadData();

    var dataTable;

    function initDataTable() {
        dataTable = $('#js-table').DataTable({
            language: dataTableChinese,
            paging: false,
            searching: true,
            info: false,
            columns: [
                { data: 'id' },
                {
                    data: 'type',
                    render: function (data) {
                        return cache.myTypeMap[String(data)].text;
                    }
                },
                { data: 'content' },
                {
                    data: 'possibility',
                    render: function(data) {
                        return cache.typeMap[String(data)].text;
                    }
                },
                {
                    data: 'impact',
                    render: function(data) {
                        return cache.typeMap[String(data)].text;
                    }
                },
                {
                    data: 'followers',
                    render: function (data) {
                        if(data) {
                            var followers = [];
                            for(var i = 0; i < data.length; i++) {
                                followers.push(data[i].text);
                            }
                            return followers.join(",");
                        }
                        return "无";
                    }
                },
                {
                    data: 'createTime',
                    render: function(data) {
                        return formatDateTime(data/1000);
                    }
                },
                {
                  data: "object",
                    render: function (data) {
                        // var myEdit = document.createElement("span");
                        // myEdit.className = "my-edit";
                        // myEdit.innerHTML = "编辑";
                        // myEdit.onclick = function (e) {
                        //     editRiskItem(data);
                        // }
                        return '<span class="my-edit" onclick="editRiskItem(' + data.id + ')">编辑</span> <span class="my-edit" onclick="deleteRiskItem(' + data.id + ')">删除</span>';
                    }
                }
            ],
            order: [[0, 'desc']]
        });
    }
    
    function loadData() {
        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/risk/getRisksByPid?id=' + $('#js-pid').val()
                + '&flag=' + cache.options.flag,
            success: function(ret) {
                if (ret.code == 0) {
                    if (ret.data) {
                        var risks = ret.data;
                        for(var i = 0; i < risks.length; i++){
                            risks[i].object = risks[i];
                            $.ajax({
                                type: 'GET',
                                async: false,
                                url: $('#prefixUrl').val() + '/api/riskState/getRiskStatesByRid?id=' + risks[i].id,
                                success: function(result) {
                                    if (result.code == 0) {
                                        if (result.data) {
                                            var followers = [];
                                            for (var j = 0, one; one = result.data.list[j]; j++) {
                                                followers.push({
                                                    id: one.creator,
                                                    text: one.username
                                                });
                                            }
                                            risks[i].followers = followers;
                                        }
                                    } else {
                                        toaster(result.msg || '系统繁忙', 'error');
                                    }
                                },
                                error: function() {
                                    toaster('系统繁忙', "error");
                                }
                            });
                        }
                        dataTable.clear().rows.add(ret.data).draw();
                        myRisks = ret.data;
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
    
    $('#js-select-risk-flag').on('change', function () {
        cache.options.flag = $('#js-select-risk-flag').val();
        loadData();
    });

});

function editRiskItem(id) {
    var risk;
    for(var j = 0; j < myRisks.length; j++) {
        if(myRisks[j].id == id) {
            risk = myRisks[j];
        }
    }
    console.log(risk);
    $('#js-modal-risk [name="content"]').val(risk.content);
    $('#js-modal-risk [name="possibility"]').val(risk.possibility);
    $('#js-modal-risk [name="impact"]').val(risk.impact);
    $('#js-modal-risk [name="trigger"]').val(risk.trigger);
    var users = [];
    if (risk.followers) {
        for (var i = 0, one; one = risk.followers[i]; i++) {
            users.push(one.id);
        }
    }
    $('#js-select-follower').val(users).trigger("change");
    $('#js-modal-risk').modal('show');
    $('#js-btn-add-risk-submit').off('click');
    $('#js-btn-add-risk-submit').on('click', function () {
        var data = {
            id: id+"",
            pid: $('#js-pid').val()
        };
        $('#js-modal-risk [name]').each(function () {
            data[$(this).attr('name')] = $(this).val();
        });
        data.followers = $('#js-select-follower').val() || [];
        $.ajax({
            type: 'POST',
            url: $('#prefixUrl').val() + '/api/risk/modify',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('修改成功', 'success');
                    $('#js-modal-risk').modal('hide');
                    setTimeout(function () {
                        window.location.reload();
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
}

function deleteRiskItem(id) {
    showConfirm('真的要删除这个风险条目吗？（删除后不可恢复）', '删除风险条目', function() {
        ajaxDelete();
    });
    var ajaxDelete = function() {
        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/risk/delete?id=' + id,
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('删除成功', 'success');
                    setTimeout(function () {
                        window.location.reload();
                    }, 500);
                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    }
}


