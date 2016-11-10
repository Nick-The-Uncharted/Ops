package top.kass.service;

import java.util.Map;

public interface RiskStateService {

    public Map<String, Object> add(Map reqMap, int uid);

    public Map<String, Object> modify(Map reqMap, int uid);

    public Map<String, Object> delete(int id, int uid);

    public Map<String, Object> getRiskStatesByRid(int rid, int uid);

    public Map<String, Object> getById(int id, int uid);

}
