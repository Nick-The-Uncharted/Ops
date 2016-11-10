(function (win, doc, undefined) {

    win.toaster = function(message, type) {
        var toaster = $("#toaster");
        toaster.append('<div class="toast-item"><div class="message">' + message + '</div>' +
            '<i class="close fa fa-close"></i></div>');
        var thisItem = toaster.children().last();
        $(thisItem.children(".close").eq(0)).bind("click", function () {
            thisItem.slideUp(function() {
                thisItem.remove();
            });
        });
        if (type == "success") thisItem.addClass("alert alert-success");
        else if (type == "error") thisItem.addClass("alert alert-danger");
        thisItem.fadeIn();
        setTimeout(function() {
            thisItem.slideUp(function() {
                thisItem.remove();
            });
        }, 3000);
    };

    win.tmplCache = {};

    win.tmpl = function(strTmpl, args) {
        var argNames = [];
        var argValues = [];
        for (var a in args) {
            argNames.push(a);
            argValues.push(args[a]);
        }
        var funcs = win.tmplCache[strTmpl] || function() {
                var f = [ 'var __out__ = [];' ];
                strTmpl.replace(/<%=([\d\D]*?)%>|<%([\d\D]*?)%>|([\d\D]+?)(?=<\%|$)/g, function($0, $1, $2, $3) {
                    if ($3) {
                        f.push('__out__.push(unescape("', escape($3), '"));');
                    } else if ($1) {
                        f.push('__out__.push(', $1, ');');
                    } else if ($2) {
                        f.push($2, ';');
                    }
                });
                f.push('return __out__.join("")');
                return new Function(argNames.join(', '), f.join(''));
            }();
        win.tmplCache[strTmpl] = funcs;
        return funcs.apply(args||{}, argValues);
    };

    win.showConfirm = function(text, title, handler, cancelHandler) {

        var dgEl = $('.g_confirm'),
            html = '';

        if (dgEl.length) {
            return;
        }

        html = '<div class="g_confirm modal fade" style="z-index:9999;display:block">' +
            '<div class="modal-dialog modal-sm">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-action="cancel"><span data-action="cancel">×</span></button>' +
            '<h4 class="modal-title">' + (title || '<span class="fa fa-info" aria-hidden="true"></span>') + '</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<div class="row">' +
            '<div class="col-md-12">' + text + '</div>' +
            '</div>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default" data-action="cancel">取消</button>' +
            '<button type="button" class="btn btn-primary" data-action="confirm">确定</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';

        dgEl = $(html);
        dgEl.appendTo(doc.body);
        dgEl.modal({
            keyboard: false,
            backdrop: 'static'
        });

        dgEl.on('click', function(e) {
            var target = $(e.target),
                action = target.attr('data-action');
            if (action == 'cancel') {
                dgEl.modal('hide');
                if (typeof cancelHandler == 'function') {
                    cancelHandler();
                }
            } else if (action == 'confirm') {
                dgEl.modal('hide');
                if (typeof handler == 'function') {
                    handler();
                }
            }
        });

        dgEl.on('hidden.bs.modal', function() {
            dgEl.remove();
        });
    };

    win.formatDateTime = function(d) {
        function addZero(num) {
            return num < 10 ? '0' + num : num;
        }
        var _d = new Date(d*1000);
        return _d.getFullYear() + '-' + addZero(_d.getMonth() + 1) + '-' + addZero(_d.getDate()) + ' ' + addZero(_d.getHours()) + ':' + addZero(_d.getMinutes()) + ':' + addZero(_d.getSeconds());
    };

    win.dataTableChinese = {
        "sProcessing":   "处理中...",
            "sLengthMenu":   "显示 _MENU_ 项结果",
            "sZeroRecords":  "没有匹配结果",
            "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix":  "",
            "sSearch":       "搜索:",
            "sUrl":          "",
            "sEmptyTable":     "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands":  ",",
            "oPaginate": {
            "sFirst":    "首页",
                "sPrevious": "上页",
                "sNext":     "下页",
                "sLast":     "末页"
        },
        "oAria": {
            "sSortAscending":  ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
        }
    }

})(window, document);

$(document).ready(function () {
    
    $('#js-btn-logout').on('click', function () {
        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/auth/logout',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('登出成功' , 'success');
                    setTimeout(function () {
                        window.location.href = $('#prefixUrl').val() + '/login';
                    }, 1000);
                } else {
                    toaster(ret.msg || '系统繁忙' , 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    });

});