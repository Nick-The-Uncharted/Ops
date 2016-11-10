package top.kass.dao;

import top.kass.model.RiskState;

import java.util.List;

public interface RiskStateDao {

    public RiskState getById(int id);

    public RiskState create(RiskState riskState);

    public RiskState update(RiskState riskState);

    public void delete(int id);

    public List getByRid(int rid);

}
