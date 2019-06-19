package bos.dao;

import bos.base.BaseDao;
import bos.pojo.Subarea;
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
public class SubareaDao extends BaseDao<Subarea> {

    public void saveSubarea(Subarea model) {
        getHibernateTemplate().save(model);
    }

    public int findSubareaTotalSize(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
        return list.get(0).intValue();
    }

    public List<Subarea> findAllSubarea(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(null);
        //指定hibernate框架封装对象的方式,使得根据条件查询返回的数据都是在subarea对象中
        criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int start=(pageUtils.getCurrentPage()-1)*pageUtils.getPageSize();
        int pageSize=pageUtils.getPageSize();
        return (List<Subarea>) getHibernateTemplate().findByCriteria(criteria,start,pageSize);
    }

    public void deleteSubareaById(final String[] split) {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String hql="update Subarea set delflag=? where id=?";
                Query query = session.createQuery(hql);
                for (String id:split){
                    query.setParameter(0,'1');
                    query.setParameter(1,id);
                    query.executeUpdate();
                }
                return null;
            }
        });
    }

    public void editSubarea(Subarea model) {
        getHibernateTemplate().update(model);
    }

    public List<Subarea> findAllSubarea() {
        String hql="from Subarea";
        return (List<Subarea>) getHibernateTemplate().find(hql);
    }

    public List<Subarea> findListNotAssociation() {
        String hql="from Subarea where decidedzone is null";
        return (List<Subarea>) getHibernateTemplate().find(hql);
    }

    public List<Object> findRegionBySubarea() {
        String hql="select r.province,count(*) from Subarea s left outer join s.region r group by r.province ";
       return (List<Object>) getHibernateTemplate().find(hql);
    }
}
