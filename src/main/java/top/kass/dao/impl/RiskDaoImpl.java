package top.kass.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.kass.dao.RiskDao;
import top.kass.model.Risk;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RiskDaoImpl implements RiskDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Risk getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Risk as risk where id=:id");
        query.setInteger("id", id);
        if (query.list() == null || query.list().size() == 0) {
            return null;
        } else {
            return (Risk) query.list().get(0);
        }
    }

    @Override
    public Risk create(Risk risk) {
        Session session = sessionFactory.getCurrentSession();
        session.save(risk);
        return risk;
    }

    @Override
    public Risk update(Risk risk) {
        Session session = sessionFactory.getCurrentSession();
        session.save(risk);
        return risk;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Risk where id=:id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateFollowers(int id, List<String> uids) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("DELETE FROM risk_follower where rid=:id");
        query.setInteger("id", id);
        query.executeUpdate();
        for (String s: uids) {
            int tempId = Integer.parseInt(s);
            query = session.createSQLQuery("INSERT INTO risk_follower (rid, uid) VALUES (?, ?)");
            query.setInteger(0, id);
            query.setInteger(1, tempId);
            query.executeUpdate();
        }
    }

    @Override
    public List getFollowers(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM risk_follower rf LEFT JOIN user u " +
                "ON rf.uid=u.id WHERE rf.rid=?");
        query.setInteger(0, id);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List getByPid(int pid, int flag, int uid) {
        // flag 0是所有 1是我创建的 2是我跟踪的
        Session session = sessionFactory.getCurrentSession();
        Query query;
        List list;

        if (flag == 0) {
            query = session.createSQLQuery("SELECT r.id, r.pid, r.type, r.content, r.possibility," +
                    "r.impact, r.committer, r.isProblem, r.createTime, r.updateTime, " +
                    "u.name, u.username FROM risk r LEFT JOIN user u " +
                    "ON r.committer=u.id WHERE r.pid=?");
            query.setInteger(0, pid);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            list = query.list();
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        } else if (flag == 1) {
            query = session.createSQLQuery("SELECT r.id, r.pid, r.type, r.content, r.possibility," +
                    "r.impact, r.committer, r.isProblem, r.createTime, r.updateTime, " +
                    "u.name, u.username FROM risk r LEFT JOIN user u " +
                    "ON r.committer=u.id WHERE r.pid=? AND r.committer=?");
            query.setInteger(0, pid);
            query.setInteger(1, uid);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            list = query.list();
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        } else {
            query = session.createSQLQuery("SELECT r.id, r.pid, r.type, r.content, r.possibility," +
                    "r.impact, r.committer, r.isProblem, r.createTime, r.updateTime, " +
                    "u.name, u.username FROM risk_follower rf " +
                    "LEFT JOIN risk r ON rf.rid=r.id LEFT JOIN user u " +
                    "ON r.committer=u.id WHERE r.pid=? AND rf.uid=?");
            query.setInteger(0, pid);
            query.setInteger(1, uid);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            list = query.list();
            if (list == null) {
                list = new ArrayList<>();
            }
        }
        return list;
    }

}
