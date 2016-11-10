package top.kass.service;

import java.util.Map;

public interface RiskService {

    public Map<String, Object> add(Map reqMap, int uid);

    public Map<String, Object> modify(Map reqMap, int uid);

    public Map<String, Object> delete(int id, int uid);

    public Map<String, Object> getRisksByPid(int pid, int flag, int uid);

    public Map<String, Object> getById(int id, int uid);

}
