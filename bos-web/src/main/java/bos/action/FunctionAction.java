package bos.action;

import bos.base.BaseAction;
import bos.pojo.Function;
import bos.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
   @Autowired
    private FunctionService functionService;

   public String ajaxList(){
       List<Function> functionList=functionService.findAllFunction();
       JavaToJson(functionList,new String[]{"parentFunction","roles"});
       return null;
   }

   public String add(){
       functionService.addFunction(model);
       return "list";
   }
   public String list(){
       pageUtils.setCurrentPage(Integer.parseInt(model.getPage()));
       int total=functionService.getAllFunctionSize(pageUtils);
       pageUtils.setTotal(total);
       List<Function> functionList=functionService.getAllFunction(pageUtils);
       pageUtils.setRows(functionList);
       JavaToJson(pageUtils,new String[]{"parentFunction","roles","children"});
       return null;
   }
    public String findParentNameById(){
       Function function=functionService.findParentNameById(model.getId());
       JavaToJson(function,new String[]{"roles","children"});
       return null;
   }

   public String edit(){
       functionService.editFunction(model);
       return "list";
   }
}
