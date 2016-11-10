package top.kass.dao;

import top.kass.model.User;

import java.util.List;

public interface UserDao {

    public User getByUsername(String username);

    public User create(User user);

    public User update(User user);

    public User getById(int id);

    public List<User> getAll();

}
