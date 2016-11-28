package top.kass.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

public interface RiskService {

    public Map<String, Object> add(Map reqMap, int uid);

    public Map<String, Object> modify(Map reqMap, int uid);

    public Map<String, Object> delete(int id, int uid);

    public Map<String, Object> getRisksByPid(int pid, int flag, int uid);

    public Map<String, Object> getRisksByTimeIntervel(Date beginTime, Date endTime, int type, int id);

    public Map<String, Object> getById(int id, int uid);

}
