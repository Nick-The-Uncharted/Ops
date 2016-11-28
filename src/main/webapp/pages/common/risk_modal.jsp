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
                    <label>风险类型</label>
                    <select class="form-control" name="type">
                        <option value="1">范围风险</option>
                        <option value="2">进度风险</option>
                        <option value="3">成本风险</option>
                        <option value="4">质量风险</option>
                        <option value="5">技术风险</option>
                        <option value="6">管理风险</option>
                        <option value="7">社会环境风险</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>风险描述</label>
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
                    <input class="form-control" name="trigger" />
                </div>
                <div class="form-group">
                    <p style="font-weight: bold">跟踪者</p>
                    <select id="js-select-follower" multiple="multiple" style="width: 100%"></select>
                </div>
                <div class="has-warning" style="padding-bottom: 8px">
                    <div class="checkbox">
                        <label style="position: absolute; right: 20px">
                            <input type="checkbox" id="isProblem" value="1" name="isProblem">
                            成为问题
                        </label>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="js-btn-add-risk-submit">确定</button>
            </div>
        </div>
    </div>
</div>