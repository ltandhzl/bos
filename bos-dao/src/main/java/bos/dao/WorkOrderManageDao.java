package bos.dao;

import bos.base.BaseDao;
import bos.pojo.Workordermanage;
import bos.utils.PageUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkOrderManageDao extends BaseDao<Workordermanage> {

    public void addWorkOrderManage(Workordermanage model) {
        getHibernateTemplate().save(model);
    }

    public int findAllWorkOrderManageSize(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria();
        criteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);

        return list.get(0).intValue();

    }

    public List<Workordermanage> findAllWorkOrderManage(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(null);
        criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int start=(pageUtils.getCurrentPage()-1)*pageUtils.getPageSize();
        int pageSize=pageUtils.getPageSize();
        List<Workordermanage> list = (List<Workordermanage>) getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }
}
