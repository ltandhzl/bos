package bos.dao;

import bos.base.BaseDao;
import bos.pojo.Staff;
import bos.utils.PageUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDao extends BaseDao<Staff> {
    public void addStaff(Staff model) {
        getHibernateTemplate().save(model);
    }

    public int getTotalSize(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(Projections.rowCount());
        List<Long> countList = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
        return countList.get(0).intValue();
    }

    public List<Staff> findAllStaffBySize(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(null);
        int firstPage=(pageUtils.getCurrentPage()-1)*pageUtils.getPageSize();
        int pageSize=pageUtils.getPageSize();
        List<Staff> staffList = (List<Staff>) getHibernateTemplate().findByCriteria(criteria, firstPage, pageSize);
        return staffList;
    }

    public void deleteStaffById(final String[] split) {

        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String hql="update Staff set deltag=? where id=?";
                Query  query= session.createQuery(hql);
                for (String id:split){
                    query.setParameter(0,'1');
                    query.setParameter(1,id);
                    query.executeUpdate();
                }
                return null;
            }
        });
    }

    public void editStaff(Staff model) {

        getHibernateTemplate().update(model);
    }

    public List<Staff> findAllStaff() {
        String hql="from Staff";
        return (List<Staff>) getHibernateTemplate().find(hql);
    }

    public List<Staff> finAllStaffByQ(String q) {
        String hql="from Staff where name like ?";
        return (List<Staff>) getHibernateTemplate().find(hql,"%"+q+"%");
    }
}
