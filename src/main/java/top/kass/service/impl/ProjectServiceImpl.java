package top.kass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kass.dao.AuthDao;
import top.kass.dao.ProjectDao;
import top.kass.dao.UserDao;
import top.kass.model.Project;
import top.kass.model.User;
import top.kass.service.ProjectService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private AuthDao authDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> add(Map reqMap, int uid) {

        Map<String, Object> map = new HashMap<>();

        String name = (String)reqMap.get("name");
        String description = (String)reqMap.get("description");
        List<String> uids = (List<String>)reqMap.get("users");

        if (name.length() == 0) {
            map.put("code", 100);
            map.put("msg", "RA名称不能为空");
            return map;
        }

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setCreator(uid);
        project.setCreateTime(new Timestamp(System.currentTimeMillis()));
        project = projectDao.create(project);
        projectDao.updateUsers(project.getId(), uids, uid);

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", project.getId());
        data.put("name", project.getName());
        data.put("description", project.getDescription());
        data.put("creator", project.getCreator());
        data.put("createTime", project.getCreateTime());
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> getMyProjects(int uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("create", projectDao.getCreatedProjectsByUid(uid));

        List<Map> ins = projectDao.getProjectsByUid(uid);
        List<Map> newIns = new ArrayList<>();
        for (Map m: ins) {
            if ((int)m.get("creator") != uid) {
                newIns.add(m);
            }
        }

        data.put("in", newIns);
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> delete(int id, int uid) {
        Map<String, Object> map = new HashMap<>();
        if (!authDao.isProjectCreator(id, uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        projectDao.delete(id);
        map.put("code", 0);
        return map;
    }

    @Override
    public Map<String, Object> modify(Map reqMap, int uid) {
        Map<String, Object> map = new HashMap<>();

        int pid = Integer.parseInt((String)reqMap.get("id"));

        if (!authDao.isProjectCreator(pid, uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        String name = (String)reqMap.get("name");
        String description = (String)reqMap.get("description");
        List<String> uids = (List<String>)reqMap.get("users");

        if (name.length() == 0) {
            map.put("code", 100);
            map.put("msg", "RA名称不能为空");
            return map;
        }

        Project project = projectDao.getById(pid);
        project.setName(name);
        project.setDescription(description);
        project = projectDao.update(project);
        projectDao.updateUsers(project.getId(), uids, uid);

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", project.getId());
        data.put("name", project.getName());
        data.put("description", project.getDescription());
        data.put("creator", project.getCreator());
        data.put("createTime", project.getCreateTime());
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> getById(int id, int uid) {
        Map<String, Object> map = new HashMap<>();
        Project project = projectDao.getById(id);
        List<Map> users = projectDao.getUsers(id);
        User user = userDao.getById(project.getCreator());
        for (Map m: users) {
            m.remove("password");
            m.remove("role");
            m.remove("pid");
            m.remove("uid");
        }

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", project.getId());
        data.put("name", project.getName());
        data.put("description", project.getDescription());
        data.put("createTime", project.getCreateTime());
        data.put("users", users);
        data.put("creator", user);
        if (project.getCreator() == uid) {
            data.put("isCreator", 1);
        } else {
            data.put("isCreator", 0);
        }
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> getUsersByPid(int pid, int uid) {
        Map<String, Object> map = new HashMap<>();

        if (!authDao.isProjectUser(pid, uid)) {
            map.put("code", 401);
            map.put("msg", "你没有权限");
            return map;
        }

        map.put("code", 0);
        List<Map> users = projectDao.getUsers(pid);
        for (Map m: users) {
            m.remove("pid");
            m.remove("uid");
            m.remove("password");
            m.remove("role");
        }
        map.put("data", users);
        return map;
    }

}
