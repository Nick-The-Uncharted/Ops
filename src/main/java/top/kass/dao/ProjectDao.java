package top.kass.dao;

import top.kass.model.Project;

import java.util.List;

public interface ProjectDao {

    public Project getById(int id);

    public Project create(Project project);

    public Project update(Project project);

    public void delete(int id);

    public void updateUsers(int id, List<String> uids, int uid);

    public List getUsers(int id);

    public List<Project> getCreatedProjectsByUid(int uid);

    public List getProjectsByUid(int uid);

}
