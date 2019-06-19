package bos.service;

import bos.dao.SubareaDao;
import bos.pojo.Subarea;
import bos.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubareaService  {
    @Autowired
    public SubareaDao subareaDao;


    public void saveSubarea(Subarea model) {
        subareaDao.saveSubarea(model);
    }

    public int findSubareaTotalSize(PageUtils pageUtils) {
        return subareaDao.findSubareaTotalSize(pageUtils);
    }

    public List<Subarea> findAllSubarea(PageUtils pageUtils) {
        return subareaDao.findAllSubarea(pageUtils);
    }

    public void deleteSubareaById(String[] split) {
        subareaDao.deleteSubareaById(split);
    }

    public void editSubarea(Subarea model) {
        subareaDao.editSubarea(model);
    }

    public List<Subarea> findAllSubarea() {
        return subareaDao.findAllSubarea();
    }

    public List<Subarea> findListNotAssociation() {
        return subareaDao.findListNotAssociation();
    }

    public List<Object> findRegionBySubarea() {
        return subareaDao.findRegionBySubarea();
    }
}
