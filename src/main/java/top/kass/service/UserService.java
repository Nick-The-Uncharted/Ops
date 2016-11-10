package top.kass.service;

import java.util.Map;

public interface UserService {

    public Map<String, Object> login(Map reqMap);

    public Map<String, Object> register(Map reqMap);

    public Map<String, Object> password(Map reqMap, int id);

    public Map<String, Object> getMyInfo(int id);

    public Map<String, Object> getAllUsers();


}
