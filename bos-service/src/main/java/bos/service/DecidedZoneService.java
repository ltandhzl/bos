package bos.service;

import bos.dao.DecidedZoneDao;
import bos.pojo.Decidedzone;
import bos.pojo.Subarea;
import bos.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DecidedZoneService {
    @Autowired
    public DecidedZoneDao decidedZoneDao;

    public Subarea getSubareaById(String id) {
        return decidedZoneDao.getSubareaById(id);
    }

    public void saveDecidedZone(Decidedzone decidedzone) {
        decidedZoneDao.saveDecidedZone(decidedzone);
    }

    public int getAllDecidedZoneSize(PageUtils pageUtils) {
        return decidedZoneDao.getAllDecidedZoneSize(pageUtils);
    }

    public List<Decidedzone> getAllDecidedZone(PageUtils pageUtils) {
        return decidedZoneDao.getAllDecidedZone(pageUtils);
    }

    public void deleteDecidedZoneById(String[] split) {
          decidedZoneDao.deleteDecidedZoneById(split);
    }

    public List<Subarea> findAssociationSubareaById(String id) {
        return decidedZoneDao.findAssociationSubareaById(id);
    }


    public void updateDecidedZoneAndSubarea(Decidedzone model, List<String> subareaid) {
        decidedZoneDao.updateDecidedZoneAndSubarea(model,subareaid);
    }

    public void updateDecidedZoneSetNull(Decidedzone model) {
        decidedZoneDao.updateDecidedZoneSetNull(model);
    }
}
