<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" tabindex="-1" role="dialog" id="js-modal-risk">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">创建条目</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>风险内容</label>
                    <textarea class="form-control" rows="3" name="content"></textarea>
                </div>
                <div class="form-group">
                    <label>可能性</label>
                    <select class="form-control" name="possibility">
                        <option value="1">低</option>
                        <option value="2">中</option>
                        <option value="3">高</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>影响程度</label>
                    <select class="form-control" name="impact">
                        <option value="1">低</option>
                        <option value="2">中</option>
                        <option value="3">高</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>触发器/阈值</label>
                    <textarea class="form-control" rows="3" name="trigger"></textarea>
                </div>
                <div class="form-group">
                    <p style="font-weight: bold">跟踪者</p>
                    <select id="js-select-follower" multiple="multiple" style="width: 100%"></select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="js-btn-add-risk-submit">提交</button>
            </div>
        </div>
    </div>
</div>