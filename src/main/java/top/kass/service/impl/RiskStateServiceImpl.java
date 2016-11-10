package top.kass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kass.dao.AuthDao;
import top.kass.dao.RiskDao;
import top.kass.dao.RiskStateDao;
import top.kass.dao.UserDao;
import top.kass.model.Project;
import top.kass.model.Risk;
import top.kass.model.RiskState;
import top.kass.service.RiskStateService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RiskStateServiceImpl implements RiskStateService {

    @Autowired
    private RiskDao riskDao;
    @Autowired
    private RiskStateDao riskStateDao;
    @Autowired
    private AuthDao authDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> modify(Map reqMap, int uid) {
        Map<String, Object> map = new HashMap<>();

        System.out.println(reqMap);

        String name = (String)reqMap.get("name");
        String content = (String)reqMap.get("content");

        if (name.length() == 0 || content.length() == 0) {
            map.put("code", 100);
            map.put("msg", "跟踪名称或内容不能为空");
            return map;
        }

        int id = (int)reqMap.get("id");
        RiskState riskState = riskStateDao.getById(id);

        if (uid != riskState.getCreator()) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        riskState.setRid(Integer.parseInt((String)reqMap.get("rid")));
        riskState.setName(name);
        riskState.setContent(content);
        riskState.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        riskState = riskStateDao.update(riskState);

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", riskState.getId());
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> add(Map reqMap, int uid) {
        Map<String, Object> map = new HashMap<>();

        int rid = Integer.parseInt((String)reqMap.get("id"));

        if (!authDao.isRiskCommitter(rid, uid) && !authDao.isRiskFollower(rid, uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        String name = (String)reqMap.get("name");
        String content = (String)reqMap.get("content");

        if (name.length() == 0 || content.length() == 0) {
            map.put("code", 100);
            map.put("msg", "跟踪标题或内容不能为空");
            return map;
        }

        RiskState riskState = new RiskState();
        riskState.setRid(rid);
        riskState.setName(name);
        riskState.setContent(content);
        riskState.setCreator(uid);
        riskState.setCreateTime(new Timestamp(System.currentTimeMillis()));
        riskState.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        riskState = riskStateDao.create(riskState);

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", riskState.getId());
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> delete(int id, int uid) {
        // TODO
        return null;
    }

    @Override
    public Map<String, Object> getRiskStatesByRid(int rid, int uid) {
        Map<String, Object> map = new HashMap<>();

        Risk risk = riskDao.getById(rid);
        if (!authDao.isProjectUser(risk.getPid(), uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        List<Map> list = riskStateDao.getByRid(rid);
        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        if (!authDao.isRiskCommitter(rid, uid) && !authDao.isRiskFollower(rid, uid)) {
            data.put("canEdit", 0);
        } else {
            data.put("canEdit", 1);
        }
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> getById(int id, int uid) {
        // TODO
        return null;
    }

}
