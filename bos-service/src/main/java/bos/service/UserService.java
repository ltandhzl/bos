package bos.service;
import bos.dao.FunctionDao;
import bos.dao.UserDao;
import bos.pojo.Function;
import bos.pojo.Role;
import bos.pojo.User;
import bos.utils.PageUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private FunctionDao functionDao;

    public User findUserByUsernameByPassword(User model) {
       return userDao.findUserByUsernameByPassword(model);
    }

    public void editPassword(User model) {
        userDao.editPassword(model);
    }

    public List<Role> findAllRole() {
        return userDao.findAllRole();
    }

    public void addUser(User model, String roleIds) {
        userDao.addUser(model,roleIds);
    }

    public int findAllUserSize(PageUtils pageUtils) {
        return userDao.findAllUserSize(pageUtils);
    }

    public List<User> findAllUser(PageUtils pageUtils) {
        return userDao.findAllUser(pageUtils);
    }

    public void updateUser(User model, String roleIds) {
        userDao.updateUser(model,roleIds);
    }

    public List<Function> findMenu() {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        List<Function> functionList=null;
        if (user!=null){
            if ("admin".equals(user.getUsername())){
                functionList=userDao.findAllMenu();
            }else{
                functionList=userDao.findMenuByUserId(user.getId());
            }
            return functionList;
        }
        return null;
    }
}
