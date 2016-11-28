package top.kass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kass.dao.AuthDao;
import top.kass.dao.RiskDao;
import top.kass.dao.UserDao;
import top.kass.model.Risk;
import top.kass.model.User;
import top.kass.service.RiskService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RiskServiceImpl implements RiskService {

    @Autowired
    private RiskDao riskDao;
    @Autowired
    private AuthDao authDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> add(Map reqMap, int uid) {
        Map<String, Object> map = new HashMap<>();

        int pid = Integer.parseInt((String)reqMap.get("pid"));

        if (!authDao.isProjectUser(pid, uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        String content = (String)reqMap.get("content");
        int possibility = Integer.parseInt((String)reqMap.get("possibility"));
        int type = Integer.parseInt((String)reqMap.get("type"));
        int impact = Integer.parseInt((String)reqMap.get("impact"));
        boolean isProblem= ((String)reqMap.get("isProblem")).equals("1");
        String trigger = (String)reqMap.get("trigger");
        List<String> uids = (List<String>)reqMap.get("followers");

        if (content.length() == 0) {
            map.put("code", 100);
            map.put("msg", "风险内容不能为空");
            return map;
        }

        Risk risk = new Risk();
        risk.setPid(pid);
        risk.setContent(content);
        risk.setPossibility((byte)possibility);
        risk.setImpact((byte)impact);
        risk.setTrigger(trigger);
        risk.setCommitter(uid);
        risk.setType(type);
        risk.setCreateTime(new Timestamp(System.currentTimeMillis()));
        risk.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        risk.setIsProblem(isProblem);
        risk = riskDao.create(risk);
        riskDao.updateFollowers(risk.getId(), uids);

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("content", risk.getId());
        data.put("possibility", risk.getPossibility());
        data.put("impact", risk.getImpact());
        data.put("trigger", risk.getTrigger());
        data.put("createTime", risk.getCreateTime());
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> modify(Map reqMap, int uid) {
        Map<String, Object> map = new HashMap<>();

        int rid = Integer.parseInt((String)reqMap.get("id"));

        if (!authDao.isRiskCommitter(rid, uid) && !authDao.isRiskFollower(rid, uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        String content = (String)reqMap.get("content");
        int possibility = Integer.parseInt((String)reqMap.get("possibility"));
        int type = Integer.parseInt((String)reqMap.get("type"));
        int impact = Integer.parseInt((String)reqMap.get("impact"));
        boolean isProblem= ((String)reqMap.get("isProblem")).equals("1");
        String trigger = (String)reqMap.get("trigger");
        List<String> uids = (List<String>)reqMap.get("followers");

        if (content.length() == 0) {
            map.put("code", 100);
            map.put("msg", "风险内容不能为空");
            return map;
        }

        Risk risk = riskDao.getById(rid);
        risk.setContent(content);
        risk.setPossibility((byte)possibility);
        risk.setImpact((byte)impact);
        risk.setTrigger(trigger);
        risk.setType(type);
        risk.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        risk.setIsProblem(isProblem);
        risk = riskDao.create(risk);
        riskDao.updateFollowers(risk.getId(), uids);

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", risk.getId());
        data.put("content", risk.getContent());
        data.put("possibility", risk.getPossibility());
        data.put("impact", risk.getImpact());
        data.put("trigger", risk.getTrigger());
        data.put("createTime", risk.getCreateTime());
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> delete(int id, int uid) {
        Map<String, Object> map = new HashMap<>();
        if (!authDao.isRiskCommitter(id, uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        riskDao.delete(id);
        map.put("code", 0);
        return map;
    }

    @Override
    public Map<String, Object> getRisksByPid(int pid, int flag, int uid) {
        Map<String, Object> map = new HashMap<>();

        if (!authDao.isProjectUser(pid, uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        List<Map> risks = riskDao.getByPid(pid, flag, uid);
        for (Map m: risks) {

        }
        map.put("code", 0);
        map.put("data", risks);
        return map;
    }

    @Override
    public Map<String, Object> getRisksByTimeIntervel(Date beginTime, Date endTime, int type, int id) {
        Map<String, Object> map = new HashMap<>();
        Map risks = riskDao.getByTimeIntervel(beginTime, endTime, type, id);
        map.put("data", risks);
        return map;
    }

    @Override
    public Map<String, Object> getById(int id, int uid) {
        Map<String, Object> map = new HashMap<>();

        Risk risk = riskDao.getById(id);

        if (!authDao.isProjectUser(risk.getPid(), uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        User user = userDao.getById(risk.getCommitter());

        List<Map> followers = riskDao.getFollowers(id);
        for (Map m: followers) {
            m.remove("password");
            m.remove("role");
            m.remove("rid");
            m.remove("uid");
        }

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", risk.getId());
        data.put("pid", risk.getPid());
        data.put("type", risk.getType());
        data.put("content", risk.getContent());
        data.put("possibility", risk.getPossibility());
        data.put("impact", risk.getImpact());
        data.put("trigger", risk.getTrigger());
        data.put("committer", user);
        data.put("createTime", risk.getCreateTime());
        data.put("updateTime", risk.getUpdateTime());
        data.put("followers", followers);
        data.put("isProblem", risk.getIsProblem());

        if (authDao.isRiskCommitter(id, uid)) {
            data.put("isCommitter", 1);
            data.put("isUser", 1);
        } else {
            data.put("isCommitter", 0);
            if (authDao.isRiskFollower(id, uid)) {
                data.put("isUser", 1);
            } else {
                data.put("isUser", 0);
            }
        }
        map.put("data", data);
        return map;
    }

}
