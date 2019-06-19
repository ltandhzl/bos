package bos.service;

import bos.dao.WorkBillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkBillService {
    @Autowired
    private WorkBillDao workBillDao;
}
