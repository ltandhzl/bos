package bos.action;

import bos.base.BaseAction;
import bos.pojo.Function;
import bos.pojo.Role;
import bos.pojo.User;
import bos.service.UserService;
import bos.utils.MD5Utils;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    private String code;
    public void setCode(String code) {
        this.code = code;
    }

    @Autowired
    private UserService userService;

    public String login(){
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        model.setPassword(MD5Utils.md5(model.getPassword()));
        if (code!=null && key.equals(code)){
           //使用shiro框架提供的方式进行认证操作
            Subject subject = SecurityUtils.getSubject();//获得当前用户对象,状态为“未认证”
            //创建用户名密码令牌对象
            AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),model.getPassword());
            try{
                subject.login(token);
            }catch(IncorrectCredentialsException e){
                e.printStackTrace();
                this.addActionError("密码错误！");
                return LOGIN;
            }catch (UnknownAccountException e){
                e.printStackTrace();
                this.addActionError("账号不存在！");
                return LOGIN;
            }
            User user = (User) subject.getPrincipal();
            ServletActionContext.getRequest().getSession().setAttribute("user", user);
            return "home";
        }else{
            this.addActionError("验证码错误！");
            return "login";
        }
    }
    public String login1(){
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        model.setPassword(MD5Utils.md5(model.getPassword()));
        if (code!=null && key.equals(code)){
            User user=userService.findUserByUsernameByPassword(model);
            if(user==null){
                this.addActionError("用户名或者密码错误！");
                return "login";
            }else{
                ServletActionContext.getRequest().getSession().setAttribute("user",user);
                return "home";
            }
        }else{
            this.addActionError("验证码错误！");
            return "login";
        }
    }
    public String logout(){

        ServletActionContext.getRequest().getSession().invalidate();
        return "logout";
    }

    public String editPass(){
        userService.editPassword(model);
        String data="{\'code\':0}";
        String s = JSONObject.fromObject(data).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String ajaxList(){
        List<Role> roleList= userService.findAllRole();
        JavaToJson(roleList,new String[]{"functions","users"});
        return null;
    }

    private String roleIds;
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String add(){
        model.setPassword(MD5Utils.md5(model.getPassword()));
        userService.addUser(model,roleIds);
        return "list";
    }

    public String list(){
        int total=userService.findAllUserSize(pageUtils);
        pageUtils.setTotal(total);
        List<User> userList=userService.findAllUser(pageUtils);
        pageUtils.setRows(userList);
        JavaToJson(pageUtils,new String[]{"roles"});
        return null;
    }

    private String birthdayString;

    public void setBirthdayString(String birthdayString) {
        this.birthdayString = birthdayString;
    }

    public String edit() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(birthdayString);
        model.setBirthday(sdf.parse(birthdayString));
        userService.updateUser(model,roleIds);
        return "list";
    }

    public String findMenu(){
        List<Function> functionList=userService.findMenu();
        JavaToJson(functionList,new String[]{"roles","parentFunction","children"});
        return null;
    }
}
