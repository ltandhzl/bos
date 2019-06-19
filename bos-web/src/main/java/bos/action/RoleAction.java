package bos.action;

import bos.base.BaseAction;
import bos.pojo.Role;
import bos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
    @Autowired
    private RoleService roleService;

    private String functionIds;
    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    public String add(){
        roleService.addRole(model,functionIds);
        return "list";
    }

    public String list(){
        int total=roleService.getAllRoleSize(pageUtils);
        pageUtils.setTotal(total);
        List<Role> roleList=roleService.getAllRole(pageUtils);
        pageUtils.setRows(roleList);
        JavaToJson(pageUtils,new String[]{"roles","users","parentFunction"});
        return null;
    }

    public String getDistinctList(){
         Role role=roleService.findRoleById(model.getId());
         JavaToJson(role,new String[]{"users","roles","parentFunction"});
        return null;
    }
}
