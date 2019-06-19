package bos.realm;

import bos.dao.FunctionDao;
import bos.dao.UserDao;
import bos.pojo.Function;
import bos.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BOSRealm extends AuthorizingRealm{
	@Autowired
	private UserDao userDao;
	@Autowired
	private FunctionDao functionDao;
	
	//认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken passwordToken = (UsernamePasswordToken)token;
		//获得页面输入的用户名
		String username = passwordToken.getUsername();
		//根据用户名查询数据库中的密码
		User user = userDao.findUserByUsername(username);
		if(user == null){
			//页面输入的用户名不存在
			return null;
		}
		//简单认证信息对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		//框架负责比对数据库中的密码和页面输入的密码是否一致
		return info;
	}

	//授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//为用户授权
		info.addStringPermission("staff-list");

		//TODO 后期需要修改为根据当前登录用户查询数据库，获取实际对应的权限
		//在该方法中不能使用session获取用户信息
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Function> functionList=null;
        if (user!=null){
        	if ("admin".equals(user.getUsername())){
				functionList=functionDao.findAllFunction();
			}else{
                functionList=userDao.findFunctionListByUsername(user.getId());
			}
        	for (Function function:functionList){
				info.addStringPermission(function.getCode());
			}
		}
		return info;
	}
}
