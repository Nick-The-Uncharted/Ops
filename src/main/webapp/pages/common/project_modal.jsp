<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" tabindex="-1" role="dialog" id="js-modal-project">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">创建项目</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="js-input-name">项目名称</label>
                    <input class="form-control" id="js-input-name">
                </div>
                <div class="form-group">
                    <label for="js-textarea-description">项目描述</label>
                    <textarea class="form-control" id="js-textarea-description" rows="4"></textarea>
                </div>
                <div class="form-group">
                    <p style="font-weight: bold">项目成员</p>
                    <select id="js-select-user" multiple="multiple" style="width: 100%"></select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="js-btn-add-submit">确认</button>
            </div>
        </div>
    </div>
</div>