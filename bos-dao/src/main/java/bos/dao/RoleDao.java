package bos.dao;

import bos.base.BaseDao;
import bos.pojo.Function;
import bos.pojo.Role;
import bos.utils.PageUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDao extends BaseDao<Role> {
    public void addRole(Role model, String functionIds) {
        getHibernateTemplate().save(model);
        String[] strings = functionIds.split(",");
        for (String s:strings){
            Function function=new Function();
            function.setId(s);
            model.getFunctions().add(function);
            //此时model是持久状态的对象，可以对数据库进行操作
        }
    }

    public int getAllRoleSize(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria();
        criteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
        return list.get(0).intValue();
    }

    public List<Role> getAllRole(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(null);
        criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int start=(pageUtils.getCurrentPage()-1)*pageUtils.getPageSize();
        int pageSize=pageUtils.getPageSize();
        List<Role> list = (List<Role>) getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }

    public Role findRoleById(String id) {
        String hql="from Role where id=?";
        List<Role> list = (List<Role>) getHibernateTemplate().find(hql, id);
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
