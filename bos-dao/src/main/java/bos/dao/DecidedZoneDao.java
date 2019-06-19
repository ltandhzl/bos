package bos.dao;

import bos.base.BaseDao;
import bos.pojo.Decidedzone;
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
import java.util.Set;

@Repository
public class DecidedZoneDao extends BaseDao<Decidedzone> {
    public Subarea getSubareaById(String id) {

        return getHibernateTemplate().get(Subarea.class, id);
    }

    public void saveDecidedZone(Decidedzone decidedzone) {
        getHibernateTemplate().save(decidedzone);
    }

    public int getAllDecidedZoneSize(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria();
        criteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
        return list.get(0).intValue();
    }

    public List<Decidedzone> getAllDecidedZone(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(null);
        criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int start = (pageUtils.getCurrentPage() - 1) * pageUtils.getPageSize();
        int pageSize = pageUtils.getPageSize();
        List<Decidedzone> list = (List<Decidedzone>) getHibernateTemplate().findByCriteria(criteria, start, pageSize);

        return list;

    }

    public void deleteDecidedZoneById(final String[] split) {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String hql = "update Decidedzone set delflag=? where id=?";
                Query query = session.createQuery(hql);
                for (String id : split) {
                    query.setParameter(0, '1');
                    query.setParameter(1, id);
                    query.executeUpdate();
                }
                return null;
            }
        });
    }

    public List<Subarea> findAssociationSubareaById(String id) {
        String hql = "from Subarea where decidedzone.id=?";
        return (List<Subarea>) getHibernateTemplate().find(hql, id);
    }


    public void updateDecidedZoneAndSubarea(final Decidedzone model, final List<String> subareaid) {
        getHibernateTemplate().update(model);
        Set<Subarea> subareas = model.getSubareas();
        for (String id : subareaid) {
            Subarea subarea = new Subarea();
            subarea.setId(id);
            subareas.add(subarea);
        }

    }

    public void updateDecidedZoneSetNull(final Decidedzone model) {
       getHibernateTemplate().update(model);
        Set<Subarea> subareas = model.getSubareas();
        for (Subarea subarea:subareas){
            subarea.setDecidedzone(null);
        }
    }
}
