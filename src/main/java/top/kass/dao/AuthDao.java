package top.kass.dao;

public interface AuthDao {

    public boolean isProjectCreator(int pid, int uid);

    public boolean isProjectUser(int pid, int uid);

    public boolean isRiskCommitter(int rid, int uid);

    public boolean isRiskFollower(int rid, int uid);

}
