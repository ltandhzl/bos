package bos.service;

import bos.dao.WorkOrderManageDao;
import bos.pojo.Workordermanage;
import bos.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkOrderManageService  {

    @Autowired
    private WorkOrderManageDao workOrderManageDao;

    public void addWorkOrderManage(Workordermanage model) {
        workOrderManageDao.addWorkOrderManage(model);
    }

    public int findAllWorkOrderManageSize(PageUtils pageUtils) {
        return workOrderManageDao.findAllWorkOrderManageSize(pageUtils);
    }

    public List<Workordermanage> findAllWorkOrderManage(PageUtils pageUtils) {
        return workOrderManageDao.findAllWorkOrderManage(pageUtils);
    }
}
