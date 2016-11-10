package top.kass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kass.dao.UserDao;
import top.kass.model.User;
import top.kass.service.UserService;
import top.kass.util.Util;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> login(Map reqMap) {
        Map<String, Object> map = new HashMap<>();

        String username = ((String)reqMap.get("username")).trim();
        String password = ((String)reqMap.get("password")).trim();

        if (username.length() == 0 || password.length() == 0) {
            map.put("code", 100);
            map.put("msg", "用户名或密码不能为空");
            return map;
        }

        if (password.length() < 5) {
            map.put("code", 101);
            map.put("msg", "密码长度至少是5位");
            return map;
        }

        User user = userDao.getByUsername(username);
        if (user == null || !Util.md5(password).equals(user.getPassword())) {
            map.put("code", 102);
            map.put("msg", "用户名或密码错误");
            return map;
        }

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("name", user.getName());
        data.put("role", user.getRole());
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> register(Map reqMap) {
        Map<String, Object> map = new HashMap<>();
        String username = ((String)reqMap.get("username")).trim();
        String name = ((String)reqMap.get("name")).trim();
        String password = ((String)reqMap.get("password")).trim();
        String password2 = ((String)reqMap.get("password2")).trim();

        if (username.length() == 0 || name.length() == 0 ||
                password.length() == 0 || password2.length() == 0) {
            map.put("code", 100);
            map.put("msg", "请填写完整");
            return map;
        }

        if (password.length() < 5) {
            map.put("code", 101);
            map.put("msg", "密码长度至少是5位");
            return map;
        }

        if (!password.equals(password2)) {
            map.put("code", 102);
            map.put("msg", "密码不一致");
            return map;
        }

        User user = userDao.getByUsername(username);
        if (user != null) {
            map.put("code", 103);
            map.put("msg", "用户名已经被注册了");
            return map;
        }

        user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(Util.md5(password));
        user.setRole((byte)0);
        user = userDao.create(user);
        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("name", user.getName());
        data.put("role", user.getRole());
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> password(Map reqMap, int id) {
        Map<String, Object> map = new HashMap<>();
        String oldPassword = ((String)reqMap.get("oldPassword")).trim();
        String password = ((String)reqMap.get("password")).trim();
        String password2 = ((String)reqMap.get("password2")).trim();

        if (oldPassword.length() == 0 || password.length() == 0 ||
                password2.length() == 0) {
            map.put("code", 100);
            map.put("msg", "请填写完整");
            return map;
        }

        User user = userDao.getById(id);
        if (!Util.md5(oldPassword).equals(user.getPassword())) {
            map.put("code", 101);
            map.put("msg", "旧密码不正确");
            return map;
        }

        if (password.length() < 5) {
            map.put("code", 102);
            map.put("msg", "密码长度至少是5位");
            return map;
        }

        if (!password.equals(password2)) {
            map.put("code", 103);
            map.put("msg", "新密码不一致");
            return map;
        }

        user.setPassword(Util.md5(password));
        userDao.update(user);
        map.put("code", 0);
        return map;
    }

    @Override
    public Map<String, Object> getMyInfo(int id) {
        Map<String, Object> map = new HashMap<>();
        User user = userDao.getById(id);
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("name", user.getName());
        data.put("role", user.getRole());
        map.put("code", 0);
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> getAllUsers() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", userDao.getAll());
        return map;
    }

}
