package bos.service;

import bos.dao.RoleDao;
import bos.pojo.Role;
import bos.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public void addRole(Role model, String functionIds) {
        roleDao.addRole(model,functionIds);
    }

    public int getAllRoleSize(PageUtils pageUtils) {
        return roleDao.getAllRoleSize(pageUtils);
    }

    public List<Role> getAllRole(PageUtils pageUtils) {
        return roleDao.getAllRole(pageUtils);
    }

    public Role findRoleById(String id) {
        return roleDao.findRoleById(id);
    }
}
