package bos.dao;

import bos.base.BaseDao;
import bos.pojo.Function;
import bos.pojo.Role;
import bos.pojo.User;
import bos.utils.MD5Utils;
import bos.utils.PageUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends BaseDao<User> {

    public User findUserByUsernameByPassword(User model) {

        String hql="from User where username=? and password=?";
        List<User> users= (List<User>) getHibernateTemplate().find(hql, model.getUsername(), model.getPassword());
        if (users!=null && users.size()>0){
            return users.get(0);
        }
        return null;
    }

    public void editPassword(final User model) {
        final User user= (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        getHibernateTemplate().execute(new HibernateCallback<Object>() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String hql="update User set password=? where username=?";
                Query query = session.createQuery(hql);
                query.setParameter(0,MD5Utils.md5(model.getPassword()));
                query.setParameter(1,user.getUsername());
                query.executeUpdate();

                return null;
            }
        });
    }

    public User findUserByUsername(String username) {
        String hql="from User where username=?";
        List<User> users= (List<User>) getHibernateTemplate().find(hql,username);
        if (users!=null && users.size()>0){
            return users.get(0);
        }
        return null;
    }

    public List<Role> findAllRole() {
        String hql="from Role";
        List<Role> list = (List<Role>) getHibernateTemplate().find(hql);
        return list;
    }

    public void addUser(User model, String roleIds) {
        getHibernateTemplate().save(model);
        Role role=new Role();
        role.setId(roleIds);
        model.getRoles().add(role);
    }

    public int findAllUserSize(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria();
        criteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
        return list.get(0).intValue();
    }

    public List<User> findAllUser(PageUtils pageUtils) {
        DetachedCriteria criteria = pageUtils.getDetachedCriteria().setProjection(null);
        criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int start=(pageUtils.getCurrentPage()-1)*pageUtils.getPageSize();
        int pageSize=pageUtils.getPageSize();
        List<User> list = (List<User>) getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }

    public void updateUser(User model, String roleIds) {
        getHibernateTemplate().update(model);
        Role role=new Role();
        role.setId(roleIds);
        model.getRoles().add(role);
    }


    public List<Function> findFunctionListByUsername(String id) {
        String hql="select distinct f from Function f left outer join f.roles r left outer join r.users u where u.id=?";
        List<Function> list = (List<Function>) getHibernateTemplate().find(hql, id);
        return list;
    }

    public List<Function> findMenuByUserId(String id) {
        String hql = "select distinct f from Function f left outer join f.roles"
                + " r left outer join r.users u where u.id = ? and f.generatemenu = '1' "
                + "order by f.zindex desc";
        List<Function> list = (List<Function>) getHibernateTemplate().find(hql, id);
        return list;
    }

    public List<Function> findAllMenu() {
        String hql="select distinct f from Function f where f.generatemenu='1' order by f.zindex desc";
        List<Function> list = (List<Function>) getHibernateTemplate().find(hql);
        return list;
    }
}
