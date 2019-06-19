package bos.service;

import bos.dao.NoticeBillDao;
import bos.pojo.Decidedzone;
import bos.pojo.Noticebill;
import bos.pojo.Workbill;
import bos.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeBillService {
    @Autowired
    private NoticeBillDao noticeBillDao;

    public void saveNoticeBill(Noticebill model) {
        noticeBillDao.saveNoticeBill(model);
    }

    public Decidedzone findDecidedZoneById(String decidedzoneId) {
        return noticeBillDao.findDecidedZoneById(decidedzoneId);
    }

    public void saveWorkBill(Workbill workbill) {
        noticeBillDao.saveWorkBill(workbill);
    }

    public List<Noticebill> findAllNoticeBill(PageUtils pageUtils) {
        return noticeBillDao.findAllNoticeBill(pageUtils);

    }

    public int findAllNoticeBillSize() {
        return noticeBillDao.findAllNoticeBillSize();
    }
}
