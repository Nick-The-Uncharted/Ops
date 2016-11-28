package top.kass.dao;

import top.kass.model.Risk;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RiskDao {

    public Risk getById(int id);

    public Risk create(Risk risk);

    public Risk update(Risk risk);

    public void delete(int id);

    public void updateFollowers(int id, List<String> uids);

    public List getFollowers(int id);

    public List getByPid(int pid, int flag, int uid);

    public Map<Integer, Object> getByTimeIntervel(Date beginTime, Date endTime, int type, int id);

}
