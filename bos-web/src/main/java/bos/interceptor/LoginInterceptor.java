package bos.interceptor;

import bos.pojo.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

//为什么使用MethodFilterInterceptor?，因为该方法中可以指定需要被拦截和不被拦截的页面
public class LoginInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        User user= (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        if (user==null){
            return "login";
        }
        return actionInvocation.invoke();
    }
}
