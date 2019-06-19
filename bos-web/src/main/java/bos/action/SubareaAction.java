package bos.action;


import bos.base.BaseAction;
import bos.pojo.Subarea;
import bos.service.SubareaService;
import bos.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    public SubareaService subareaService;

    public String add(){
        subareaService.saveSubarea(model);
        return "list";
    }
    public String list(){
        DetachedCriteria criteria = pageUtils.getDetachedCriteria();
        String addresskey = model.getAddresskey();
        if (StringUtils.isNotBlank(addresskey)){
            criteria.add(Restrictions.like("addresskey","%"+addresskey+"%"));
        }
        if(model.getRegion()!=null){
            String province = model.getRegion().getProvince();
            String city = model.getRegion().getCity();
            String district = model.getRegion().getDistrict();
            criteria.createAlias("region", "r");
            if(StringUtils.isNotBlank(province)){
                criteria.add(Restrictions.like("r.province","%"+province+"%"));
            }
            if (StringUtils.isNotBlank(city)){
                criteria.add(Restrictions.like("r.city","%"+city+"%"));
            }
            if (StringUtils.isNotBlank(district)){
                criteria.add(Restrictions.like("r.district","%"+district+"%"));
            }
        }
        pageUtils.setDetachedCriteria(criteria);
        int total=subareaService.findSubareaTotalSize(pageUtils);
        pageUtils.setTotal(total);
        List<Subarea> subareaList=subareaService.findAllSubarea(pageUtils);
        pageUtils.setRows(subareaList);
        JavaToJson(pageUtils,new String[]{"currentPage","detachedCriteria","pageSize",
                "decidedzone","subareas"});
        return null;
    }

    private String ids;
    public void setIds(String ids) {
        this.ids = ids;
    }
    public String delete(){
        String[] split = ids.split(",");
        subareaService.deleteSubareaById(split);
        return "list";
    }

    public String edit(){
        subareaService.editSubarea(model);
        return "list";
    }

    public String exportXls() throws IOException {
        List<Subarea> subareaList=subareaService.findAllSubarea();
        //在内存中创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个标签页
        HSSFSheet sheet = workbook.createSheet("分区数据");
        //创建标题行
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("开始编号");
        headRow.createCell(2).setCellValue("结束编号");
        headRow.createCell(3).setCellValue("位置信息");
        headRow.createCell(4).setCellValue("省市区");

        for (Subarea subarea : subareaList) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getStartnum());
            dataRow.createCell(2).setCellValue(subarea.getEndnum());
            dataRow.createCell(3).setCellValue(subarea.getPosition());
            dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }

        //第三步：使用输出流进行文件下载（一个流、两个头）

        String filename = "分区数据.xls";
        String contentType = ServletActionContext.getServletContext().getMimeType(filename);
        ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(contentType);

        //获取客户端浏览器类型
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        filename = FileUtils.encodeDownloadFilename(filename, agent);
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
        workbook.write(out);
        return null;
    }
    public String subareaList(){
        List<Subarea> subareaList=subareaService.findListNotAssociation();
        JavaToJson(subareaList,new String[]{"decidedzone","region"});
        return null;
    }

    public String findRegionBySubarea(){
        List<Object> list=subareaService.findRegionBySubarea();
        JavaToJson(list,new String[]{});
        return null;
    }
}
