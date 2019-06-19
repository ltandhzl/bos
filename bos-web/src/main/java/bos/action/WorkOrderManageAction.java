package bos.action;

import bos.base.BaseAction;
import bos.pojo.Workordermanage;
import bos.service.WorkOrderManageService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class WorkOrderManageAction extends BaseAction<Workordermanage> {

    @Autowired
    private WorkOrderManageService workOrderManageService;

    public String add() throws IOException {
        String i="1";
        try {
            workOrderManageService.addWorkOrderManage(model);
        }catch (Exception e){
            i="0";
        }
        ServletActionContext.getResponse().getWriter().write(i);
        return null;
    }

    public String list() {
        int total=workOrderManageService.findAllWorkOrderManageSize(pageUtils);
        pageUtils.setTotal(total);
        List<Workordermanage> workordermanageList=workOrderManageService.findAllWorkOrderManage(pageUtils);
        pageUtils.setRows(workordermanageList);
        JavaToJson(pageUtils,new String[]{});
        return null;
    }
}
