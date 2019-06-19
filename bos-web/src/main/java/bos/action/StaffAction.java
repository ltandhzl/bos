package bos.action;

import bos.base.BaseAction;
import bos.pojo.Staff;
import bos.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    public StaffService staffService;

    public String add(){
        try {
            ServletActionContext.getRequest().setCharacterEncoding("utf-8");
            staffService.addStaff(model);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "list";
    }
    public String list(){
        int totalSize = staffService.getTotalSize(pageUtils);
        pageUtils.setTotal(totalSize);
        List<Staff> staffList=staffService.findAllStaffBySize(pageUtils);
        pageUtils.setRows(staffList);
        this.JavaToJson(pageUtils,new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
        return null;
    }

    private String ids;
    public void setIds(String ids) {
        this.ids = ids;
    }
    public String getIds() {
        return ids;
    }
    @RequiresPermissions("staff-delete")//执行这个方法，需要当前用户具有staff-delete这个权限
    public String delete(){
        String[] split = ids.split(",");
        staffService.deleteStaffById(split);
        return "list";
    }

    @RequiresPermissions("staff-edit")
    public String edit(){
        staffService.editStaff(model);
        return "list";
    }

    public String q;
    public void setQ(String q) {
        this.q = q;
    }
    public String staffList(){
        List<Staff> staffList=null;
        if (StringUtils.isNotBlank(q)){
            staffList=staffService.findAllStaffByQ(q);
        }else{
            staffList=staffService.findAllStaff();
        }
        JavaToJson(staffList,new String[]{"decidedzones"});
        return null;
    }



}
