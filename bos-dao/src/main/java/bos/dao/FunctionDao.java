package bos.dao;

import bos.base.BaseDao;
import bos.pojo.Function;
import bos.utils.PageUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FunctionDao extends BaseDao<Function> {


    public int getAllFunctionSize(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria();
        criteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
        return list.get(0).intValue();
    }

    public List<Function> getAllFunction(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(null);
        criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int start=(pageUtils.getCurrentPage()-1)*pageUtils.getPageSize();
        int pageSize=pageUtils.getPageSize();
        List<Function> list = (List<Function>) getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }

    public List<Function> findAllFunction() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
        List<Function> list = (List<Function>) getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    public Function findParentNameById(String id) {
        String hql="from Function where id=?";
        List<Function> list = (List<Function>) getHibernateTemplate().find(hql, id);
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public void editFunction(Function model) {
        getHibernateTemplate().update(model);
    }


}
