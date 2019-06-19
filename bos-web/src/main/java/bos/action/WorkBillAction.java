package bos.action;

import bos.base.BaseAction;
import bos.pojo.Workbill;
import bos.service.WorkBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class WorkBillAction extends BaseAction<Workbill> {
    @Autowired
    private WorkBillService workBillService;
}
