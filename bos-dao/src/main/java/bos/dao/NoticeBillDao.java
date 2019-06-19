package bos.dao;

import bos.base.BaseDao;
import bos.pojo.Decidedzone;
import bos.pojo.Noticebill;
import bos.pojo.User;
import bos.pojo.Workbill;
import bos.utils.PageUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeBillDao extends BaseDao<Noticebill> {
    public void saveNoticeBill(Noticebill model) {
        getHibernateTemplate().save(model);
    }

    public Decidedzone findDecidedZoneById(String decidedzoneId) {
        return getHibernateTemplate().get(Decidedzone.class, decidedzoneId);
    }

    public void saveWorkBill(Workbill workbill) {
        getHibernateTemplate().save(workbill);
    }

    public List<Noticebill> findAllNoticeBill(final PageUtils pageUtils) {

        return getHibernateTemplate().execute(new HibernateCallback<List<Noticebill>>() {

            @Override
            public List<Noticebill> doInHibernate(Session session) throws HibernateException {
                String hql =" from Noticebill  where user.id=?";
                Query query = session.createQuery(hql);
                User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
                String id = user.getId();
                query.setParameter(0, id);
                int start = (pageUtils.getCurrentPage() - 1) * pageUtils.getPageSize();
                int pageSize = pageUtils.getPageSize();
                query.setFirstResult(start);
                query.setMaxResults(pageSize);
                List<Noticebill> list = query.list();
                return list;
            }
        });


    }

    public int findAllNoticeBillSize() {
        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
                String id = user.getId();
                String hql = "select count(*) from Noticebill where user.id=?";
                Query query = session.createQuery(hql);
                query.setParameter(0, id);
                Long row = (Long) query.uniqueResult();
                return row.intValue();
            }
        });

    }

}
