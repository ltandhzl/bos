package bos.dao;

import bos.base.BaseDao;
import bos.pojo.Region;
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
public class RegionDao extends BaseDao<Region> {

    public void saveOrUpdateRegion(List<Region> list) {

        for (Region region:list){
             getHibernateTemplate().saveOrUpdate(region);
        }
    }

    public int getTotalSize(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(Projections.rowCount());
        List<Long> countSize = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
        return countSize.get(0).intValue();
    }

    public List<Region> findAllRegionByQuery(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(null);
        int start=(pageUtils.getCurrentPage()-1)*pageUtils.getPageSize();
        int pageSize=pageUtils.getPageSize();
        List<Region> regionList= (List<Region>) getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return regionList;
    }

    public void saveRegion(Region model) {
        getHibernateTemplate().save(model);
    }

    public void deleteRegionById(final String[] split) {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {

                String hql="update Region set delflag=? where id=?";
                Query query = session.createQuery(hql);
                for (String s:split){
                    query.setParameter(0,'1');
                    query.setParameter(1,s);
                    query.executeUpdate();
                }
                return null;
            }
        });
    }

    public void editRegion(Region model) {

        getHibernateTemplate().update(model);
    }

    public List<Region> findAllRegion() {
        String hql="from Region";
        return (List<Region>) getHibernateTemplate().find(hql);
    }

    public List<Region> findAllRegionByQ(String q) {
        String hql="from Region where province like ? or city like ? or district like ? or shortcode like ? or citycode like ?";
        return (List<Region>) getHibernateTemplate().find(hql,"%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
    }
}
