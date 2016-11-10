package top.kass.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.kass.dao.AuthDao;
import top.kass.model.Project;
import top.kass.model.Risk;

@Repository
public class AuthDaoimpl implements AuthDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean isProjectCreator(int pid, int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Project as project where id=:id");
        query.setInteger("id", pid);
        if (query.list() == null || query.list().size() == 0) {
            return false;
        }
        Project project = (Project) query.list().get(0);
        if (project.getCreator() == uid) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isProjectUser(int pid, int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM project_user WHERE pid=? AND uid=?");
        query.setInteger(0, pid);
        query.setInteger(1, uid);
        if (query.list() == null || query.list().size() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isRiskCommitter(int rid, int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Risk as risk where id=:id");
        query.setInteger("id", rid);
        if (query.list() == null || query.list().size() == 0) {
            return false;
        }
        Risk risk = (Risk) query.list().get(0);
        if (risk.getCommitter() == uid) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRiskFollower(int rid, int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM risk_follower WHERE rid=? AND uid=?");
        query.setInteger(0, rid);
        query.setInteger(1, uid);
        if (query.list() == null || query.list().size() == 0) {
            return false;
        }
        return true;
    }
}
