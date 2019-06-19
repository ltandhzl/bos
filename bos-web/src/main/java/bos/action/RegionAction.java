package bos.action;

import bos.base.BaseAction;
import bos.pojo.Region;
import bos.service.RegionService;
import bos.utils.PinYin4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
    @Autowired
    public RegionService regionService;
    private String regionFile;
    public void setRegionFile(String regionFile) {
        this.regionFile = regionFile;
    }

    public String importXls() throws Exception {
        List<Region> list=new ArrayList<Region>();
        HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(regionFile));
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        for (Row row:sheet){
            int rowNum = row.getRowNum();
            if (rowNum==0){
               continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            Region region=new Region();
            region.setId(id);
            region.setProvince(province);
            region.setCity(city);
            region.setDistrict(district);
            region.setPostcode(postcode);
            province=province.substring(0,province.length()-1);
            city=city.substring(0,city.length()-1);
            district=district.substring(0,district.length()-1);
            String info=province+city+district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortcode = StringUtils.join(headByString);
            //城市编码---->>shijiazhuang
            String citycode = PinYin4jUtils.hanziToPinyin(city, "");
            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            list.add(region);
        }
        regionService.saveOrUpdateRegion(list);
        return null;
    }

    public String list(){
        int totalSize=regionService.getTotalSize(pageUtils);
        pageUtils.setTotal(totalSize);
        List<Region> regionList=regionService.findAllRegionByQuery(pageUtils);
        pageUtils.setRows(regionList);
        JavaToJson(pageUtils,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return null;
    }

    public String add(){
        try {
            ServletActionContext.getRequest().setCharacterEncoding("utf-8");
            regionService.saveRegion(model);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "list";
    }
    private String ids;
    public void setIds(String ids) {
        this.ids = ids;
    }
    public String delete(){
        String[] split = ids.split(",");
        regionService.deleteRegionById(split);
        return "list";
    }
    public String edit(){
        regionService.editRegion(model);
        return "list";
    }
    public String q;
    public void setQ(String q) {
        this.q = q;
    }
    public String regionList(){
        List<Region> regionList=null;
        if (StringUtils.isNotBlank(q)){
            regionList=regionService.findAllListByQ(q);
        }else{
            regionList=regionService.findAllRegion();
        }
        JavaToJson(regionList, new String[]{"subareas"});
        return null;
    }
}
