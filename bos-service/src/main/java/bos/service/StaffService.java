package bos.service;

import bos.dao.StaffDao;
import bos.pojo.Staff;
import bos.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffService {

    @Autowired
    public StaffDao staffDao;

    public void addStaff(Staff model) {

        staffDao.addStaff(model);
    }

    public int getTotalSize(PageUtils pageUtils) {

        return staffDao.getTotalSize(pageUtils);
    }

    public List<Staff> findAllStaffBySize(PageUtils pageUtils) {
        return staffDao.findAllStaffBySize(pageUtils);
    }

    public void deleteStaffById(String[] split) {
         staffDao.deleteStaffById(split);
    }

    public void editStaff(Staff model) {
        staffDao.editStaff(model);
    }

    public List<Staff> findAllStaff() {
        return staffDao.findAllStaff();
    }

    public List<Staff> findAllStaffByQ(String q) {
        return staffDao.finAllStaffByQ(q);
    }
}
