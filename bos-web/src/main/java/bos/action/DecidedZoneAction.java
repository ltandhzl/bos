package bos.action;

import bos.base.BaseAction;
import bos.customer.Customer;
import bos.customer.ICustomerService;
import bos.pojo.Decidedzone;
import bos.pojo.Staff;
import bos.pojo.Subarea;
import bos.service.DecidedZoneService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class DecidedZoneAction extends BaseAction<Decidedzone> {
    @Autowired
    public DecidedZoneService decidedZoneService;
    //注入crm代理对象

    @Autowired
    public ICustomerService proxy;

    private List<String> subareaid;
    public void setSubareaid(List<String> subareaid) {
        this.subareaid = subareaid;
    }

    public String add(){
        for (String id:subareaid){
            Subarea subarea=decidedZoneService.getSubareaById(id);
            subarea.setDecidedzone(model);
            decidedZoneService.saveDecidedZone(model);
        }
        return "list";
    }

    public String list(){
        DetachedCriteria criteria = pageUtils.getDetachedCriteria();
        String id=model.getId();
        Staff staff = model.getStaff();
        if (StringUtils.isNotBlank(id)){
            criteria.add(Restrictions.like("id","%"+id+"%"));
        }
        if(staff!=null){
            String station = staff.getStation();
            criteria.createAlias("staff", "s");
            criteria.add(Restrictions.like("s.station","%"+station+"%"));
        }
        pageUtils.setDetachedCriteria(criteria);
        int total=decidedZoneService.getAllDecidedZoneSize(pageUtils);
        pageUtils.setTotal(total);
        List<Decidedzone> decidedzoneList=decidedZoneService.getAllDecidedZone(pageUtils);
        pageUtils.setRows(decidedzoneList);
        JavaToJson(pageUtils,new String[]{"subareas","decidedzones"});
        return null;
    }
    private String ids;
    public void setIds(String ids) {
        this.ids = ids;
    }
    public String delete(){
        String[] split = ids.split(",");
        decidedZoneService.deleteDecidedZoneById(split);
        return "list";
    }

    public String noassociation(){

        List<Customer> listNotAssociation = proxy.findListNotAssociation();
        JavaToJson(listNotAssociation,new String[]{});
        return null;
    }

    public String association(){
        List<Customer> noList=new ArrayList<Customer>();
        List<Customer> listHasAssociation = proxy.findListHasAssociation(model.getId());
        if (listHasAssociation==null){
            JavaToJson(noList,new String[]{});
        }else {
            JavaToJson(listHasAssociation,new String[]{});
        }
        return null;
    }
    private List<Integer> customerIds;

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    public String assigncustomerstodecidedzone(){
        if (customerIds!=null){
            proxy.assigncustomerstodecidedzone(model.getId(),customerIds);
        }else{
            proxy.noassigncustomerstodecidedzone(model.getId());
        }
        return "list";
    }

    public String associationSubarea(){

        List<Subarea> no=new ArrayList<Subarea>();
        List<Subarea> subareaList=decidedZoneService.findAssociationSubareaById(model.getId());
        if (subareaList!=null){
            JavaToJson(subareaList,new String[]{"decidedzone","subareas"});
        }else {
            JavaToJson(no,new String[]{});
        }
        return null;
    }

    public String edit(){

       if (subareaid!=null && subareaid.size()>0){

           decidedZoneService.updateDecidedZoneAndSubarea(model,subareaid);
       }else{
           decidedZoneService.updateDecidedZoneSetNull(model);
       }
        return "list";
    }



}
