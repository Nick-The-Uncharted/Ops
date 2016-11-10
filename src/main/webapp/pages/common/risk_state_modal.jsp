<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" tabindex="-1" role="dialog" id="js-modal-risk-state">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">创建跟踪</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>跟踪标题</label>
                    <input class="form-control" name="name">
                </div>
                <div class="form-group">
                    <label>跟踪内容</label>
                    <textarea class="form-control" name="content" rows="4"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="js-btn-add-state-submit">提交</button>
            </div>
        </div>
    </div>
</div>