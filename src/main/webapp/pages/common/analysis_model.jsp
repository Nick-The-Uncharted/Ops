<%--
  Created by IntelliJ IDEA.
  User: administrasion
  Date: 28/11/2016
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" tabindex="-1" role="dialog" id="js-modal-analysis">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">分析RA</h4>
            </div>
            <div class="modal-body">
                <div class="form-group" style="display: flex; justify-content: space-between">
                    <input id="begin-time" type="date" class="form-control" placeholder="开始时间" />
                    <input id="end-time" type="date" class="form-control" placeholder="结束时间" />
                </div>
                <div class="form-group" style="display: flex; justify-content: center">
                    <div style="height: 400px; width: 400px; display: inline-block" >
                        <canvas id="analysisChart" ></canvas>
                    </div>
                </div>

                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="riskType" id="riskTypeRadio1" value="1" checked> 识别风险
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="riskType" id="riskTypeRadio2" value="2"> 演变成问题风险
                    </label>
                </div>

                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="chartType" id="chartTypeRadio1" value="pie" checked> 饼状图
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="chartType" id="chartTypeRadio2" value="bar"> 柱状图
                    </label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
