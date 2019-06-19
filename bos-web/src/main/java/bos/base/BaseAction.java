package bos.base;

import bos.utils.PageUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected T model;
    protected PageUtils pageUtils=new PageUtils();
    protected DetachedCriteria criteria=null;
    protected int page;
    protected int rows;
    public void setPage(int page) {
        pageUtils.setCurrentPage(page);
    }
    public void setRows(int rows) {
        pageUtils.setPageSize(rows);
    }

    public void JavaToJson(Object o,String[] objects){

        try {
            JsonConfig jsonConfig = new JsonConfig();
            //指定哪些属性不需要转json
            jsonConfig.setExcludes(objects);
            JSONObject jsonObject = JSONObject.fromObject(o, jsonConfig);
            String json = jsonObject.toString();
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void JavaToJson(List list,String[] objects){
        try {
            JsonConfig jsonConfig = new JsonConfig();
            //指定哪些属性不需要转json
            jsonConfig.setExcludes(objects);
            String json =JSONArray.fromObject(list,jsonConfig).toString();
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BaseAction(){
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得BaseAction上声明的泛型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        //通过反射创建对象
        criteria=DetachedCriteria.forClass(entityClass);
        pageUtils.setDetachedCriteria(criteria);
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public T getModel() {
        return model;
    }

}
