package bos.action;

import bos.base.BaseAction;
import bos.customer.Customer;
import bos.customer.ICustomerService;
import bos.pojo.Decidedzone;
import bos.pojo.Noticebill;
import bos.pojo.User;
import bos.pojo.Workbill;
import bos.service.NoticeBillService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.List;

@Controller
@Scope("prototype")
public class NoticeBillAction extends BaseAction<Noticebill> {

    @Autowired
    private NoticeBillService noticeBillService;

    @Autowired
    private ICustomerService proxy;

    public String getCustomerInfo(){
        Customer customer = proxy.findCustomerByTelephone(model.getTelephone());
        JavaToJson(customer,new String[]{});
        return null;
    }
    public String add(){
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        model.setUser(user);
        noticeBillService.saveNoticeBill(model);
        Customer customer = proxy.findCustomerByTelephone(model.getTelephone());
        String decidedzoneId = customer.getDecidedzoneId();
        System.out.println(decidedzoneId);
        if (decidedzoneId!=null){
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            Decidedzone decidedzone= noticeBillService.findDecidedZoneById(decidedzoneId);
            model.setStaff(decidedzone.getStaff());
            Workbill workbill=new Workbill();
            workbill.setNoticebill(model);
            workbill.setType(Workbill.TYPE_1);
            workbill.setPickstate(Workbill.PICKSTATE_NO);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setAttachbilltimes(0);//追单次数
            workbill.setRemark(model.getRemark());
            workbill.setStaff(decidedzone.getStaff());
            noticeBillService.saveWorkBill(workbill);
        }else{
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);//此时的model已经是持久状态了，则直接可以更新数据库的数据
        }
        return "list";
    }

    public String list(){
        int total=noticeBillService.findAllNoticeBillSize();
        pageUtils.setTotal(total);
        List<Noticebill> noticebillList=noticeBillService.findAllNoticeBill(pageUtils);
        pageUtils.setRows(noticebillList);
        JavaToJson(pageUtils,new String[]{"noticebill","user"});
        return null;
    }
}
