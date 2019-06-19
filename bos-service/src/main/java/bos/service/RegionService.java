package bos.service;

import bos.dao.RegionDao;
import bos.pojo.Region;
import bos.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionService {
    @Autowired
    public RegionDao regionDao;


    public void saveOrUpdateRegion(List<Region> list) {
        regionDao.saveOrUpdateRegion(list);
    }

    public int getTotalSize(PageUtils pageUtils) {

        return  regionDao.getTotalSize(pageUtils);
    }

    public List<Region> findAllRegionByQuery(PageUtils pageUtils) {
        return regionDao.findAllRegionByQuery(pageUtils);
    }

    public void saveRegion(Region model) {
        regionDao.saveRegion(model);
    }

    public void deleteRegionById(String[] split) {
        regionDao.deleteRegionById(split);
    }

    public void editRegion(Region model) {
        regionDao.editRegion(model);
    }

    public List<Region> findAllRegion() {
        return  regionDao.findAllRegion();
    }

    public List<Region> findAllListByQ(String q) {
        return regionDao.findAllRegionByQ(q);
    }
}
